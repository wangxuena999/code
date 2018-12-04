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
<title>文章新增</title>
<!-- 添加表单验证 -->
<script type="text/javascript">
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
	<div class="rpos">当前位置: 文章表 - 添加</div>
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
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>标题:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="title" id="title" maxlength="100" size="50" onblur="chktitle()"/>
						<span id="resultTitle"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>内容:</td>
					<td width="80%" class="pn-fcontent">
						<textarea rows="15" cols="120" id="content" name="content" onblur="chkcontent()"></textarea>
						<span id="resultContent"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>作者:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="author" id="author" maxlength="100" size="50" onblur="chkauthor()"/>
						<span id="resultAuthor"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">所属栏目:</td>
					<td width="80%" class="pn-fcontent">
						<select id="c1" name="c1">
							<c:forEach items="${CLIST1 }" var="c1">
								<option value="${c1.id }" name="id">${c1.cname }</option>
							</c:forEach>
						</select>
						<select id="c2" name="channel.id">
						    <c:forEach items="${CLIST2 }" var="c2">
								<option value="${c2.id }" name="id">${c2.cname }</option>
							</c:forEach>
						</select>
								
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">是否推荐:</td>
					<td width="80%" class="pn-fcontent">
						<input type="radio" name="isremod" value="1" checked="checked" />推荐
						<input type="radio" name="isremod" value="-1" />不推荐
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">是否热点:</td>
					<td width="80%" class="pn-fcontent">
						<input type="radio" name="ishot" value="1" checked="checked" />热点
						<input type="radio" name="ishot" value="-1" />非热点
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