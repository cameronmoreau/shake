package mobi.idappthat.shake.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mobi.idappthat.shake.Activity.CategoryActivity;
import mobi.idappthat.shake.Adapter.PimpinListViewAdapter;
import mobi.idappthat.shake.ListView.GeneralListItem;
import mobi.idappthat.shake.ListView.PimpinListItem;
import mobi.idappthat.shake.Model.Category;
import mobi.idappthat.shake.Model.GeneralItem;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 3/1/15.
 */
public class GeneralCategoryFragment extends Fragment implements View.OnClickListener {

    private ListView lv;
    private LinearLayout layout;
    private Context context;

    private PimpinListViewAdapter arrayAdapter;
    private ArrayList<PimpinListItem> listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_category, container, false);
        context = view.getContext();

        int type = getActivity().getIntent().getIntExtra(Category.CATEGORY_TYPE, 1);
        String typeName = Category.getCategoryName(type);
        //getActivity().getActionBar().setTitle(typeName);

        lv = (ListView) view.findViewById(R.id.listView);
        listItems = new ArrayList<>();

        listItems.add(new GeneralListItem(context, new GeneralItem(
                "TEst item",
                3,
                2,
                34.5,
                33.4
        )));
        listItems.add(new GeneralListItem(context, new GeneralItem(
                "TEst item",
                3,
                2,
                34.5,
                33.4
        )));
        listItems.add(new GeneralListItem(context, new GeneralItem(
                "TEst item",
                3,
                2,
                34.5,
                33.4
        )));
        listItems.add(new GeneralListItem(context, new GeneralItem(
                "TEst item",
                3,
                2,
                34.5,
                33.4
        )));


        arrayAdapter = new PimpinListViewAdapter(context, listItems);
        lv.setAdapter(arrayAdapter);

        Toast.makeText(context, "type: " + typeName, Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout:
                break;
        }
    }
}