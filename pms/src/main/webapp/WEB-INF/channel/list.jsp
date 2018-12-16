<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!-- 引入jqyery函数库 -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>

<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="../res/fckeditor/fckeditor.js"></script>
<script src="../res/common/js/jquery.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="../res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="../res/common/js/lecheng.js" type="text/javascript"></script>
<script src="../res/lecheng/js/admin.js" type="text/javascript"></script>

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
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='toadd.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="list.do" method="post" style="padding-top:5px;">
栏目名: <input type="text" value="${QUERY.cname }" name="cname"/>
	<select name="isleaf">
	  <c:if test="${QUERY.isleaf==0 }">
	    <option value="0" selected="selected">请选择</option>
		<option value="1"  >叶子</option>
		<option value="-1" >非叶子</option>
	  </c:if>
	  
	  <c:if test="${QUERY.isleaf==1 }">
	    <option value="0" >请选择</option>
		<option value="1" selected="selected">叶子</option>
		<option value="-1" >非叶子</option>
	  </c:if>
	  
	  <c:if test="${QUERY.isleaf==-1 }">
	    <option value="0" >请选择</option>
		<option value="1" >叶子</option>
		<option value="-1" selected="selected">非叶子</option>
	  </c:if>
		    
	</select>
	<input type="submit" class="query" value="查询"/>
</form>

		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">
				<span class="pn-frequired">${ERROR }</span>
			</td>
		</tr>
<form action="deletes.do" method="post" id="tableForm">
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
		<c:forEach items="${LIST }" var="channel">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${channel.id }"/></td>
			<td align="center">${channel.id }</td>
			<td align="center">${channel.cname }</td>
			<td align="center">${channel.cpid.cname }</td>
			<td align="center">${channel.lev }</td>
			<td align="center">${channel.isleafTxt }</td>
			<td align="center">
			<a href="get.do?id=${channel.id }" onclick="if(!confirm('您确定修改吗？')) {return false;}" class="pn-opt">修改</a> | 
			<a href="delete.do?id=${channel.id }" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
			</td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>



<div style="float:left;font-size: 20px;margin-top: 10px">
<!-- 批量删除按钮 通过ID触发上面的点击事件
<input class="del-button" type="submit" value="删除" /> -->
</div>
</form>
<div class="page pb15" style="float:right;margin-top: 10px">
	<span class="r inb_a page_b">
		<!-- [当前页/尾页] -->
		<a href="list.do?page=1&cname=${QUERY.cname }&isleaf=${QUERY.isleaf}">首页</a>
		
		<a href="list.do?page=${(PAGE-1)<=0?1:(PAGE-1) }&cname=${QUERY.cname }&isleaf=${QUERY.isleaf}">上一页</a>

		<a href="list.do?page=${PAGE+1>PAGECOUNT?PAGECOUNT:PAGE+1 }&cname=${QUERY.cname }&isleaf=${QUERY.isleaf}">下一页</a>
		
		<a href="list.do?page=${PAGECOUNT }&${QUERY.cname }&${QUERY.isleaf}">尾页</a>
	
		当前第${PAGE }/共${PAGECOUNT }页
	</span>
</div>

<div style="margin-top:15px;">
<!-- 	<input class="del-button" type="button" value="取消" onclick="optCancel();"/>
	<input class="submit" type="button" value="通过" onclick="optPass();"/> -->
</div>

</div>
</body>
</html>