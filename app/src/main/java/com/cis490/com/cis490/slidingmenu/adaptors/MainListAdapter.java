package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class MainListAdapter extends ParseQueryAdapter<Store> {

	public MainListAdapter(Context context) {
		// Specification of which stores to display
		super(context, new QueryFactory<Store>() {
			public ParseQuery create() {
				ParseQuery query = new ParseQuery("Store");
				query.whereExists("storeName");
				return query;
			}
		});
	}

	@Override
	public View getItemView(final Store object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.listview_mainpage, null);
		}

		super.getItemView(object, v, parent);

		// Add and download the image
		ParseImageView storeImage = (ParseImageView) v.findViewById(R.id.imgStore);
		ParseFile imageFile = object.getPhotoFile();
		if (imageFile != null) {
			storeImage.setParseFile(imageFile);
			storeImage.loadInBackground();
		}

		final View finalV = v;

		// Add the title view
		TextView titleTextView = (TextView) finalV.findViewById(R.id.textViewStoreTitle);
		titleTextView.setText(object.getStoreName());

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Item");
		query.whereEqualTo("storeName", object.getStoreName().toString());
		query.countInBackground(new CountCallback() {
			@Override
			public void done(int count, ParseException e) {
				if(e == null){
					TextView itemCountTextView = (TextView) finalV.findViewById(R.id.textViewStoreItemCount);
					itemCountTextView.setText("Item count: " + count);
				}
			}
		});

		return v;
	}


}
