package com.cis490.haonguyen.shopgang.model;

/**
 * Created by Hao on 11/17/2014.
 */
public class StoreItem {

	private String _itemName;
	private double _itemPrice;
	private int _itemQuantity;
	private String _itemDescription;
	private Store _store;

	public String ItemName() {
		return _itemName;
	}

	public void ItemName(String itemName) {
		this._itemName = itemName;
	}

	public double ItemPrice() {
		return _itemPrice;
	}

	public void ItemPrice(double itemPrice) {
		this._itemPrice = itemPrice;
	}

	public int ItemQuantity() {
		return _itemQuantity;
	}

	public void ItemQuantity(int itemQuantity) {
		this._itemQuantity = itemQuantity;
	}

	public String ItemDescription() {
		return _itemDescription;
	}

	public void ItemDescription(String itemDescription) {
		this._itemDescription = itemDescription;
	}

	public Store Store() {
		return _store;
	}

	public void Store(Store _store) {
		this._store = _store;
	}
}
