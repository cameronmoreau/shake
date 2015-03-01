package mobi.idappthat.shake.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import mobi.idappthat.shake.Fragment.FacebookEventFragment;
import mobi.idappthat.shake.Fragment.LoginFragment;
import mobi.idappthat.shake.Fragment.MainFragment;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 2/28/15.
 */
public class MainActivity extends ActionBarActivity {

    public MainActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                FacebookEventFragment facebookEventFragment = new FacebookEventFragment();
                getFragmentManager().beginTransaction()
                        .add(R.id.container, facebookEventFragment)
                        .commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
