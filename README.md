
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

### 博客评论功能(只需要注册一个多说账号然后->安装->创建站点->获取代码->通用代码)将代码框中的js(如下所示)放到页面的body标签中即可。注意修改data-thread-key,data-title,data-url

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

####TODO
博客评论功能存在的问题:每个需要评论功能的页面都要添加如上的相同的代码块，怎么能把它提取出来而且在评论时能对应到每个页面？？
第二个问题:先点开第一个有评论功能的页面，能正常显示评论框，而再次点击不同的有评论功能的页面，评论框消失！
原因是评论框的内容需要动态显示需要重新加载，而是用ng-repeat的方式加载的都是静态内容，导致评论框无法显示。


###博客评论功能添加DISQUS，这是国外的第三方评论插件，目前将多说替换为disqus，问题不同:现在的问题是添加disqus的每个页面都会 有评论功能但是无法区分评论的到底是哪个blog，需要用地址栏的方式区分开来 目前未做 TODO
<script id="dsq-count-scr" src="//michaelzhangblog.disqus.com/count.js" async></script>
	<div id="disqus_thread"></div>
	<script>
		/**
		 * RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
		 * LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables
		 */
		var disqus_config = function () {
			this.page.url = 'http://michaelygzhang.github.io/home/'; // Replace PAGE_URL with your page's canonical URL variable
			this.page.identifier = 'singleton'; //注意这里要修改 Replace PAGE_IDENTIFIER with your page's unique identifier variable
		};

		(function() { // DON'T EDIT BELOW THIS LINE
			var d = document, s = d.createElement('script');

			s.src = '//michaelzhangblog.disqus.com/embed.js';

			s.setAttribute('data-timestamp', +new Date());
			(d.head || d.body).appendChild(s);
		})();
	</script>
	<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript" rel="nofollow">comments powered by Disqus.</a></noscript>

###TODO
* 2016-2-10 下午开始HeadFirst Servlet&JSP read 200pages  yes?no   yes
* 2016-2-11 HeadFirst Servlet&JSP 201-400 yes?no ?? 
* 2016-2-13 finished headfirst servlet&jsp! total=4days! pre=200pages/day!
* 2016-2-16 HeadFirst DesignPattern 
* 2016-2-24 HeadFirst DesignPattern unfinished! Spring start!
* 2016-2-27 Spring in action 1-4 finished!
* 2016 -3-3 页面在排版时都个bug 中间会空一些内容。已解决!原因:json对象的titletext太长，不换行就行！dy 
* 2016-3-19 study spring3 in action!
* 2016-5-9  zrcf clojure emacs mac ring ....studing!
* 2016-5-22 喝完德威治北太平桥开的药以后运动出汗后有皮肤大片瘙痒的情况！此刻想起wzx，在当今的中国的大环境下只能努力提升自己的实力，才会             有机会抵抗，才有更多的可选择的机会。⛽️！
* 2016-9-20 很久没有更新，计划10.1把最近半年的知识点做个总结，写blog。

####[博客地址](http://michaelygzhang.github.io/home/)

 
