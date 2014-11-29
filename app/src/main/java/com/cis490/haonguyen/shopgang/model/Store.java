package com.cis490.haonguyen.shopgang.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Hao on 11/17/2014.
 */
@ParseClassName("Store")
public class Store extends ParseObject{

    public Store() {

    }

	public String getStoreName() {
        return getString("storeName");
	}

	public void setStoreName(String storeName) {
        put("storeName", storeName);
	}

    public ParseFile getPhotoFile() {
        return getParseFile("imgStore");
    }

    public void setPhotoFile(ParseFile file) {
        put("imgStore", file);
    }
}
