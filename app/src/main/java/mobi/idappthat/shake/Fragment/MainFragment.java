package mobi.idappthat.shake.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.squareup.seismic.ShakeDetector;

import mobi.idappthat.shake.Activity.CategoryActivity;
import mobi.idappthat.shake.Model.Category;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 2/28/15.
 */
public class MainFragment extends Fragment implements
        View.OnClickListener, GoogleMap.OnMapLoadedCallback, LocationListener, ShakeDetector.Listener {

    private ImageButton ibDining, ibFun, ibSports, ibOutdoors, ibHobbies, ibTravel;
    private GoogleMap map;
    private ImageButton bShake;
    private Context context;

    private LocationManager locationManager;
    private Location location;
    private Animation spinAnimation;

    private String provider;

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context = view.getContext();

        ibDining = (ImageButton) view.findViewById(R.id.buttonDining);
        ibHobbies = (ImageButton) view.findViewById(R.id.buttonHobbies);
        ibSports = (ImageButton) view.findViewById(R.id.buttonSports);
        ibOutdoors = (ImageButton) view.findViewById(R.id.buttonOutdoors);
        ibFun = (ImageButton) view.findViewById(R.id.buttonFun);
        ibTravel = (ImageButton) view.findViewById(R.id.buttonTravel);

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

        ibDining.setOnClickListener(this);
        ibHobbies.setOnClickListener(this);
        ibSports.setOnClickListener(this);
        ibOutdoors.setOnClickListener(this);
        ibFun.setOnClickListener(this);
        ibTravel.setOnClickListener(this);
        bShake.setOnClickListener(this);

        spinAnimation = AnimationUtils.loadAnimation(context, R.anim.cool_spin);


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String fbId = sharedPref.getString(getString(R.string.pref_facebook_id), "");

        SensorManager sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        ShakeDetector sD = new ShakeDetector(this);
        sD.start(sensorManager);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(context, CategoryActivity.class);
        switch (v.getId()) {
            case R.id.buttonShake:
                doJsonStuff();
                break;
            case R.id.buttonHobbies:
                i.putExtra(Category.CATEGORY_TYPE, Category.HOBBIES);
                startActivity(i);
                break;
            case R.id.buttonTravel:
                i.putExtra(Category.CATEGORY_TYPE, Category.TRAVEL);
                startActivity(i);
                break;
            case R.id.buttonDining:
                i.putExtra(Category.CATEGORY_TYPE, Category.DINING);
                startActivity(i);
                break;
            case R.id.buttonFun:
                i.putExtra(Category.CATEGORY_TYPE, Category.FUN);
                startActivity(i);
                break;
            case R.id.buttonOutdoors:
                i.putExtra(Category.CATEGORY_TYPE, Category.OUTDOORS);
                startActivity(i);
                break;
            case R.id.buttonSports:
                i.putExtra(Category.CATEGORY_TYPE, Category.SPORTS);
                startActivity(i);
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

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.pref_lat), Double.toString(location.getLatitude()));
        editor.putString(getString(R.string.pref_lng), Double.toString(location.getLongitude()));
        editor.apply();

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(setLocation);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(setLocation)
                .zoom(12)
                .build();


        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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

    @Override
    public void hearShake() {
        Toast.makeText(context, "SHAKE", Toast.LENGTH_SHORT).show();

        Handler handler = new Handler();
        bShake.startAnimation(spinAnimation);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bShake.clearAnimation();
            }
        }, 2000);
    }
}
