package com.cis490.haonguyen.shopgang.model;

/**
 * Created by Hao on 11/17/2014.
 */
public class StoreItem {

	private String itemName;
	private double itemPrice;
	private int itemQuantity;
	private String itemDescription;

	public String ItemName() {
		return itemName;
	}

	public void ItemName(String itemName) {
		this.itemName = itemName;
	}

	public double ItemPrice() {
		return itemPrice;
	}

	public void ItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int ItemQuantity() {
		return itemQuantity;
	}

	public void ItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String ItemDescription() {
		return itemDescription;
	}

	public void ItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
}
