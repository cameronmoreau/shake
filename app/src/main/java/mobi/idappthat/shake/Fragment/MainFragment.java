package mobi.idappthat.shake.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 2/28/15.
 */
public class MainFragment extends Fragment implements
        View.OnClickListener, GoogleMap.OnMapLoadedCallback, LocationListener {

    private ImageButton ibFood, ibFun, ibSports, ibOutdoors, ibHobbies, ibShopping;
    private GoogleMap map;
    private ImageButton bShake;
    private Context context;

    private LocationManager locationManager;
    private Location location;

    private String provider;

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context = view.getContext();

        ibFood = (ImageButton) view.findViewById(R.id.buttonFood);
        ibHobbies = (ImageButton) view.findViewById(R.id.buttonHobbies);
        ibSports = (ImageButton) view.findViewById(R.id.buttonSports);
        ibOutdoors = (ImageButton) view.findViewById(R.id.buttonOutdoors);
        ibFun = (ImageButton) view.findViewById(R.id.buttonFun);
        ibShopping = (ImageButton) view.findViewById(R.id.buttonShopping);

        bShake = (ImageButton) view.findViewById(R.id.buttonShake);

        if(map == null) {
            map = ((MapFragment) getActivity().getFragmentManager()
                    .findFragmentById(R.id.map)).getMap();

            map.setOnMapLoadedCallback(this);
        }

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        location = locationManager.getLastKnownLocation(provider);

        ibFood.setOnClickListener(this);
        ibHobbies.setOnClickListener(this);
        ibSports.setOnClickListener(this);
        ibOutdoors.setOnClickListener(this);
        ibFun.setOnClickListener(this);
        ibShopping.setOnClickListener(this);
        bShake.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonShake:
                doJsonStuff();
                break;
            case R.id.buttonFood:
                doJsonStuff();
                break;
        }
    }

    private void doJsonStuff() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://api.randomuser.me/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(context, "Response is: "+ response.substring(0,500), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Didnt work", Toast.LENGTH_SHORT).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onMapLoaded() {
        LatLng setLocation = new LatLng(location.getLatitude(), location.getLongitude());

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(setLocation);
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 80));
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
