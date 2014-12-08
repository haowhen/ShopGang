package com.cis490.haonguyen.shopgang.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;
import com.cis490.haonguyen.shopgang.model.Item;

/**
 * Created by Alex on 12/7/2014.
 */
public class DetailsFragment extends Fragment {

    private Button btnPurchase;
    private Button btnReject;
    private Item item;

    @Override
    public void onStart(){
        super.onStart();
        initControls();
    }

    private void initControls()
    {
        TextView name = (TextView) getView().findViewById(R.id.textViewDetailItemName);
        TextView price = (TextView) getView().findViewById(R.id.textViewPrice);
        TextView quantity = (TextView) getView().findViewById(R.id.textViewQuantity);
        TextView totalPrice = (TextView) getView().findViewById(R.id.textViewTotalPrice);
        TextView addedBy = (TextView) getView().findViewById(R.id.textViewAddedBy);

        item = ((ItemListActivity)getActivity()).getItem();

        double totalPriceCalc = (item.getItemPrice() * item.getItemQuantity());

        name.setText(item.getItemName());
        price.setText(Double.toString(item.getItemPrice()));
        quantity.setText(Integer.toString(item.getItemQuantity()));
        totalPrice.setText(Double.toString(totalPriceCalc));
        addedBy.setText("NOT IMPLEMENTED");


        btnPurchase = (Button)getView().findViewById(R.id.btnPurchase);
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            item.setPurchasedStatus(true);
                item.saveEventually();
                StoreItemListFragment fragment = new StoreItemListFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ItemListcontainer, fragment);
                transaction.commit();
                Toast toast = Toast.makeText(getActivity(),"Item Purchased",Toast.LENGTH_LONG);
                toast.show();
           }
        });
        btnReject = (Button)getView().findViewById(R.id.btnReject);
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RejectFragment fragment = new RejectFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ItemListcontainer, fragment);
                transaction.commit();
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_details,
                container, false);

        return view;
    }
}