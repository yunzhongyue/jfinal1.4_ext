package com.hongyuan.dbAccess;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.jfinal.dbAccess.DBHelper;
import com.jfinal.dbAccess.DBType;
import com.jfinal.dbAccess.DSFactory;
import com.jfinal.dbAccess.Model;

public class SqlExecutorTest {

	@Before
	public void before()
	{
		try {
			DataSource ds=DSFactory.getDS(DBType.ORACLE, "java:oracle:thin:@127.0.0.1:1521:XE", "zhang", "zhang");
			DBHelper.addDS("default", ds, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void executorTest()
	{
		ArrayList<Model> result=DBHelper.executeQuery("select * from emp");
		for(int i=0;i<result.size();i++)
		{
			Model model=result.get(i);
			System.out.println(model.get("EMPNO")+"\t"+model.get("ENAME")+"\t"+model.get("JOB")+
					"\t"+model.get("MGR")+"\t"+model.get("SAL"));
		}
	}
}
