<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<link href="../res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="../res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- 引时间控件 -->
<script type="text/javascript" src="../js/DatePicker/WdatePicker.js"></script>
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
<!-- 引入jQuery -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>
<!-- 引入日期控件 -->
<script type="text/javascript" language="javascript" src="js/DatePicker/WdatePicker.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>栏目新增</title>
<!-- 添加表单验证 -->
<script type="text/javascript">
	//栏目名（至少两个汉字）
	var CHKCNAME="^[\u4e00-\u9fa5]{2,}$";
	
	//验证栏目名
	function chkcname(){
		//获取元素对象的属性值，获取栏目名
		var cname=$("#cname").val();
		//定义匹配栏目名的正则表达式
		var reg=new RegExp(CHKCNAME);
		//判断输入的栏目名是否匹配规定好的正则表达式
		if(reg.test(cname)){
			//给栏目名是否输入成功的元素对象文本内容赋值
			$("#resultCname").html("✔");
			//给栏目名是否输入成功的元素对象颜色赋值
			$("#resultCname").css("color","green");
			//给方法一个返回值为true，用于使用onBlur()失焦方法判断
			return true;
		}else{
			//给栏目名是否输入成功的元素对象文本内容赋值
			$("#resultCname").html("栏目名必须使用汉字，至少两个字");
			//给栏目名是否输入成功的元素对象颜色赋值
			$("#resultCname").css("color","red");
			//清空文本框
			$("#cname").val("");
			//重新聚焦
			$("#cname").focus();
			//给方法一个返回值为false，用于使用onBlur()失焦方法判断
			return false;
		}
	}
	
	
	

</script>

<style type="">
.h2_ch a:hover, .h2_ch a.here {
    color: #FFF;
    font-weight: bold;
    background-position: 0px -32px;
}
.h2_ch a {
    float: left;
    height: 32px;
    margin-right: -1px;
    padding: 0px 16px;
    line-height: 32px;
    font-size: 14px;
    font-weight: normal;
    border: 1px solid #C5C5C5;
    background: url('../res/lecheng/img/admin/bg_ch.gif') repeat-x scroll 0% 0% transparent;
}
a {
    color: #06C;
    text-decoration: none;
}
</style>

</head>
<body>
<img src="../images/logo4.png" />
<div class="box-positon">
	<div class="rpos">当前位置: 栏目管理 - 添加</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form id="jvForm" action="add.do" method="post">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">栏目名:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="cname" id="cname" maxlength="10" onblur="chkcname()"/>
						<span id="resultCname"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">上级栏目:</td>
					<td width="80%" class="pn-fcontent">
						<select id="c1" name="pid">
							<c:forEach items="${CLIST1 }" var="c1">
								<option value="${c1.id }" name="id">${c1.cname }</option>
							</c:forEach>
						</select>
						
					</td>
				</tr>
				
				
			
				
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp; 
						<input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>