package mobi.idappthat.shake.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mobi.idappthat.shake.Model.Category;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 3/1/15.
 */
public class ViewActivity extends ActionBarActivity {

    private ImageView iv;
    private TextView tvPrice, tvTitle;
    private Button dDir;

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_view);

        iv = (ImageView) findViewById(R.id.ivBanner);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        dDir = (Button) findViewById(R.id.bDirections);
        dDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "https://www.google.com/maps?q=dfw+airport+maps&rls=en&biw=1240&bih=636&um=1&ie=UTF-8&sa=X&ei=hEjzVN6PPKi_sQSyzYDIAg&ved=0CAcQ_AUoAQ";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                startActivity(intent);
            }
        });

        double price = getIntent().getExtras().getDouble("price", 200);
        String title = getIntent().getExtras().getString("title", "AIRPORT");

        tvPrice.setText(String.format("$ %.2f", price));
        tvTitle.setText(title);


        switch(getIntent().getExtras().getInt(Category.CATEGORY_TYPE, 1)) {
            case Category.DINING:
                iv.setImageResource(R.mipmap.cat_dining);
                break;
            case Category.HOBBIES:
                iv.setImageResource(R.mipmap.cat_hobbies);
                break;
            case Category.TRAVEL:
                iv.setImageResource(R.mipmap.cat_travel);
                break;
            case Category.OUTDOORS:
                iv.setImageResource(R.mipmap.cat_outdoors);
                break;
            case Category.FUN:
                iv.setImageResource(R.mipmap.cat_fun);
                break;
            case Category.SPORTS:
                iv.setImageResource(R.mipmap.cat_sports);
                break;

        }
    }
}
