package com.jfinal.dbAccess;

import java.util.HashMap;


public class Model{

	private HashMap<String, Object> cols=new HashMap<String, Object>();
	
	public void set(String key,Object value)
	{
		cols.put(key, value);
	}
	public Object get(String key)
	{
		return cols.get(key);
	}
}
