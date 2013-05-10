package com.jfinal.plugin.activerecord;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import oracle.jdbc.pool.OracleDataSource;

public class DSFactory {

	public static DataSource getDS(DBType type,String url,String username,String password) throws SQLException
	{
		if(DBType.ORACLE.equals(type))
		{
			OracleDataSource ds=new OracleDataSource();
			ds.setURL(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.MYSQL.equals(type))
		{
			MysqlDataSource ds=new MysqlDataSource();
			ds.setURL(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.SQLSERVER.equals(type))
		{
			SQLServerDataSource ds=new SQLServerDataSource();
			ds.setURL(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else
		{
			return null;
		}
	}
}
