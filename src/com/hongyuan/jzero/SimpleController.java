package com.hongyuan.jzero;

import com.jfinal.core.BasePath;
import com.jfinal.core.Controller;

@BasePath("/simple")
public class SimpleController extends Controller {

	public void sayHello(int a,int b)
	{
		renderText("Hello,"+(a+b));
	}
	
	public void index()
	{
		renderJsp("/index.jsp");
	}
}