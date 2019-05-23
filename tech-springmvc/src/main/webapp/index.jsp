<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/hello/basicDataType.do" method="post">
		<div>姓名:</div>
		<div>
			<input name="name" value="张三" />
		</div>
		<div class="clear"></div>
		<div>年龄:</div>
		<div>
			<input name="age" value="20" />
		</div>
		<div class="clear"></div>
		<div>收入:</div>
		<div>
			<input name="income" value="100000" />
		</div>
		<div class="clear"></div>
		<div>结婚:</div>
		<div>
			<input type="radio" name="isMarried" value="true" checked="checked" />是
			<input type="radio" name="isMarried" value="false" />否
		</div>
		<div class="clear"></div>
		<div>兴趣:</div>
		<div>
			<input type="checkbox" name="interests" value="听歌" checked="checked" />听歌
			<input type="checkbox" name="interests" value="书法" checked="checked" />书法
			<input type="checkbox" name="interests" value="看电影" checked="checked" />看电影
		</div>
		<div class="clear"></div>
		<div>
			<input type="submit" value="提交表单" />
		</div>
	</form>
	
	<hr/>
	<form action="/hello/listParam.do">
		用户一：<input type="text" name="users[0].name" value="zhangsan"/>
		用户二：<input type="text" name="users[1].name" value="zhangsan1"/>
		用户三：<input type="text" name="users[2].name" value="zhangsan2"/>
		<input type="submit" value="submit"/>
	</form>

</body>
</html>