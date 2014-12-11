package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.model.Item;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Created by Alex on 11/29/2014.
 */
public class StoreItemListAdapter extends ParseQueryAdapter<Item> {

    public StoreItemListAdapter(final Context context, final String StoreName) {
        // Specification of which stores to display
        super(context, new QueryFactory<Item>() {
            public ParseQuery create() {
                ParseQuery<Store> query = ParseQuery.getQuery("Item");
                query.whereEqualTo("users", ParseUser.getCurrentUser());
                query.whereEqualTo("storeName", StoreName);
                return query;
            }
        });
    }

    //Improve Speed of adapter
    static class ViewHolderItem {
        	    TextView itemTextView;
                RelativeLayout relativeLayout;
        	}

    @Override
    public View getItemView(Item object, View v, ViewGroup parent) {
        ViewHolderItem viewHolder;

        if (v == null)
        {
            v = View.inflate(getContext(),R.layout.listview_itempage, null);

            viewHolder = new ViewHolderItem();
            viewHolder.itemTextView = (TextView) v.findViewById(R.id.textViewItemName);
            viewHolder.relativeLayout = (RelativeLayout) v.findViewById(R.id.test);

            v.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolderItem) v.getTag();
        }

        super.getItemView(object, v, parent);


        viewHolder.itemTextView.setText(object.getItemName());

        if(object.getPurchasedStatus() == true)
        {
            viewHolder.relativeLayout.setBackgroundResource(R.drawable.border_purchased);
        }

        if(object.getRejectReason() != null)
        {
            RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.itemListBorder);

            viewHolder.relativeLayout.setBackgroundResource(R.drawable.border_reject);
        }

        return v;
    }

}