# note4j
a easy note cloud for personal useage

t_article
	uuid
	title
	author
	mdbody
	create_time
	modified_time
	is_publishded
	is_deleted
t_tag
	uuid
	name
	description
	user_id
	username
	create_time
	modified_time
t_user
	uuid
	username
t_article_tag [one to many]
	uuid
	article_id
	tag_id

目前借鉴的方法：前端把account和password，提交到服务端的登录api，服务端验证正确后，生成一个token，并把token和userId，存在缓存里（推荐redis数据库），然后把token返回给前端。前端每次的请求头中带token，这样就是简单的token 机制

作者：文杰
链接：https://www.zhihu.com/question/36724306/answer/119983931
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

数据库保存markdown，把转换处理后的html放入redis		https://stackoverflow.com/questions/59557/html-to-markdown-with-java
展示的时候直接读取 html，速度很快，读的次数远远多于写的次数
编辑的时候读取 markdown，能减少诸如 XSS 之类的安全问题
代码只需写 markdown to html 部分，免去 html to markdown
即使将来 markdown 版本修改了，或者不再使用 markdown 了，旧的数据仍然能正常显示