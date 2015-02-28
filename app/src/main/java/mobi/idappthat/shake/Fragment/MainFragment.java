package mobi.idappthat.shake.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import mobi.idappthat.shake.Activity.MainActivity;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 2/28/15.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private ImageButton ibFood, ibEntertainment, ibSports, ibOutdoors, ibNightlife, ibShopping;
    private Context context;

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        context = view.getContext();

        ibFood = (ImageButton) view.findViewById(R.id.buttonFood);
        ibEntertainment = (ImageButton) view.findViewById(R.id.buttonEntertainment);
        ibSports = (ImageButton) view.findViewById(R.id.buttonSports);
        ibOutdoors = (ImageButton) view.findViewById(R.id.buttonOutdoors);
        ibNightlife = (ImageButton) view.findViewById(R.id.buttonNightlife);
        ibShopping = (ImageButton) view.findViewById(R.id.buttonShopping);

        ibFood.setOnClickListener(this);
        ibEntertainment.setOnClickListener(this);
        ibSports.setOnClickListener(this);
        ibOutdoors.setOnClickListener(this);
        ibNightlife.setOnClickListener(this);
        ibShopping.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFood:
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
                break;
        }
    }
}
