package com.cis490.haonguyen.shopgang.Utility;

import com.cis490.Parse.Application;
import com.cis490.haonguyen.shopgang.model.Item;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Alex on 12/11/2014.
 */
public class RefreshStore{

    protected List<Store> storeList;
    protected List<Item> itemList;
    protected boolean refreshItems;
    protected boolean refreshStores;

    public List<Store> MainStoreRefresh()
    {
        return storeList;
    }

    public List<Item> ItemListRefresh ()
    {
        return itemList;
    }

    public void setStoreList(Store store)
    {
        this.storeList.add(store);
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
