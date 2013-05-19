package com.jfinal.dbAccess;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;


public class DBHelper {

	private static HashMap<String, DataSource> dataSources=new HashMap<String, DataSource>();
	
	private static DataSource defaultDS=null;
	
	public static void addDS(String dsName,DataSource ds,boolean isDefault)
	{
		dataSources.put(dsName, ds);
		if(isDefault) defaultDS=ds;
	}
	public static void setDefault(String dsName)
	{
		if(dataSources.containsKey(dsName))
			defaultDS=dataSources.get(dsName);
	}
	public static void removeDS(String dsName)
	{
		DataSource ds=dataSources.get(dsName);
		if(ds!=null&&ds.equals(defaultDS)) defaultDS=null;
		dataSources.remove(dsName);
	}
	public static Connection getConnection()
	{
		if(defaultDS!=null)
		{
			try {
				return defaultDS.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public static Connection getConnection(String dsName)
	{
		if(dataSources.containsKey(dsName))
		{
			try {
				return dataSources.get(dsName).getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static void closeResource(Connection conn,Statement s,ResultSet rs)
	{
		try {
			if(rs!=null) rs.close();
			if(s!=null) s.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static ISqlExecutor getSqlExecutor()
	{
		ISqlExecutor executor = (ISqlExecutor) Proxy.newProxyInstance(ISqlExecutor.class.getClassLoader(),
                 new Class[] { ISqlExecutor.class },
                 new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args){
						
						Connection conn=null;
						PreparedStatement ps=null;
						ResultSet rs=null;
						
						try {
							if(args.length==1)
							{
								conn=getConnection();
								ps=conn.prepareStatement(args[0].toString());
							}
							else if(args.length==2)
							{
								conn=getConnection(args[0].toString());
								ps=conn.prepareStatement(args[1].toString());
							}
	
								boolean hasResultSet=ps.execute();
								if(hasResultSet)
								{
									ArrayList<Model> result=new ArrayList<Model>();
									rs=ps.getResultSet();
									ResultSetMetaData md=rs.getMetaData();
									while(rs.next())
									{
										Model model=new Model();
										for(int i=1;i<=md.getColumnCount();i++)
										{
											switch (md.getColumnType(i)) {
											
											case Types.BIT:
												model.set(md.getColumnName(i).toUpperCase(), rs.getBoolean(i));
												break;
											case Types.TINYINT:
												model.set(md.getColumnName(i).toUpperCase(), rs.getByte(i));
												break;
											case Types.SMALLINT:
												model.set(md.getColumnName(i).toUpperCase(), rs.getShort(i));
												break;
											case Types.INTEGER:
												model.set(md.getColumnName(i).toUpperCase(), rs.getInt(i));
												break;
											case Types.BIGINT:
												model.set(md.getColumnName(i).toUpperCase(), rs.getLong(i));
												break;
											case Types.FLOAT:
												model.set(md.getColumnName(i).toUpperCase(), rs.getFloat(i));
												break;
											case Types.DOUBLE:
												model.set(md.getColumnName(i).toUpperCase(), rs.getDouble(i));
												break;
											case Types.CHAR:
											case Types.VARCHAR:
											case Types.LONGVARCHAR:
												model.set(md.getColumnName(i).toUpperCase(), rs.getString(i));
												break;
											case Types.DATE:
												model.set(md.getColumnName(i).toUpperCase(), rs.getDate(i));
												break;
											case Types.TIME:
												model.set(md.getColumnName(i).toUpperCase(), rs.getTime(i));
												break;
											case Types.TIMESTAMP:
												model.set(md.getColumnName(i).toUpperCase(), rs.getTimestamp(i));
												break;
											case Types.BINARY:
												model.set(md.getColumnName(i).toUpperCase(), rs.getBytes(i));
												break;
											case Types.BLOB:
												model.set(md.getColumnName(i).toUpperCase(), rs.getBlob(i));
												break;
											case Types.CLOB:
												model.set(md.getColumnName(i).toUpperCase(), rs.getClob(i));
												break;
											default:
												model.set(md.getColumnName(i).toUpperCase(), rs.getString(i));
												break;
											}
										}
										result.add(model);
									}
									return result;
								}
								else
								{
									return ps.getUpdateCount();
								}
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							closeResource(conn, ps, rs);
						}
						return null;
					}
				});
		
		return executor;
	}
	
	public static ArrayList<Model> executeQuery(String sql)
	{
		return getSqlExecutor().executeQuery(sql);
	}
	public static int executeUpdate(String sql)
	{
		return getSqlExecutor().executeUpdate(sql);
	}
	public static ArrayList<Model> executeQuery(String dsName,String sql)
	{
		return getSqlExecutor().executeQuery(dsName, sql);
	}
	public static int executeUpdate(String dsName,String sql)
	{
		return getSqlExecutor().executeUpdate(dsName, sql);
	}
}
