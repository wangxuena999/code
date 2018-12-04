<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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

<!-- 引入jQuery -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>
<!-- 引入日期控件 -->
<script type="text/javascript" language="javascript" src="js/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="../res/css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>文章修改</title>
<!-- 添加表单验证 -->
<script type="text/javascript">
//文章标题必须是汉字或大小写字母或数字，至少两个
var CHKTITLE="^[0-9a-zA-Z\u4e00-\u9fa5]{2,}$";
//文章内容必须是汉字、大小写字母、数字，至少4个字(标点为中文)
var CHKCONTENT="^[a-zA-Z0-9\u4e00-\u9fa5\u3002|\uff1f|\uff01|\uff0c|\u3001|\uff1b|\uff1a|\u201c|\u201d|\u2018|\u2019|\uff08|\uff09|\u300a|\u300b|\u3008|\u3009|\u3010|\u3011|\u300e|\u300f|\u300c|\u300d|\ufe43|\ufe44|\u3014|\u3015|\u2026|\u2014|\uff5e|\ufe4f|\uffe5]{4,}$";
//作者（至少两个汉字）
var CHKAUTHOR="^[\u4e00-\u9fa5]{2,}$";

//验证文章标题
function chktitle(){
	//获取元素对象的属性值，获取文章标题
	var title=$("#title").val();
	//定义匹配文章标题的正则表达式
	var reg=new RegExp(CHKTITLE);
	//判断输入的文章标题是否匹配规定好的正则表达式
	if(reg.test(title)){
		//给文章标题是否输入成功的元素对象文本内容赋值
		$("#resultTitle").html("✔");
		//给文章标题是否输入成功的元素对象颜色赋值
		$("#resultTitle").css("color","green");
		//给方法一个返回值为true，用于使用onBlur()失焦方法判断
		return true;
	}else{
		//给文章标题是否输入成功的元素对象文本内容赋值
		$("#resultTitle").html("文章标题必须是汉字、大小写字母、数字，至少两个字");
		//给文章标题是否输入成功的元素对象颜色赋值
		$("#resultTitle").css("color","red");
		//清空文本框
		$("#title").val("");
		//重新聚焦
		$("#title").focus();
		//给方法一个返回值为false，用于使用onBlur()失焦方法判断
		return false;
	}
}

//验证文章内容
function chkcontent(){
	//获取元素对象的属性值，获取文章内容
	var content=$("#content").val();
	//定义匹配文章内容的正则表达式
	var reg=new RegExp(CHKCONTENT);
	//判断输入的文章内容是否匹配规定好的正则表达式
	if(reg.test(content)){
		//给文章内容是否输入成功的元素对象文本内容赋值
		$("#resultContent").html("✔");
		//给文章内容是否输入成功的元素对象颜色赋值
		$("#resultContent").css("color","green");
		//给方法一个返回值为true，用于使用onBlur()失焦方法判断
		return true;
	}else{
		//给文章内容是否输入成功的元素对象文本内容赋值
		$("#resultContent").html("文章内容必须是汉字、大小写字母、数字，至少四个字");
		//给文章内容是否输入成功的元素对象颜色赋值
		$("#resultContent").css("color","red");
		//清空文本框
		$("#content").val("");
		//重新聚焦
		$("#content").focus();
		//给方法一个返回值为false，用于使用onBlur()失焦方法判断
		return false;
	}
}

//验证作者
function chkauthor(){
	//获取元素对象的属性值，获取文章作者
	var author=$("#author").val();
	//定义匹配作者的正则表达式
	var reg=new RegExp(CHKAUTHOR);
	//判断输入的作者是否匹配规定好的正则表达式
	if(reg.test(author)){
		//给作者是否输入成功的元素对象文本内容赋值
		$("#resultAuthor").html("✔");
		//给作者是否输入成功的元素对象颜色赋值
		$("#resultAuthor").css("color","green");
		//给方法一个返回值为true，用于使用onBlur()失焦方法判断
		return true;
	}else{
		//给文章作者是否输入成功的元素对象文本内容赋值
		$("#resultAuthor").html("作者姓名必须使用汉字，至少两个字");
		//给文章作者是否输入成功的元素对象颜色赋值
		$("#resultAuthor").css("color","red");
		//清空文本框
		$("#author").val("");
		//重新聚焦
		$("#author").focus();
		//给方法一个返回值为false，用于使用onBlur()失焦方法判断
		return false;
	}
}

