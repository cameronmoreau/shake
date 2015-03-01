package mobi.idappthat.shake.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import mobi.idappthat.shake.Fragment.MainFragment;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 2/28/15.
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }
}
