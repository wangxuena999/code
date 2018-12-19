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
<title>文章列表</title>
<meta charset="UTF-8">
<script type="text/javascript">
//创建文档就绪事件
$(function(){
		//批量删除
		$("#delete").click(function(){
			//获取被选中的多选框的value值
			//checked:表示选中的单选框和多选框
			//创建数组进行存储
			var ids=new Array();
			var msg="确定批量删除吗？";
			   if(confirm(msg)==true){
				   $("input[type=checkbox]:checked").each(function(){
					   //给数组赋值，方法是push()
						ids.push($(this).val());							 							
					});
					//链接
					if(ids.length==0){
						alert("请至少要选择一条数据");
						//location.href="articlelist.do";
						//重新加载当前页面
						location.reload();
					}else{
						//按照指定的url信息载入地址  携带数组参数     参数名   参数值
						location.href="articlesdelete.do?idss="+ids;	
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
	<div class="rpos">当前位置: 文章管理 - 列表</div>
	<form class="ropt">
	<button class="add" type="button">
		<a href="javascript:window.location.reload()">刷新</a>
	</button>
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='channel.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">

<form method="post" id="tableForm">

<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th></th>
			<th>文章ID</th>
			<th>标题</th>
			<th width="40%">内容</th>
			<th>作者</th>
			<th>创建时间</th>
			<th>所属栏目</th>
			<th>是否推荐</th>
			<th>是否热点</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${articles }" var="article" >
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${article.id }"/></td>
			<td align="center">${article.id }</td>
			<td align="center">${article.title }</td>
			<td align="center">${article.content }</td>
			<td align="center">${article.author }</td>
			<td align="center">${article.crtime }</td>
			<td align="center">${article.channelname }</td>
			<td align="center">${article.isremodTxt }</td>
			<td align="center">${article.ishotTxt }</td>
			<td align="center">
			<a href="articleget.do?id=${article.id }" onclick="if(!confirm('您确定修改吗？')) {return false;}" class="pn-opt">修改</a> | <a href="articledelete.do?id=${article.id }" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
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
		<a href="articlelist.do?currentPage=1">首页</a>
		<c:if test="${requestScope.currentPage-1>0 }">
			<a href="articlelist.do?currentPage=${requestScope.currentPage-1 }">上一页</a>
		</c:if>
		<c:if test="${requestScope.currentPage+1<=requestScope.pageCount }">
			<a href="articlelist.do?currentPage=${requestScope.currentPage+1 }">下一页</a>
		</c:if>
		<a href="articlelist.do?currentPage=${requestScope.pageCount }">尾页</a>
	</span>
</div>

<div style="margin-top:15px;">
<!-- 	<input class="del-button" type="button" value="取消" onclick="optCancel();"/>
	<input class="submit" type="button" value="通过" onclick="optPass();"/> -->
</div>

</div>
</body>
</html>