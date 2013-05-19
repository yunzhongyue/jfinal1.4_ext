package com.jfinal.dbAccess;

import java.util.ArrayList;


public interface ISqlExecutor {

	ArrayList<Model> executeQuery(String sql);
	ArrayList<Model> executeQuery(String dsName,String sql);
	int executeUpdate(String sql);
	int executeUpdate(String dsName,String sql);
	
}
