package mobi.idappthat.shake;

import android.util.Log;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.concurrent.ExecutionException;

/**
 * Created by Anthony on 2/28/2015.
 */
public class DBHandler {
    private static final String serverURL = "";
    private static final String TAG = "DBHandler";

    private HttpClient client;

    public DBHandler() {

        try {
            client = new DefaultHttpClient();
        } catch (Exception e) {
            Log.d(TAG, "" + e);
        }
    }


    public void POST(String data) {

    }

    public String GET(String data) {
        return null;
    }

}
