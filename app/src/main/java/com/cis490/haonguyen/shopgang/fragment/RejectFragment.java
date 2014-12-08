package com.cis490.haonguyen.shopgang.fragment;

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
import com.cis490.haonguyen.shopgang.activity.ItemListActivity;
import com.cis490.haonguyen.shopgang.model.Item;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by Alex on 12/7/2014.
 */
public class RejectFragment extends Fragment {

    private Button btnQuickReject;
    private Button btnReasonReject;
    private Item item;

    @Override
    public void onStart(){
        super.onStart();
        initControls();
    }

    private void initControls()
    {
        TextView name = (TextView) getView().findViewById(R.id.textViewRejectItemName);
        final EditText reason = (EditText) getView().findViewById(R.id.editTextRejectReason);

        final String reasonText = reason.getText().toString();
        item = ((ItemListActivity)getActivity()).getItem();

        btnQuickReject = (Button)getView().findViewById(R.id.btnQuickReject);
        btnQuickReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item.deleteEventually();

                StoreItemListFragment fragment = new StoreItemListFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ItemListcontainer, fragment);
                transaction.commit();
                Toast toast = Toast.makeText(getActivity(),"Item Deleted",Toast.LENGTH_LONG);
                toast.show();
            }
        });
        btnReasonReject = (Button)getView().findViewById(R.id.btnRejectReason);
        btnReasonReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!reasonText.isEmpty()) {
                    item.setRejectReason(reasonText);
                    item.saveEventually();
                    StoreItemListFragment fragment = new StoreItemListFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.ItemListcontainer, fragment);
                    transaction.commit();
                }
                {
                    Toast toast = Toast.makeText(getActivity(),"Reason CANNOT be blank!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_reject,
                container, false);

        return view;
    }
}