package com.cis490.haonguyen.shopgang.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Hao on 11/17/2014.
 */
@ParseClassName("Item")
public class Item extends ParseObject {

    public Item() {

    }

	public String getItemName() {
        return getString("itemName");
	}

	public void setItemName(String itemName) {
        put("itemName", itemName);
	}

	public double getItemPrice() {
        return getDouble("itemPrice");
	}

	public void setItemPrice(double itemPrice) {
        put("itemPrice", itemPrice);
	}

	public int getItemQuantity() {
        return getInt("itemQuantity");
	}

	public void setItemQuantity(int itemQuantity) {
        put("itemQuantity", itemQuantity);
	}

	public String getItemDescription() {
        return getString("itemDescription");
	}

	public void setItemDescription(String itemDescription) {
        put("itemDescription", itemDescription);
	}

    public String getItemParseID() {
        return getObjectId();
    }

    public String getStoreName() {
        return getString("storeName");
    }

    public void setStoreName(String storeName) {
        put("storeName", storeName);
    }

    public boolean getPurchasedStatus() {
        return getBoolean("purchased");
    }

    public void setPurchasedStatus(boolean purchased) {
        put("purchased", purchased);
    }

    public String getRejectReason() {
        return getString("rejectReason");
    }

    public void setRejectReason(String reason) {
        put("rejectReason", reason);
    }
}
