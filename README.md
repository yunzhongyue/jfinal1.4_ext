jfinal1.4_扩展版
=============

jfinal1.4衍生版version1.0

相对于jfinal1.4的改变有：
1.去掉了jfinal内嵌服务器的支持.
2.程序默认实现了一个JFinalConfig的子类，框架不再要求实现一个JFinalConfig的子类。
在jfinal过滤器未配置configClass的情况下，默任JFinalConfig将生效。
3.可以选择在配置jfinal过滤器时，通过初始化参数配置jfinal常量。如devMode,viewType等。

4.无需手工注册Action，程序将自动将所有继承Controller进行注册。此功能默认开启，如果要手工注册，请在jfinal过滤器的启动参数中将autoRegisterAction设置为false（默认注册的Action,其key为类的全限定名，可通过BasePath注解和ActionKey注解分别为类和方法指定路径，如果不这样做，该类将不可访问。）
5.修改默认配置，将默认devMode设置为true，viewTyp设置为jsp

  注意事项：由于才疏学浅，不能保证以上功能的性能和稳定性，仅为自己开发方便而实现。请大家多多指教，谨慎使用！