$(function(){
	//下拉框change事件
	$("#c1").change(
		function(){
			//清空第二个下拉框
			$("#c2").empty();
			
			//ajax的异步提交
			$.post(
			"getchannel.do",//1.url
			{
			  pid:this.value //2.json类型数据，传值，this表示pid，value表示选中的一级部门的id，是二级部门的pid
			},
			function(data){ //3.成功后执行
			 if(data!=null){
				$(data).each(
				  function(){
					  //添加数据到第二个下拉框中,this表示data,id表示选中的二级部门的id
					  $("#c2").append("<option value="+this.id+">"+this.cname+"</option>");
				   }		
				);
			}	        

			},
			"json" //4.返回类型
			)
		}	

	);
});
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
<div>
	<img src="../images/logo4.png"/>	
</div>

<div class="box-positon">
	<div class="rpos">当前位置: 文章表 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form id="jvForm" action="update.do?id=${ARTICLE.id }" method="post">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>标题:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="title" id="title" maxlength="100" size="50" value="${ARTICLE.title }" onblur="chktitle()"/>
						<span id="resultTitle"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>内容:</td>
					<td width="80%" class="pn-fcontent">
						<textarea rows="15" cols="120" id="content" name="content" onblur="chkcontent()" >${ARTICLE.content }</textarea>
						<span id="resultContent"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>作者:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="author" id="author" maxlength="100" size="50" value="${ARTICLE.author }" onblur="chkauthor()"/>
						<span id="resultAuthor"></span>
					</td>
				</tr>
			
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">所属栏目:</td>
					<td width="80%" class="pn-fcontent">
						<select id="c1" name="c1">
							<c:forEach items="${CLIST1 }" var="c1">
								<!-- 显示所属栏目 -->
								<c:if test="${ARTICLE.channel.pid==c1.id }">
									<option value="${c1.id }" selected="selected">${c1.cname }</option>
								</c:if>
								<!-- 显示其他栏目-->
								<c:if test="${ARTICLE.channel.pid!=c1.id }">
									<option value="${c1.id }">${c1.cname }</option>
								</c:if>
							</c:forEach>
						</select>
						<select id="c2" name="channel.id">
							<c:forEach items="${CLIST2 }" var="c2">
								<!-- 显示所属栏目 -->
								<c:if test="${ARTICLE.channel.id==c2.id }">
									<option value="${c2.id }" selected="selected">${c2.cname }</option>
								</c:if>
								<!-- 显示其他栏目-->
								<c:if test="${ARTICLE.channel.id!=c2.id }">
									<option value="${c2.id }">${c2.cname }</option>
								</c:if>
							</c:forEach>
						</select>
								
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">是否推荐:</td>
					<td width="80%" class="pn-fcontent">
						<c:if test="${ARTICLE.isremod==1 }">
								<input type="radio" name="isremod" value="1" checked="checked" />推荐
								<input type="radio" name="isremod" value="-1" />不推荐
						</c:if> 
						<c:if test="${ARTICLE.isremod==-1 }">
								<input type="radio" name="isremod" value="1" />推荐
								<input type="radio" name="isremod" value="-1" checked="checked" />不推荐
						</c:if>
					</td>
				</tr>

					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否热点:</td>
						<td width="80%" class="pn-fcontent">
							<c:if test="${ARTICLE.ishot==1 }">
									<input type="radio" name="ishot" value="1" checked="checked" />热点
									<input type="radio" name="ishot" value="-1" />非热点
							</c:if> 
							<c:if test="${ARTICLE.ishot==-1 }">
									<input type="radio" name="ishot" value="1" />热点
									<input type="radio" name="ishot" value="-1" checked="checked" />非热点
							</c:if>
						</td>
					</tr>
				
			</tbody>
			<tbody id="tab_2" style="display: none">
				<tr>
					<td >
						<textarea rows="10" cols="10" id="productdesc" name="description"></textarea>
					</td>
				</tr>
			</tbody>
			<tbody id="tab_3" style="display: none">
				<tr>
					<td >
						<textarea rows="15" cols="136" id="productList" name="packageList"></textarea>
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>