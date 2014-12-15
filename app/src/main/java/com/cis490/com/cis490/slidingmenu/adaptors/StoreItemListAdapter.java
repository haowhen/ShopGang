package com.cis490.com.cis490.slidingmenu.adaptors;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.fragment.StoreItemListFragment;
import com.cis490.haonguyen.shopgang.model.Item;
import com.cis490.haonguyen.shopgang.model.ItemList;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

/**
 * Created by Alex on 11/29/2014.
 */
public class StoreItemListAdapter extends ParseQueryAdapter<Item> {

    protected FragmentManager manager;

    public StoreItemListAdapter(final Context context, final String StoreName, FragmentManager manager, final ItemList list) {
        // Specification of which stores to display
        super(context, new QueryFactory<Item>() {
            public ParseQuery create() {
                ParseQuery<Store> query = ParseQuery.getQuery("Item");
                query.whereEqualTo("ItemList", list);
                query.whereEqualTo("users", ParseUser.getCurrentUser());
                query.whereEqualTo("storeName", StoreName);
                query.orderByAscending("itemName");
                return query;
            }
        });
        this.manager = manager;
    }

    //Improve Speed of adapter
    static class ViewHolderItem {
        	    TextView itemTextView;
                Button btnPurchase;
                Button btnReject;
                RelativeLayout relativeLayout;
                ListView listView;
        	}

    @Override
    public View getItemView(final Item object, View v, ViewGroup parent) {
        final ViewHolderItem viewHolder;

        if (v == null)
        {
            v = View.inflate(getContext(),R.layout.listview_itempage, null);

            viewHolder = new ViewHolderItem();
            viewHolder.itemTextView = (TextView) v.findViewById(R.id.textViewItemName);
            viewHolder.relativeLayout = (RelativeLayout) v.findViewById(R.id.itemEntity);
            viewHolder.btnPurchase = (Button) v.findViewById(R.id.btnPurchaseDetails);
            viewHolder.btnReject = (Button) v.findViewById(R.id.btnRejectDetails);
            viewHolder.listView = (ListView) v.findViewById(R.id.listviewItemList);
            v.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolderItem) v.getTag();
        }

        super.getItemView(object, v, parent);

        final RelativeLayout layoutChanger = viewHolder.relativeLayout;

        viewHolder.itemTextView.setText(object.getItemName());

        viewHolder.btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                object.setPurchasedStatus(true);
                object.saveEventually();
                layoutChanger.setBackgroundResource(R.drawable.border_purchased);

                Toast toast = Toast.makeText(getContext(),"Item " + object.getItemName() +" has been purchased.", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        viewHolder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(getContext(), object.getItemName()+ " Deleted!", Toast.LENGTH_LONG);
                toast.show();
                object.deleteEventually();
                StoreItemListFragment fragment = new StoreItemListFragment();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ItemListcontainer, fragment);
                transaction.commit();

            }
        });

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