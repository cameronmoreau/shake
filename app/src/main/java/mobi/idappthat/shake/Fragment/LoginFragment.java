package mobi.idappthat.shake.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import mobi.idappthat.shake.R;


public class LoginFragment extends Fragment implements Session.StatusCallback, Request.GraphUserCallback {

    private static final String TAG = "MainFragment";
    private UiLifecycleHelper uiHelper;
    private SharedPreferences mData;

    public static final String DATA_FILE = "DATA_FILE";
    public static final String KEY_AUTH = "KEY_AUTH";
    public static final String NAME = "NAME";
    public static final String USERID = "USERID";

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
        authButton.setReadPermissions(Arrays.asList("email", "public_profile"));


        printKeyHash(getActivity());


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), this);
        uiHelper.onCreate(savedInstanceState);

        //mData = getActivity().getSharedPreferences(DATA_FILE, Context.MODE_PRIVATE);

    }

    private void onSessionStateChange(Session session, SessionState state,
                                      Exception exception) {
        if(state.isOpened()) {
            Request.executeMeRequestAsync(session, this);
            Log.d(TAG, "Request sent");
        } else if(state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }

    @Override
    public void call(Session session, SessionState sessionState, Exception e) {
        onSessionStateChange(session, sessionState, e);
    }

    @Override
    public void onResume() {
        super.onResume();
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed()) ) {
            onSessionStateChange(session, session.getState(), null);
        }
        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onCompleted(GraphUser graphUser, Response response) {
        if (response != null) {
            SharedPreferences.Editor editor = mData.edit();
            editor.putString(KEY_AUTH, "true");
            editor.putString(USERID, graphUser.getId());
            editor.putString(NAME, graphUser.getUsername());
            editor.apply();
        }
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {

            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }

        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }
}
