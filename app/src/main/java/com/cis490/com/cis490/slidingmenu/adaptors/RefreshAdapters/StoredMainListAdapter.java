package com.cis490.com.cis490.slidingmenu.adaptors.RefreshAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.StoreNameComparer;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseFile;
import com.parse.ParseImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Alex on 12/11/2014.
 */
public class StoredMainListAdapter extends ArrayAdapter<Store> {

    private ArrayList<Store> objects;

    public StoredMainListAdapter(Context context, int textViewResourceId, ArrayList<Store> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
        Collections.sort(objects, new StoreNameComparer());
    }

    //Improve Speed of adapter
    static class ViewHolderStore {
        TextView storeTextView;
        TextView storeCount;
    }

    public View getView(int position, View v, ViewGroup parent){

        final ViewHolderStore viewHolder;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview_mainpage, parent,false);

            viewHolder = new ViewHolderStore();
            viewHolder.storeTextView = (TextView) v.findViewById(R.id.textViewStoreTitle);
            viewHolder.storeCount = (TextView) v.findViewById(R.id.textViewStoreItemCount);

            v.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolderStore) v.getTag();
        }

        Store store = objects.get(position);

        TextView titleTextView = viewHolder.storeTextView;
        titleTextView.setText(store.getStoreName());

        TextView itemCountTextView = viewHolder.storeCount;
        itemCountTextView.setText("List count: " + store.getItemCount());

        return v;

    }

}
