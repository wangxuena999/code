<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!-- 引入jqyery函数库 -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>

<link href="res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>

<link rel="stylesheet" href="../res/css/style.css" />


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>栏目列表</title>
<meta charset="UTF-8">

<script>
	//创建文档就绪事件
	$(function(){
		//批量删除
		$("#delete").click(function(){
			//创建数组进行存储
			var ids=new Array();
			var msg="确定批量删除吗？";
			if(confirm(msg)==true){
				$("input[type=checkbox]:checked").each(function(){
					//给数组赋值，方法是push()
					ids.push($(this).val());
				});
				if(ids.length==0){
					alert("请至少要选择一条数据");
					//重新加载当前页面
					location.reload();
				}else{
					//按照指定的url信息载入地址  携带数组参数     参数名   参数值
					location.href="channelsdelete.do?idss="+ids;
				}
			}else{
				return false;
			}
		});
		
	});
</script>

</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 栏目管理 - 列表</div>
	<form class="ropt">
	<button class="add" type="button">
		<a href="javascript:window.location.reload()">刷新</a>
	</button>
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='channelname.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">


<form method="post" id="tableForm">

<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th></th>
			<th>栏目ID</th>
			<th>栏目名</th>
			<th>上级栏目</th>
			<th>栏目级别</th>
			<th>是否叶子</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${channels }" var="channel">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${channel.id }"/></td>
			<td align="center">${channel.id }</td>
			<td align="center">${channel.cname }</td>
			<td align="center">${channel.channelpidname }</td>
			<td align="center">${channel.levname }</td>
			<td align="center">${channel.isleafTxt }</td>
			<td align="center">
			<a href="channelget.do?id=${channel.id }" onclick="if(!confirm('您确定修改吗？')) {return false;}" class="pn-opt">修改</a> | <a href="channeldelete.do?id=${channel.id }" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
			</td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>
</form>

<div style="float:left;font-size: 20px;margin-top: 10px">
<!-- 批量删除按钮 通过ID触发上面的点击事件 -->
<button id="delete">批量删除</button>
</div>

<div class="page pb15" style="float:right;">
	<span class="r inb_a page_b">
		<!-- [当前页/尾页] -->
		[${requestScope.currentPage }/${requestScope.pageCount }]
		<a href="channellist.do?currentPage=1">首页</a>
		<c:if test="${requestScope.currentPage-1>0 }">
			<a href="channellist.do?currentPage=${requestScope.currentPage-1 }">上一页</a>
		</c:if>
		<c:if test="${requestScope.currentPage+1<=requestScope.pageCount }">
			<a href="channellist.do?currentPage=${requestScope.currentPage+1 }">下一页</a>
		</c:if>
		<a href="channellist.do?currentPage=${requestScope.pageCount }">尾页</a>
	</span>
</div>

<div style="margin-top:15px;">
<!-- 	<input class="del-button" type="button" value="取消" onclick="optCancel();"/>
	<input class="submit" type="button" value="通过" onclick="optPass();"/> -->
</div>

</div>
</body>
</html>