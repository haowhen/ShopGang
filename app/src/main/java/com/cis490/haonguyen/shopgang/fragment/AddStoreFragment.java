package com.cis490.haonguyen.shopgang.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cis490.haonguyen.shopgang.R;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;

/**
 * Created by Alex on 11/28/2014.
 */
public class AddStoreFragment extends Fragment {

    private ImageButton btnSelectImage;
    private Button btnAddStoreInit;
    private Button cancelButton;
    private EditText storeTitle;
    private ParseImageView storeImg;

    @Override
    public void onStart(){
        super.onStart();
        initControls();
    }

    private void initControls()
    {
        storeImg = (ParseImageView) getView().findViewById(R.id.parImgViewStore);
        storeImg.setVisibility(View.INVISIBLE);
        btnSelectImage = (ImageButton)getView().findViewById(R.id.btnAddImage);
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(storeTitle.getWindowToken(), 0);
                startCamera();
            }
        });

        btnAddStoreInit = (Button)getView().findViewById(R.id.btnAddStoreInit);
        btnAddStoreInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) getView().findViewById(R.id.editTextStoreTitle);
                String pushText = editText.getText().toString();

                if (pushText != null) {
                    Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
                    Bitmap resized = Bitmap.createScaledBitmap(icon, 200, 72, true);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    resized.compress(Bitmap.CompressFormat.PNG, 100, bos);
                    byte[] scaledData = bos.toByteArray();
                    ParseFile photoFile = new ParseFile("storeImage.png", scaledData);
                    Store store = new Store();

                    store.setStoreName(pushText);
                    store.setPhotoFile(photoFile);
                    store.saveEventually();
                    Toast toast = Toast.makeText(getActivity(), "Items pushed to Parse.com.", Toast.LENGTH_LONG);
                    toast.show();
                    Delay();
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                else {
                    Toast toast = Toast.makeText(getActivity(), "ERROR: Title cannot be empty!", Toast.LENGTH_LONG);
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

    public void startCamera() {
        Fragment cameraFragment = new CameraFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.mainLayout, cameraFragment);
        transaction.addToBackStack("NewMealFragment");
        transaction.commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_addstore,
                container, false);

        return view;
    }

  //  @Override
  //  public void onResume() {
     //   super.onResume();
    //    ParseFile photoFile = ((MainActivity) getActivity())
   //             .getCurrentAddStore().getPhotoFile();
  //      if (photoFile != null) {
   //         storeImg.setParseFile(photoFile);
   //         storeImg.loadInBackground(new GetDataCallback() {
   //             @Override
    //            public void done(byte[] data, ParseException e) {
     //               storeImg.setVisibility(View.VISIBLE);
      //          }
       //     });
       // }
   // }
}
