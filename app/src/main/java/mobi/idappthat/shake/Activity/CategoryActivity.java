package mobi.idappthat.shake.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import mobi.idappthat.shake.Fragment.GeneralCategoryFragment;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 3/1/15.
 */
public class CategoryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new GeneralCategoryFragment())
                    .commit();
        }
    }
}
