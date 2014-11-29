package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cis490.haonguyen.shopgang.R;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class MainListAdapter extends ParseQueryAdapter<ParseObject> {

	public MainListAdapter(Context context) {
		// Specification of which stores to display
		super(context, new QueryFactory<ParseObject>() {
			public ParseQuery create() {
				ParseQuery query = new ParseQuery("Store");
                query.whereExists("storeName");
				return query;
			}
		});
	}

	// Customize the layout by overriding getItemView
	@Override
	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		if (v == null) {
			v = View.inflate(getContext(), R.layout.listview_mainpage, null);
		}

		super.getItemView(object, v, parent);

		// Add and download the image
		ParseImageView storeImage = (ParseImageView) v.findViewById(R.id.imgStore);
		ParseFile imageFile = object.getParseFile("imgStore");
		if (imageFile != null) {
            storeImage.setParseFile(imageFile);
            storeImage.loadInBackground();
		}

		// Add the title view
		TextView titleTextView = (TextView) v.findViewById(R.id.textViewStoreTitle);
		titleTextView.setText(object.getString("storeName"));

		return v;
	}

}
