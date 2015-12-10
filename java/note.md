### 为什么数组的起始索引是0不是1？
   源于机器语言,那时要计算一个数组元素的地址，需要将数组的起始地址加上该元素的索引，
   将起始索引设为1要么浪费数组第一个元素的空间，要么花费额外的时间将索引减1。
   
### Maven + Spring MVC +Tomcat8.0 启动时错误：
   A child container failed during start
   java.util.concurrent.ExecutionException: org.apache.catalina.LifecycleException: Failed to start component           [StandardEngine[Catalina].StandardHost[localhost].StandardContext[/SpringMVC]]
   解决方法:http://crunchify.com/org-apache-catalina-lifecycleexception/
   添加一个commons-logging.jar包即可。
