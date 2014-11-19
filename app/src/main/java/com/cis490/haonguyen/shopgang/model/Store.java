package com.cis490.haonguyen.shopgang.model;

import java.util.List;

/**
 * Created by Hao on 11/17/2014.
 */
public class Store {

	private String _storeName;
	private int _itemCount;
	private List<StoreItem> _storeItems;

	public String StoreName() {
		return _storeName;
	}

	public void StoreName(String _storeName) {
		this._storeName = _storeName;
	}

	public List<StoreItem> StoreItems() {
		return _storeItems;
	}

	public void StoreItems(List<StoreItem> storeItems) {
		this._storeItems = storeItems;
	}

	public int ItemCount() {
		return _itemCount;
	}

	public void ItemCount(int _itemCount) {
		this._itemCount = _storeItems.size();
	}
}
