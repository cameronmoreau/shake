package mobi.idappthat.shake.ListView;

import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Cameron on 3/1/15.
 */
public interface PimpinListItem {

    public enum RowType {
        GENERAL_LIST_ITEM,
        MOVIE_LIST_ITEM
    }

    public int getViewType();
    public View getView(LayoutInflater inflater, View convertView);

}
