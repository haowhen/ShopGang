package com.cis490.Parse;

import com.parse.Parse;
import com.parse.ParseUser;

public class Application extends android.app.Application {

	public void onCreate() {
		Parse.initialize(this, "QhQhyHdGCseW6qKY7JROrxfyN4miEayBSL4Ys5de", "VY2Vw5hNpCm8H9GyC9sy2SgvghQS0qEIlkxMnIKW");
	}

}
