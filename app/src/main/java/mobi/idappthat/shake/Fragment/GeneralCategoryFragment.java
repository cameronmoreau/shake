package mobi.idappthat.shake.Fragment;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mobi.idappthat.shake.Activity.CategoryActivity;
import mobi.idappthat.shake.Adapter.PimpinListViewAdapter;
import mobi.idappthat.shake.ListView.GeneralListItem;
import mobi.idappthat.shake.ListView.PimpinListItem;
import mobi.idappthat.shake.Model.GeneralItem;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 3/1/15.
 */
public class GeneralCategoryFragment extends Fragment {

    private ListView lv;
    private String select;
    private String[] items;
    private int selected;
    private Context context;
    private TextView tvCat;
    private PimpinListViewAdapter arrayAdapter;
    private ArrayList<PimpinListItem> listItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_general_category, container, false);
        context = view.getContext();

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

        int type = getActivity().getIntent().getIntExtra(CategoryActivity.CATEGORY_TYPE, 1);
        Toast.makeText(context, "type: " + type, Toast.LENGTH_SHORT).show();

        TextView tvCatView = (TextView)view.findViewById(R.id.etRank);



        tvCatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int cat = getActivity().getIntent().getIntExtra(CategoryActivity.CATEGORY_TYPE, 1);
                items = null;
                switch (cat) {
                    case 1:
                        items = new String[]{
                                "Tech",
                                "Arts"};
                        break;
                    case 2:
                        items = new String[]{
                                "flight"};
                        break;
                    case 3:
                        items = new String[]{
                                "Dinner",
                                "Lunch",
                                "Coffee",
                                "Breakfast",};
                        break;
                    case 4:
                        items = new String[]{
                                "Movies",
                                "Music",
                                "Nightlife",
                        };
                        break;
                    case 5:
                        items = new String[]{
                                "Swimming",
                                "Trails",
                                "Hiking",};
                        break;

                    case 6:
                        items = new String[]{
                                "Football",
                                "Baseball",
                                "Basketball",
                                "Soccer",};
                        break;

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Sub Categories");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        selected=item;
                        // Do something with the selection
                        Log.d("asdlga", Integer.toString(item));
                        Log.d("asdlga2",items[item]);
                        //Toast.makeText(context, Integer.toString(item), Toast.LENGTH_SHORT);
                        TextView tv = (TextView) view.findViewById(R.id.etRank);
                        tv.setText(items[selected]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


        return view;
    }
}
