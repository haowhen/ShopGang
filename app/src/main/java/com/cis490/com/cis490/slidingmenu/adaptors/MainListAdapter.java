package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cis490.Parse.Application;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.model.Item;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.CountCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseRelation;
import com.parse.ParseUser;

public class MainListAdapter extends ParseQueryAdapter<Store> {


	public MainListAdapter(Context context) {



		// Specification of which stores to display
		super(context, new QueryFactory<Store>() {
			public ParseQuery<Store> create() {
                ParseQuery<Store> query = ParseQuery.getQuery("Store");
                query.whereEqualTo("users", ParseUser.getCurrentUser());
                query.orderByAscending("storeName");
				return query;
			}
		});
	}

    //Improve Speed of adapter
    static class ViewHolderStore {
        TextView storeTextView;
        TextView storeCount;
    }

	@Override
	public View getItemView(final Store object, View v, ViewGroup parent) {

        final ViewHolderStore viewHolder;

		if (v == null) {
			v = View.inflate(getContext(), R.layout.listview_mainpage, null);

            viewHolder = new ViewHolderStore();
            viewHolder.storeTextView = (TextView) v.findViewById(R.id.textViewStoreTitle);
            viewHolder.storeCount = (TextView) v.findViewById(R.id.textViewStoreItemCount);

            v.setTag(viewHolder);

		}
        else
        {
            viewHolder = (ViewHolderStore) v.getTag();
        }

		super.getItemView(object, v, parent);

		// Add the title view
		TextView titleTextView = viewHolder.storeTextView;
		titleTextView.setText(object.getStoreName());

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
        query.whereEqualTo("users", ParseUser.getCurrentUser());
		query.whereEqualTo("storeName", object.getStoreName());
		query.countInBackground(new CountCallback() {
			@Override
			public void done(int count, ParseException e) {
				if(e == null){
                    object.setItemCount(count);
					TextView itemCountTextView = viewHolder.storeCount;
					itemCountTextView.setText("Item count: " + count);
				}
			}
		});
        Application globalState = (Application)getContext().getApplicationContext();
        globalState.setStoreList(object);
		return v;
	}


}
