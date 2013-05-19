package com.jfinal.dbAccess;

import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.derby.jdbc.ClientDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.sqlite.SQLiteDataSource;

import com.ibm.db2.jcc.DB2DataSource;
import com.informix.jdbcx.IfxDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sybase.jdbc3.jdbc.SybDataSource;

public class DSFactory {

	public static DataSource getDS(DBType type,
								String serverName,
								int port,
								String dbName,
								String username,
								String password) throws SQLException
	{
		if(DBType.ORACLE.equals(type))
		{
			return getDS(DBType.ORACLE, buildUrl(DBType.ORACLE, serverName, port, dbName), username, password);
		}
		else if(DBType.MYSQL.equals(type))
		{
			return getDS(DBType.MYSQL, buildUrl(DBType.MYSQL, serverName, port, dbName), username, password);
		}
		else if(DBType.SQLSERVER.equals(type))
		{
			return getDS(DBType.SQLSERVER, buildUrl(DBType.SQLSERVER, serverName, port, dbName), username, password);
		}
		else if(DBType.POSTGRESQL.equals(type))
		{
			return getDS(DBType.POSTGRESQL, buildUrl(DBType.POSTGRESQL, serverName, port, dbName), username, password);
		}
		else if(DBType.DERBY.equals(type))
		{
			return getDS(DBType.DERBY, buildUrl(DBType.DERBY, serverName, port, dbName), username, password);
		}
		else if(DBType.SQLITE.equals(type))
		{
			return getDS(DBType.SQLITE, buildUrl(DBType.SQLITE, serverName, port, dbName), username, password);
		}
		else if(DBType.DB2.equals(type))
		{
			return getDS(DBType.DB2, buildUrl(DBType.DB2, serverName, port, dbName), username, password);
		}
		else if(DBType.SYBASE.equals(type))
		{
			return getDS(DBType.SYBASE, buildUrl(DBType.SYBASE, serverName, port, dbName), username, password);
		}
		else if(DBType.INFORMIX.equals(type))
		{
			return getDS(DBType.INFORMIX, buildUrl(DBType.INFORMIX, serverName, port, dbName), username, password);
		}
		else
		{
			return null;
		}
	}
	
	public static DataSource getDS(DBType dbType,String url,String username,String password) throws SQLException
	{
		if(DBType.ORACLE.equals(dbType))
		{
			OracleDataSource ds=new OracleDataSource();
			ds.setURL(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.MYSQL.equals(dbType))
		{
			MysqlDataSource ds=new MysqlDataSource();
			ds.setURL(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.SQLSERVER.equals(dbType))
		{
			SQLServerDataSource ds=new SQLServerDataSource();
			ds.setURL(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.POSTGRESQL.equals(dbType))
		{
			PGSimpleDataSource ds=new PGSimpleDataSource();
			ds.setServerName(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.DERBY.equals(dbType))
		{
			ClientDataSource ds=new ClientDataSource();
			ds.setServerName(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.SQLITE.equals(dbType))
		{
			SQLiteDataSource ds=new SQLiteDataSource();
			ds.setUrl(url);
			return ds;
		}
		else if(DBType.DB2.equals(dbType))
		{
			DB2DataSource ds=new DB2DataSource();
			ds.setDatabaseName(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.SYBASE.equals(dbType))
		{
			SybDataSource ds=new SybDataSource();
			ds.setDatabaseName(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else if(DBType.INFORMIX.equals(dbType))
		{
			IfxDataSource ds=new IfxDataSource();
			ds.setServerName(url);
			ds.setUser(username);
			ds.setPassword(password);
			return ds;
		}
		else
		{
			return null;
		}
	}
	
	public static String buildUrl(DBType dbType,String serverName,int port,String dbName)
	{
		if(DBType.ORACLE.equals(dbType))
		{
			return "jdbc:oracle:thin:"+serverName+":"+port+":"+dbName;
		}
		else if(DBType.MYSQL.equals(dbType))
		{
			return "jdbc:mysql://"+serverName+":"+port+"/"+dbName;
		}
		else if(DBType.SQLSERVER.equals(dbType))
		{
			return "jdbc:microsoft:sqlserver://"+serverName+":"+port+";databaseName="+dbName;
		}
		else if(DBType.POSTGRESQL.equals(dbType))
		{
			return "jdbc:postgresql://"+serverName+":"+port+"/"+dbName;
		}
		else if(DBType.SQLITE.equals(dbType))
		{
			return "jdbc:sqlite:"+dbName;
		}
		else if(DBType.DERBY.equals(dbType))
		{
			return "jdbc:derby://"+serverName+":"+port+":"+dbName+";create=false";
		}
		else if(DBType.DB2.equals(dbType))
		{
			return "jdbc:db2://"+serverName+":"+port+"/"+dbName;
		}
		else if(DBType.SYBASE.equals(dbType))
		{
			return "jdbc:sybase:Tds:"+serverName+":"+port;
		}
		else if(DBType.INFORMIX.equals(dbType))
		{
			return "jdbc:informix-sqli://"+serverName+":"+port+"/"+dbName;
		}
		else
		{
			return  "";
		}
	}
}
