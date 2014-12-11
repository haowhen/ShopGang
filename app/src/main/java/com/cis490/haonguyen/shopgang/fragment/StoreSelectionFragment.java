package com.cis490.haonguyen.shopgang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.activity.AddStoreActivity;
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;
import com.cis490.haonguyen.shopgang.model.Item;

/**
 * Created by Alex on 12/8/2014.
 */
public class StoreSelectionFragment extends Fragment {

    private Button btnExistingStore;
    private Button btnNewStore;
    private Item item;

    @Override
    public void onStart(){
        super.onStart();
        initControls();
    }

    private void initControls()
    {
        btnExistingStore = (Button)getView().findViewById(R.id.btnExisitngStore);
        btnExistingStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ExistingStoreFragment fragment = new ExistingStoreFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.StoreSelectionContainer, fragment);
                transaction.commit();
            }
        });
        btnNewStore = (Button)getView().findViewById(R.id.btnNewStore);
        btnNewStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AddStoreActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_store_selection,
                container, false);

        return view;
    }
}