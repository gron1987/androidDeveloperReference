package com.devRef.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.devRef.entities.APIMenuItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gron
 * Date: 6/30/12
 * Time: 4:23 PM
 */
public class APIArrayAdapter extends ArrayAdapter {
    private final List<APIMenuItem> menuItem;
    private final Context context;

//    static class ViewHolder{
//        public TextView label;
//    }

    public APIArrayAdapter(Context context, int textViewResourceId, List<APIMenuItem> menuItem) {
        super(context, textViewResourceId, menuItem);
        this.context = context;
        this.menuItem = menuItem;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View rowView = convertView;
//        if (rowView == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            rowView = inflater.inflate(R.layout.api_menu_item, null);
//            ViewHolder viewHolder = new ViewHolder();
//            viewHolder.label = (TextView) rowView.findViewById(R.id.label);
//            rowView.setTag(viewHolder);
//        }
//
//        ViewHolder holder = (ViewHolder) rowView.getTag();
//        APIMenuItem item = menuItem.get(position);
//        holder.label.setText(item.getTitle());
//
//        return rowView;
//    }
}
