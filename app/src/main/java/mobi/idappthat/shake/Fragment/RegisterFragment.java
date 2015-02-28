package mobi.idappthat.shake.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mobi.idappthat.shake.Activity.MainActivity;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 2/28/15.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private Button bSkip;
    private Context context;

    public RegisterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        context = view.getContext();

        bSkip = (Button) view.findViewById(R.id.buttonSkip);
        bSkip.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSkip:
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
                break;
        }
    }
}
