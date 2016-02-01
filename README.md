
#### My blog

  简介:主要使用
		      [AngularJS](https://angularjs.org/) ,
		      [Bootstrap](http://getbootstrap.com/)
	   技术实现，用以记录技术学习。

### 以下内容是记录如何进行添加博客内容以及注意事项。

#### 如何添加博客内容?

 * 第一:只要在blog.json文件中，按照指定格式添加即可。
 * 第二:将要添加的html文件放到对应路径下的文件夹内即可。
 * 注意:博客的搜索功能是根据json结构中的内容进行搜索的，所以json文件尽量写的比较专一，比如
HTML5的技术内容尽量不要出现css，java，tools字眼，以免搜索功能出现偏差

#### blog.json结构
<pre>
<code>
  {
    "ahref":"web/html5/read-html5-cookbook.html",//对应blog的文件路径
    "imgsrc":"images/html5-logo.png",//该blog显示的图片
    "titletext":"HTML5",//该blog显示的关键字,主题
     "spantext": "Reading html5 Cookbook Note."//该blog的内容简介
   }
</code>
</pre>

#### 子页面中如何添加图片？

> 正常的src="../../images/....jpeg"，这种写法是不能呈现的，应该先在git页面中打开该图片然后在把路径写在src中。这样才能正常显示图片.



#### 使用注gh-pages意事项:

 * 使用gh-pages时，如果项目中文件夹的名称为大写英文，访问url时，大写英文github是不能识别的，在线的github文件夹全是小写所以为了统一，避免出错，本地新建文件夹时，最好全部使用小写英文表示。
 * `在使用pre编写代码时，<>这种符号要进行转换,比如<div>:写成 &lt;div&gt ;`
 * 写代码时，前面总是空了很多，比较大。原因是，在写页面的时候，前面空的空白就比较多，如果紧邻起始位置写就不会出现大的空白区域。

#### 分页注意事项
> 使用 [ui-bootstrap-tpls-0.12.1.js](http://angular-ui.github.io/bootstrap/)库进行页面分页设置，其中源码设置中默认每页显示10条数据，
要修改时，需要修改源码中指令(pagination)中scope的绑定,新增了itemsPerPage:'@',对应页面的写法为:items-per-page='number',
这样就能控制每页显示多少条数据了。


### 目前存在问题
*  1 页面加载时图片暂时找不到，在console中会有404。未解决。
*  2 页面加载太慢，未进行SEO优化。


### TODO
* 保持每周六/日更新总结本周的工作内容，以及技术积累。
* 不积硅步,无以至千里！不积小流,无以成江河！重在积累！ 



* TODO 记录万能excel导入数据库中过程，mybatis动态执行sql
* 2015-9-12 已完成。
* TODO 2015-9-17 更新,添加根据数据库表字段属性进行对xml文件模板对象以及excel数据进行校验。
* TODO 添加页面简历2015-9-25  
* 2015-9-27  TODO word版简历完成
* 2015-9-29  十一计划制定完成
* 2015-10-14 算法学习开始

* 2015-10-19 制作详细计划  数据结构(1m),算法(3+m),jvm(1m),spring(1m),linux(1m).--offer(3月19日)
* 2015-10-20 THING IN JAVA (4m)
* 2015-10-23 localStorage&sessionStorage用法
* 2015-10-30 简易工作日历制作,对公/对私。 
* 2015-11-20 从做系统,因为我win10更新时，删掉了系统。

### 博客评论功能(只需要注册一个多说账号然后->安装->创建站点->获取代码->通用代码)将代码框中的js(如下所示)放到页面的body标签中即可。
<pre>
<code>
    <!-- 多说评论框 start -->
    <div class="ds-thread" data-thread-key="Michael-Simple-Factory" data-title="simple-factory" data-url="http://michaelygzhang.github.io"></div>
    <!-- 多说评论框 end -->
    <!-- 多说公共JS代码 start (一个网页只需插入一次) -->
    <script type="text/javascript">
        var duoshuoQuery = {short_name:"michaelygzhang"};
	(function() {
		var ds = document.createElement('script');
		ds.type = 'text/javascript';ds.async = true;
		ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
		ds.charset = 'UTF-8';
		(document.getElementsByTagName('head')[0]
		 || document.getElementsByTagName('body')[0]).appendChild(ds);
	})();
	</script>
     <!-- 多说公共JS代码 end -->
</code>
</pre>
####TODO
博客评论功能存在的问题:每个需要评论功能的页面都要添加如上的相同的代码块，怎么能把它提取出来而且在评论时能对应到每个页面？？


####[博客地址](http://michaelygzhang.github.io/home/)



