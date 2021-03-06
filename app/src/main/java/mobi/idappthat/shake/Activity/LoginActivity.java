package mobi.idappthat.shake.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import mobi.idappthat.shake.Fragment.LoginFragment;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 2/28/15.
 */
public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            if(!hasRegistered()) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new LoginFragment())
                        .commit();
            } else {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }
    }

    private boolean hasRegistered() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String fbId = sharedPref.getString(getString(R.string.pref_facebook_id), "");
        if(fbId.toString().length() > 0) return true;

        return false;
    }

}
