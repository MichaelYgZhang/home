# 我的主页。
注意事项:1  使用gh-pages时，如果项目中文件夹的名称为大写英文，访问url时，大写英文github是不能识别的，在线的github文件夹全是小写 
            所以为了统一，避免出错，本地新建文件夹时，最好全部使用小写英文表示。
        2 如何添加博客内容?
         第一:只要在blog.json文件中，按照指定格式添加即可。
         第二:将要添加的html文件放到对应路径下的文件夹内即可。
         注意:博客的搜索功能是根据json结构中的内容进行搜索的，所以json文件尽量写的比较专一，比如
         HTML5的技术内容尽量不要出现css，java，tools字眼，以免搜索功能出现偏差
       ========================
       blog.json结构
         {
           "ahref":"web/html5/read-html5-cookbook.html",//对应blog的文件路径
           "imgsrc":"images/html5-logo.png",//该blog显示的图片
           "titletext":"HTML5",//该blog显示的关键字,主题
           "spantext": "Reading html5 Cookbook Note."//该blog的内容简介
         }
            
