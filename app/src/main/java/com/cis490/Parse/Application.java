package com.cis490.Parse;

import com.cis490.haonguyen.shopgang.model.Item;
import com.cis490.haonguyen.shopgang.model.ItemList;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Application extends android.app.Application {

	public static final String TAG = "ShopBunch";
    protected ArrayList<Store> storeList = new ArrayList<Store>();
    protected ArrayList<Item> itemList = new ArrayList<Item>();
    protected boolean refreshItems;
    protected boolean refreshStores;
    protected Item detailsItem;
    protected ItemList listForItems;

    public void setItemList(ItemList list)
    {
        this.listForItems = list;
    }

    public ItemList getItemList()
    {
        return this.listForItems;
    }

    public ArrayList<Store> MainStoreRefresh()
    {
        return storeList;
    }

    public ArrayList<Item> ItemListRefresh ()
    {
        return itemList;
    }

    public void setStoreList(Store store)
    {
        this.storeList.add(store);
    }

    public boolean getStoreRefreshState()
    {
        return this.refreshStores;
    }

    public ArrayList<Store> PurgeExtras() {

        int remove = this.storeList.size()/2;

        for(int i = remove; i>storeList.size(); i++) {
            this.storeList.remove(i);
        }
        this.refreshStores = true;

        return storeList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public void setDetailsItem(Item item)
    {
        this.detailsItem = item;
    }

    public Item getDetailsItem() {
        return this.detailsItem;
    }


	public void onCreate() {
        ParseObject.registerSubclass(Store.class);
        ParseObject.registerSubclass(Item.class);
        ParseObject.registerSubclass((ItemList.class));
		Parse.initialize(this, "QhQhyHdGCseW6qKY7JROrxfyN4miEayBSL4Ys5de", "VY2Vw5hNpCm8H9GyC9sy2SgvghQS0qEIlkxMnIKW");
		ParseFacebookUtils.initialize("1490087411280440");
	}

    public void FORCEREFRESH()
    {
        storeList.clear();
        this.refreshStores = false;
    }
}
