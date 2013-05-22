package com.hongyuan.dbAccess;

import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import org.junit.Before;
import org.junit.Test;

import com.jfinal.dbAccess.DBType;
import com.jfinal.dbAccess.DBUtil;
import com.jfinal.dbAccess.DSFactory;

public class SqlExecutorTest {

	@Before
	public void before()
	{
		try {
			DataSource ds=DSFactory.getDS(DBType.ORACLE, "java:oracle:thin:@127.0.0.1:1521:XE", "zhang", "zhang");
			DBUtil.addDS("default", ds, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void executorTest() throws SQLException
	{
		CachedRowSet result=(CachedRowSet) DBUtil.execute("select * from emp");
		while(result.next())
		{
			System.out.println(result.getString(1));
		}
	}
}
