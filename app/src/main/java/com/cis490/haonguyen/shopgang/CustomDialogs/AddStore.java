package com.cis490.haonguyen.shopgang.CustomDialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cis490.Parse.Application;
import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.LoginActivity;
import com.cis490.haonguyen.shopgang.fragment.SelectionFragment;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;

/**
 * Created by Alex on 12/14/2014.
 */
public class AddStore extends AlertDialog {
    EditText storeName;
    FragmentManager manager;

    public AddStore(Context context, FragmentManager manager) {
        super(context);
        this.manager = manager;
        setTitle("Add a Store");
        setMessage("Please enter a name.");
        storeName = new EditText(context);
        setView(storeName);
        storeName.setTag("Name");
        setButton(AlertDialog.BUTTON_POSITIVE, "Add Store", (new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }
        ));

        setButton(AlertDialog.BUTTON_NEGATIVE, "Done", (new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }
        ));
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!storeName.getText().toString().isEmpty()) {
                    Store store = new Store();
                    store.setStoreName(storeName.getText().toString());
                    ParseRelation<ParseObject> relation = store.getRelation("users");
                    relation.add(ParseUser.getCurrentUser());
                    Application globalState = (Application)getContext().getApplicationContext();
                    globalState.setStoreList(store);

                    store.saveEventually();
                    Toast toast = Toast.makeText(getContext(), "Store created and saved... Create another or select done.", Toast.LENGTH_LONG);
                    toast.show();
                    storeName.setText("");
                } else {
                    Toast toast = Toast.makeText(getContext(), "ERROR: Title cannot be empty!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectionFragment frag = new SelectionFragment();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.mainLayout, frag);
                transaction.commit();
                dismiss();
            }
        });
    }}
