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
<title>用户修改</title>
<script type="text/javascript">
<!-- 添加表单验证 -->
$(function(){
		//下拉框change事件
		$("#dep1").change(
			function(){
				//清空第二个下拉框
				$("#dep2").empty();
				
				//ajax的异步提交
				$.post(
				"getdept.do",//1.url
				{
				  pid:this.value //2.json类型数据，传值
				},
				function(data){ //3.成功后执行
				 if(data!=null){
					$(data).each(
					  function(){
						  //添加数据到第二个下拉框中
						  $("#dep2").append("<option value="+this.id+">"+this.dname+"</option>");
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
	<div class="rpos">当前位置: 用户表 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='list.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form id="jvForm" action="update.do" method="post" onSubmit="return chkSubmit()" >
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">${ERROR }</span>
			  		</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>用户名:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="loginname" id="loginname" maxlength="100" size="20" value="${USER.loginname }" readonly="readonly"/>
						<span id="resultName"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>密码:</td>
					<td width="80%" class="pn-fcontent">
						<input type="password" class="required" name="password" id="password" maxlength="100" size="20"  value="${USER.password }" />
						<span id="resultPwd"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>确认密码:</td>
					<td width="80%" class="pn-fcontent">
						<input type="password" class="required" name="repwd" id="repwd" maxlength="100" size="20"  value="${USER.password }"/>
						<span id="resultRepwd"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>真实姓名:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="realname" id="realname" maxlength="100" size="20" value="${USER.realname }" onblur="chkrealname()"/>
						<span id="resultRealname"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">性别:</td>
					<td width="80%" class="pn-fcontent">
						<c:if test="${USER.sex=='男' }">
							<input type="radio" name="sex" value="男" checked="checked" />男
							<input type="radio" name="sex" value="女" />女
						</c:if>
						<c:if test="${USER.sex=='女' }">
							<input type="radio" name="sex" value="男" />男
							<input type="radio" name="sex" value="女" checked="checked" />女
						</c:if>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">出生日期:</td>
						<td width="80%" class="pn-fcontent">
						<input type="text" class="Wdate" onclick="WdatePicker()" name="birthday" maxlength="100" size="20" value="${USER.birthdayTxt }"/>
						</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">email:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="email" id="email" maxlength="100" size="20" value="${USER.email }"  onblur="chkemail()"/>
						<span id="resultEmail"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">部门名称:</td>
					<td width="80%" class="pn-fcontent">
							<select id="dep1" name="dep1">
								<c:forEach items="${DLIST1 }" var="dep1">
									<!-- 显示所属部门 -->
									<c:if test="${USER.dept.pid==dep1.id }">
										<option value="${dep1.id }" selected="selected">${dep1.dname }</option>
									</c:if>
									<!-- 显示其他部门 -->
									<c:if test="${USER.dept.pid!=dep1.id }">
										<option value="${dep1.id }">${dep1.dname }</option>
									</c:if>
										
								</c:forEach>
							</select>
							<select id="dep2" name="dept.id">
								<c:forEach items="${DLIST2}" var="dep2">
									<!-- 显示所属部门 -->
									<c:if test="${USER.dept.id==dep2.id }">
										<option value="${dep2.id }" selected="selected">${dep2.dname }</option>
									</c:if>
									<!-- 显示其他部门 -->
									<c:if test="${USER.dept.id!=dep2.id }">
										<option value="${dep2.id }">${dep2.dname }</option>
									</c:if>
										
								</c:forEach>
							</select>

					</td>
				</tr>
				<input type="hidden" name="id" value="${USER.id }" />
				<input type="hidden" name="isenabled" value="${USER.isenabled }" />
				
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