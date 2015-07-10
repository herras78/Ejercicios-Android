package com.valles.bodytrain.db;

import android.content.Context;

public class AccesoDB {
	private BodyDB  bodyDB;
	
	AccesoDB(Context context){
		bodyDB = new BodyDB(context);		
	}

}
