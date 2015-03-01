package mobi.idappthat.shake.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import mobi.idappthat.shake.R;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.WebDialog;

/**
 * Created by mobi on 3/1/15.
 */
public class FacebookEventFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebookevent, container, false);
        Button button = (Button) view.findViewById(R.id.event_button);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        sendRequestDialog();
    }

    private void sendRequestDialog() {
        Bundle params = new Bundle();
        params.putString("User", "Would like you to join them");

        if(SessionState.OPENED.isOpened()) {
            WebDialog requestsDialog = (
                    new WebDialog.RequestsDialogBuilder(getActivity(),
                            Session.getActiveSession(),
                            params))
                    .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                        @Override
                        public void onComplete(Bundle values,
                                               FacebookException error) {
                            if (error != null) {
                                if (error instanceof FacebookOperationCanceledException) {
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Request cancelled",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Network Error",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                final String requestId = values.getString("request");
                                if (requestId != null) {
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Request sent",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Request cancelled",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    })
                    .build();
            requestsDialog.show();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "No Facebook", Toast.LENGTH_SHORT);
        }
    }

}
