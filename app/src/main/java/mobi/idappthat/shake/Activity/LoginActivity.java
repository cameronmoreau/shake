package mobi.idappthat.shake.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import mobi.idappthat.shake.Fragment.LoginFragment;
import mobi.idappthat.shake.Fragment.MainFragment;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 2/28/15.
 */
public class LoginActivity extends Activity {


    public static final String DATA_FILE = "DATA_FILE";
    public static final String KEY_AUTH = "KEY_AUTH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences mData;
        mData = getSharedPreferences(DATA_FILE, MODE_PRIVATE);


        boolean checkData = mData.getString(KEY_AUTH, "false").equals("false");

        //Check if user has already logged in
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

}