package mobi.idappthat.shake.ListView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import mobi.idappthat.shake.Activity.ViewActivity;
import mobi.idappthat.shake.Model.GeneralItem;
import mobi.idappthat.shake.R;

/**
 * Created by Cameron on 3/1/15.
 */
public class GeneralListItem implements PimpinListItem  {

    private Context context;
    private GeneralItem item;

    private String objectId;

    public GeneralListItem(Context context, GeneralItem item) {
        this.context = context;
        this.item = item;
    }

    private static class ViewHolder {
        TextView title, price, price2;
        RatingBar rating;
        ImageView icon;
        LinearLayout layout;
    }

    public GeneralItem getEvent(){
        return this.item;
    }


    @Override
    public int getViewType() {
        return PimpinListItem.RowType.GENERAL_LIST_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater inflater, View convertView) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row_item_general, null);

            holder.icon = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.title = (TextView) convertView.findViewById(R.id.tvName);
            holder.price = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.price2 = (TextView) convertView.findViewById(R.id.tvPrice2);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.layout);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        String text[] = item.getPriceText().split(",");
        holder.price.setText(text[0]);
        holder.price2.setText(text[1]);
        holder.title.setText(item.getName());

        holder.icon.setImageResource(R.mipmap.ic_launcher);
        new ImageDownloader(holder.icon)
                .execute(item.getImageUrl());


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewActivity.class);
                //i.putExtra(ViewActivity.EVENT_ID, item.getId());
                context.startActivity(i);
            }
        });


        return convertView;
    }

    class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public ImageDownloader(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
