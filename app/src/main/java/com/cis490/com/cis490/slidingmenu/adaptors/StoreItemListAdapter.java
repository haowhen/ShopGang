package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;
import com.cis490.haonguyen.shopgang.fragment.StoreItemListFragment;
import com.cis490.haonguyen.shopgang.model.Item;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by Alex on 11/29/2014.
 */
public class StoreItemListAdapter extends ParseQueryAdapter<Item> {

    public StoreItemListAdapter(final Context context, final String StoreName) {
        // Specification of which stores to display
        super(context, new QueryFactory<Item>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Item");
                query.whereContains("storeName", StoreName);
                return query;
            }
        });
    }

    @Override
    public View getItemView(Item object, View v, ViewGroup parent) {
        if (v == null)
        {
            v = View.inflate(getContext(), R.layout.listview_itempage, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image
        TextView itemTextView = (TextView) v.findViewById(R.id.textViewItemName);
        itemTextView.setText(object.getItemName());

        return v;
    }

}