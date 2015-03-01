package mobi.idappthat.shake.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import mobi.idappthat.shake.ListView.PimpinListItem;

/**
 * Created by Cameron on 3/1/15.
 */
public class PimpinListViewAdapter extends ArrayAdapter<PimpinListItem> {

    private Context context;
    private LayoutInflater mInflater;
    private List<PimpinListItem> items;

    public PimpinListViewAdapter(Context context, List<PimpinListItem> items) {
        super(context, 0, items);
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getViewTypeCount() {
        return PimpinListItem.RowType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        return getItem(position).getView(mInflater, convertView);
    }

}
