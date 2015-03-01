package mobi.idappthat.shake.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import mobi.idappthat.shake.Activity.CategoryActivity;
import mobi.idappthat.shake.Activity.MainActivity;
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

        lv = (ListView) view.findViewById(R.id.listView);
        listItems = new ArrayList<>();

        doJsonStuff();

        Toast.makeText(context, "type: " + typeName, Toast.LENGTH_SHORT).show();

        return view;
    }

    private void doJsonStuff() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String uId = sharedPref.getString(getString(R.string.pref_user_id), "-1");
        String uLat = sharedPref.getString(getString(R.string.pref_lat), "0");
        String uLng = sharedPref.getString(getString(R.string.pref_lng), "0");

        String uploadBuilder = new Uri.Builder()
                .scheme("http")
                .authority("52.11.11.232")
                .appendPath("shake") //url
                .appendPath(uId) // id
                .appendPath("4") //course
                .appendPath("1") //fine
                .appendPath(uLat) // lat
                .appendPath(uLng).build().toString(); //lng

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                uploadBuilder, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for(int i = 0; i < jsonArray.length(); i++) {
                        Log.e("TEST", jsonArray.getJSONObject(i).toString());
                        GeneralItem item = new GeneralItem(
                                jsonArray.getJSONObject(i).getString("name"),
                                0,
                                2,
                                jsonArray.getJSONObject(i).getString("poster")
                        );
                        listItems.add(new GeneralListItem(context, item));
                    }

                    arrayAdapter = new PimpinListViewAdapter(context, listItems);
                    lv.setAdapter(arrayAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("ERROR", e.toString());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(jsonObjReq);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout:
                break;
        }
    }
}
