package mobi.idappthat.shake.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import mobi.idappthat.shake.Fragment.GeneralCategoryFragment;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 3/1/15.
 */
public class CategoryActivity extends ActionBarActivity {

    public static final int HOBBIES = 1;
    public static final int TRAVEL = 2;
    public static final int DINING = 3;
    public static final int FUN = 4;
    public static final int OUTDOORS = 5;
    public static final int SPORTS = 6;

    public static final String CATEGORY_TYPE = "category";

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
