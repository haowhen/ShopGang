package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.StoreItemListFragment;
import com.cis490.haonguyen.shopgang.model.Item;
import com.cis490.haonguyen.shopgang.model.ItemList;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex on 11/29/2014.
 */
public class ItemListAdapter extends ParseQueryAdapter<ItemList> {

    protected FragmentManager manager;

    public ItemListAdapter(final Context context, final String StoreName, FragmentManager manager) {
        // Specification of which stores to display
        super(context, new QueryFactory<ItemList>() {
            public ParseQuery create() {
                ParseQuery<Store> query = ParseQuery.getQuery("ItemList");
                query.whereEqualTo("users", ParseUser.getCurrentUser());
                query.whereEqualTo("storeName", StoreName);
                query.orderByAscending("itemName");
                return query;
            }
        });
        this.manager = manager;
    }

    //Improve Speed of adapter
    static class ViewHolderItemList {
        TextView listDateTextView;
        TextView itemCount;
        	}

    @Override
    public View getItemView(final ItemList object, View v, ViewGroup parent) {
        final ViewHolderItemList viewHolder;

        if (v == null) {
            v = View.inflate(getContext(), R.layout.listview_item_lists, null);

            viewHolder = new ViewHolderItemList();
            viewHolder.listDateTextView = (TextView) v.findViewById(R.id.textViewListDate);
            viewHolder.itemCount = (TextView) v.findViewById(R.id.textViewItemListCount);

            v.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolderItemList) v.getTag();
        }

        super.getItemView(object, v, parent);

        Date createDate = new Date();
        createDate = object.getDateCreated();
        String displayDate = new SimpleDateFormat("MM-dd-yyyy").format(createDate);

        viewHolder.listDateTextView.setText(displayDate);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
        query.whereEqualTo("ItemList", object);
        query.whereEqualTo("storeName", object.getStoreName());
        query.countInBackground(new CountCallback() {
            @Override
            public void done(int count, ParseException e) {
                if(e == null){
                    object.setItemCount(count);
                    TextView itemCountTextView = viewHolder.itemCount;
                    itemCountTextView.setText("Item count: " + count);
                }
            }
        });

        return v;
    }

}