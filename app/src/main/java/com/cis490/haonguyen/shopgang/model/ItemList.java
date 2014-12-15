package com.cis490.haonguyen.shopgang.model;

import android.provider.ContactsContract;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 12/15/2014.
 */
@ParseClassName("ItemList")
public class ItemList extends ParseObject {

    private ArrayList<ParseUser> usersAuthorized = new ArrayList<ParseUser>();

    public ItemList() {

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

    public Date getDateCreated() {
        return getCreatedAt();
    }

    public void AddItem(Item item) {

        ParseRelation<ParseObject> relation = this.getRelation("Item");
        relation.add(item);
    }

    public ParseUser GetItem() {
        return null;
    }

    public void setStoreName(String storeName) {
        put("storeName", storeName);
    }

    public boolean getCompleteStatus() {
        return getBoolean("complete");
    }

    public void setCompleteStatus(boolean complete) {
        put("complete", complete);
    }

    public String getOwnedBy() {
        return getString("addedBy");
    }

    public void setOwnedBy() {

        ParseRelation<ParseObject> relation = this.getRelation("users");
        relation.add(ParseUser.getCurrentUser());
    }

    public ArrayList<ParseUser> getAuthorizedUsers() {
        return usersAuthorized;
    }

    public void setAuthorizedUsers(ParseUser user) {

        usersAuthorized.add(user);
    }

    public int getItemCount() {
        return getInt("count");
    }

    public void setItemCount(int count) {
        put("count", count);
    }
}