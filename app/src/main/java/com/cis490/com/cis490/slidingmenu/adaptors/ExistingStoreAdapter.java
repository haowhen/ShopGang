package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ExistingStoreAdapter extends ParseQueryAdapter<Store> {

	public ExistingStoreAdapter(Context context) {
		// Specification of which stores to display
		super(context, new QueryFactory<Store>() {
			public ParseQuery create() {
                    ParseQuery query = new ParseQuery("Store");
                    query.whereExists("storeName");
                    return query;
			}
		});
	}

    //Improve Speed of adapter
    static class ViewHolderItem {
        TextView storeTextView;
        Button addButton;
        Button removeButton;
    }

	@Override
	public View getItemView(final Store object, View v, ViewGroup parent) {

        final ViewHolderItem viewHolder;

		if (v == null) {

			v = View.inflate(getContext(), R.layout.listview_item_existing_store, null);

            viewHolder = new ViewHolderItem();
            viewHolder.storeTextView = (TextView) v.findViewById(R.id.textViewStoreName);
            viewHolder.addButton = (Button) v.findViewById(R.id.btnAddStoreExist);
            viewHolder.removeButton = (Button) v.findViewById(R.id.btnRemoveStoreExist);

            v.setTag(viewHolder);
		}
        else
        {
            viewHolder = (ViewHolderItem) v.getTag();
        }

        int goneVisDeterm = 0;

        ParseQuery<Store> query = ParseQuery.getQuery("Store");
        query.whereEqualTo("users", ParseUser.getCurrentUser());
        try {
            goneVisDeterm = query.count();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(goneVisDeterm > 0 )
        {
            viewHolder.addButton.setVisibility(View.GONE);
            viewHolder.removeButton.setVisibility(View.VISIBLE);
        }
        else
        {
            viewHolder.removeButton.setVisibility(View.GONE);
            viewHolder.addButton.setVisibility(View.VISIBLE);
        }

        viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.addButton.setVisibility(View.GONE);
                viewHolder.removeButton.setVisibility(View.VISIBLE);
                ParseRelation<ParseObject> relation = object.getRelation("users");
                relation.add(ParseUser.getCurrentUser());
                object.saveEventually();

                Toast toast = Toast.makeText(getContext(), "You have added " + object.getStoreName() + " to your list of stores", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.removeButton.setVisibility(View.GONE);
                viewHolder.addButton.setVisibility(View.VISIBLE);
                ParseRelation<ParseObject> relation = object.getRelation("users");
                relation.remove(ParseUser.getCurrentUser());
                object.saveEventually();

                Toast toast = Toast.makeText(getContext(), "You have removed " + object.getStoreName() + " from your list of stores", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        viewHolder.storeTextView.setText(object.getStoreName());

		super.getItemView(object, v, parent);

		return v;
	}
}
