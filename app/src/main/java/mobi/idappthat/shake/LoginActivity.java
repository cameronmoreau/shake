package mobi.idappthat.shake;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class LoginActivity extends Activity {

    public static final String DATA_FILE = "DATA_FILE";
    public static final String KEY_AUTH = "KEY_AUTH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences mData;
        mData = getSharedPreferences(DATA_FILE, MODE_PRIVATE);

        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "pea_2.ttf");
        TextView mTextView = (TextView) findViewById(R.id.text);
        mTextView.setTypeface(mTypeface);

        //Check if user has already logged in
        if (savedInstanceState == null && mData.getString(KEY_AUTH, "false").equals("false")) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        } else {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
