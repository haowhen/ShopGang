package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cis490.haonguyen.shopgang.R;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by Alex on 11/29/2014.
 */
public class StoreItemListAdapter extends ParseQueryAdapter<ParseObject> {

    public StoreItemListAdapter(Context context) {
        // Specification of which stores to display
        super(context, new QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Item");
                //String s = getIntent().getStringExtra("myString", "some default value");
                query.whereContains("storeName", "Walmart");
                return query;
            }
        });
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null)
        {
            v = View.inflate(getContext(), R.layout.listview_mainpage, null);
        }

        super.getItemView(object, v, parent);

        // Add and download the image
        TextView itemTextView = (TextView) v.findViewById(R.id.textViewStoreTitle);
        itemTextView.setText(object.getString("itemName"));

        return v;
    }

}