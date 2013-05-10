package com.hongyuan.jzero;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;


public class HasParamController extends Controller{
	@ActionKey("/add")
	public void add(String name)
	{
		renderText(name+"");
	}
}
