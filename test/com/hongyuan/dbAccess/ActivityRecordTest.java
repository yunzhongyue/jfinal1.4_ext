package com.hongyuan.dbAccess;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.jfinal.dbAccess.DBType;
import com.jfinal.dbAccess.DSFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;

public class ActivityRecordTest {

	@Before
	public void setUp() throws SQLException
	{
		DataSource ds=DSFactory.getDS(DBType.ORACLE, "java:oracle:thin:@127.0.0.1:1521:XE",
				"zhang","zhang");
		ActiveRecordPlugin ar=new ActiveRecordPlugin(ds);
		ar.setDialect(new OracleDialect());
		ar.addMapping("emp", "empno", Emp.class);
		ar.start();
	}
	
	@Test
	public void test() {
		Emp emp=new Emp().findById("7788");
		System.out.println(emp.getStr("ename"));
	}

}
