package com.cis490.Parse;

import com.cis490.haonguyen.shopgang.model.Item;
import com.cis490.haonguyen.shopgang.model.Store;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class Application extends android.app.Application {

	public static final String TAG = "ShopBunch";

	public void onCreate() {
        ParseObject.registerSubclass(Store.class);
        ParseObject.registerSubclass(Item.class);
		Parse.initialize(this, "QhQhyHdGCseW6qKY7JROrxfyN4miEayBSL4Ys5de", "VY2Vw5hNpCm8H9GyC9sy2SgvghQS0qEIlkxMnIKW");
		ParseFacebookUtils.initialize("1490087411280440");
	}

}
