package com.jfinal.dbAccess;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashMap;

import javax.sql.DataSource;

import com.sun.rowset.CachedRowSetImpl;


public class DBUtil {
	
	private static HashMap<String, DataSource> dataSources=new HashMap<String, DataSource>();
	
	private static DataSource defaultDS=null;
	
	public static void addDS(String dsName,DataSource ds,boolean isDefault)
	{
		dataSources.put(dsName, ds);
		if(isDefault) defaultDS=ds;
	}
	public static void addDS(String dsName,DataSource ds)
	{
		dataSources.put(dsName, ds);
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
	public static Object executeUseDS(String dsName,String sql,Object...params)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=dataSources.get(dsName).getConnection();
			ps=conn.prepareStatement(sql);
			fillStatement(ps, params);
			boolean hasResultSet=ps.execute();
			
			if(hasResultSet)
			{
				rs=ps.getResultSet();
				CachedRowSetImpl crs=new CachedRowSetImpl();
				crs.populate(rs);
				return crs;
			}
			else
			{
				return ps.getUpdateCount();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			closeResource(conn, ps, rs);
		}
	}
	public static Object execute(String sql,Object...params)
	{
		return executeUseDS("default",sql,params);
	}
	/**
     * Fill the <code>PreparedStatement</code> replacement parameters with
     * the given objects.
     * @param stmt PreparedStatement to fill
     * @param params Query replacement parameters; <code>null</code> is a valid
     * value to pass in.
     * @throws SQLException if a database access error occurs
     */
    public static void fillStatement(PreparedStatement stmt, Object... params) throws SQLException {

        // check the parameter count, if we can
        ParameterMetaData pmd = null;
       
            try {
				pmd = stmt.getParameterMetaData();
				int stmtCount = pmd.getParameterCount();
				int paramsCount = params == null ? 0 : params.length;

				if (stmtCount != paramsCount) {
				    throw new SQLException("Wrong number of parameters: expected "
				            + stmtCount + ", was given " + paramsCount);
				}
			} catch (Exception e) {
			}
      

        // nothing to do here
        if (params == null) {
            return;
        }

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                stmt.setObject(i + 1, params[i]);
            } else {
                // VARCHAR works with many drivers regardless
                // of the actual column type.  Oddly, NULL and
                // OTHER don't work with Oracle's drivers.
                int sqlType = Types.VARCHAR;
                
                sqlType = pmd.getParameterType(i + 1);

                stmt.setNull(i + 1, sqlType);
            }
        }
    }
}
