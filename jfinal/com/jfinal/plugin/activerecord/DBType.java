package com.jfinal.plugin.activerecord;

public enum DBType {

	ORACLE,MYSQL,SQLSERVER,POSTGRESQL,SQLITE,DB2,INFORMIX,OTHER;
	
	public static DBType get(String type)
	{
		if(type!=null)
		{
			if(type.equalsIgnoreCase("ORACLE"))
			{
				return DBType.ORACLE;
			}
			else if(type.equalsIgnoreCase("MYSQL"))
			{
				return DBType.MYSQL;
			}
			else if(type.equalsIgnoreCase("SQLSERVER"))
			{
				return DBType.SQLSERVER;
			}
			else if(type.equalsIgnoreCase("POSTGRESQL"))
			{
				return DBType.POSTGRESQL;
			}
			else if(type.equalsIgnoreCase("SQLITE"))
			{
				return DBType.SQLITE;
			}
			else if(type.equalsIgnoreCase("DB2"))
			{
				return DBType.DB2;
			}
			else if(type.equalsIgnoreCase("INFORMIX"))
			{
				return DBType.INFORMIX;
			}
			else
			{
				return DBType.OTHER;
			}
		}
		else
		{
			return DBType.OTHER;
		}
	}
}
