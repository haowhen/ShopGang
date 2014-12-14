package com.cis490.haonguyen.shopgang.fragment;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.AddItemActivity;
import com.cis490.haonguyen.shopgang.model.Item;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;

/**
 * Created by Alex on 11/30/2014.
 */
public class AddItemFragment extends Fragment {

    private Button btnAddStoreInit;
    Item item;

    @Override
    public void onStart(){
        super.onStart();
        initControls();
    }

    private void initControls()
    {
        btnAddStoreInit = (Button)getView().findViewById(R.id.btnAddItemInit);
        btnAddStoreInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText itemName = (EditText) getView().findViewById(R.id.editTextItemName);
                String name = itemName.getText().toString();

                EditText itemQuantity = (EditText) getView().findViewById(R.id.editTextQuantity);
                String quantity = itemQuantity.getText().toString();

                EditText itemPrice = (EditText) getView().findViewById(R.id.editTextPrice);
                String price = itemPrice.getText().toString();

                EditText itemDescription = (EditText) getView().findViewById(R.id.editTextDescription);
                String description = itemDescription.getText().toString();

                if (!name.isEmpty() && !quantity.isEmpty()) {
                    item = ((AddItemActivity) getActivity()).getAddItem();

                    item.setStoreName(((AddItemActivity) getActivity()).getStore());
                    item.setItemName(name);
                    item.setPurchasedStatus(false);
                    item.setItemQuantity(Integer.parseInt(quantity));
                    if (price.isEmpty()) {
                        item.setItemPrice(0);
                    } else {
                        item.setItemPrice(Double.parseDouble(price));
                    }

                    if (description.isEmpty()) {
                        item.setItemDescription("");
                    } else {
                        item.setItemDescription(description);
                    }
                    ParseRelation<ParseObject> relation = item.getRelation("users");
                    relation.add(ParseUser.getCurrentUser());
                    item.setAddedBy(ParseUser.getCurrentUser().getUsername());

                    item.saveEventually();
                    Toast toast = Toast.makeText(getActivity(), "Items pushed to Parse.com.", Toast.LENGTH_LONG);
                    toast.show();
                    Delay();
                    NavUtils.navigateUpFromSameTask(getActivity());
                } else {
                    Toast toast = Toast.makeText(getActivity(), "ERROR: Title and/or quantity cannot be empty!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public void Delay(){

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
            }
        }, 6000);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_additem,
                container, false);

        return view;
    }
}
