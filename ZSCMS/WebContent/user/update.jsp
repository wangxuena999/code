<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入标签库 -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="res/lecheng/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- 引时间控件 -->
<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script type="text/javascript" src="res/fckeditor/fckeditor.js"></script>
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/lecheng.js" type="text/javascript"></script>
<script src="res/lecheng/js/admin.js" type="text/javascript"></script>

<!-- 引入jQuery -->
<script type="text/javascript" language="javascript" src="js/jquery-1.11.0.min.js"></script>
<!-- 引入日期控件 -->
<script type="text/javascript" language="javascript" src="js/DatePicker/WdatePicker.js"></script>

<link rel="stylesheet" href="../res/css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户修改</title>
<!-- 添加表单验证 -->
<script type="text/javascript">
//用户名：数字+字母，结束之前不能全部都是数字，6-16
var CHKLOGINNAME="^(?![0-9]+$)[a-zA-Z0-9]{6,16}$";
//出生日期     yyyy-MM-dd  月份1-12     日期1-31
var CHKDATE="^[0-9]{4}-0?[1-9]|1[0-2]-0?[1-9]|[1-2][0-9]|3[0-1]$";
//邮箱xxxxxx@xxx.com,可以包含_      企业邮箱qwe@huewei.com.cn
var CHKEMAIL="^[a-zA-Z0-9_]+@[a-z0-9]{2,5}(\\.[a-z]{2,3}){1,2}$";
//真实姓名（至少两个汉字）
var CHKREALNAME="^[\u4e00-\u9fa5]{2,}$";

//验证用户名
function chkloginname(){
	//获取元素对象的属性值，获取用户名
	var loginname=$("#loginname").val();
	//定义匹配用户名的正则表达式
	var reg=new RegExp(CHKLOGINNAME);
	//判断输入的用户名是否匹配规定好的正则表达式
	if(reg.test(loginname)){
		if(chkExistLoginname(loginname)){
			return true;
		}else{
			return false;
		}
	}else{
		//给用户名是否输入成功的元素对象文本内容赋值
		$("#resultName").html("用户名必须包含数字和字母，并且不能低于六位");
		//给用户名是否输入成功的元素对象颜色赋值
		$("#resultName").css("color","red");
		//清空文本框
		$("#loginname").val("");
		//重新聚焦
		$("#loginname").focus();
		//给方法一个返回值为false，用于使用onBlur()失焦方法判断
		return false;
	}
}

//检查用户名是否重复
function chkExistLoginname(loginname){
	//定义一个对象，表示是重复的
	var flag=false;
	$.ajax({
		//请求路径
		url:'chkuser.do',
		//请求方式
		type:'post',
		//请求参数
		data:'type=1&loginname='+loginname,
		//是否异步
		async:false,
		//预期服务器返回的数据类型
		dataType:'text',
		//响应成功调用回调函数
		success:function(flag){
			if(flag=='true'){//没有重复
				//给用户名是否输入成功的元素对象文本内容赋值
				$("#resultName").html("✔");
				//给用户名是否输入成功的元素对象颜色赋值
				$("#resultName").css("color","green");
				flag=true;
			}else{
				$("#resultName").html("此用户名已存在");
				$("#resultName").css("color","red");
				//清空文本框
				$("#loginname").val("");
				//重新聚焦
				$("#loginname").focus();
				flag=false;
			}
		},
		error:function(){
			alert('请求数据失败。。。');
		}
	});
	return flag;
}

//检查邮箱是否唯一
function chkExistEmail(email){
	var flag=false;
	$.ajax({
		//请求路径
		url:'chkuser.do',
		//请求方式
		type:'post',
		//请求参数
		data:'type=2&email='+email,
		//是否异步
		async:false,
		//预期服务器返回的数据类型
		dataType:'text',
		//响应成功调用回调函数
		success:function(flag){
			if(flag=='true'){//没有重复
				$("#resultEmail").html("✔");
				$("#resultEmail").css("color","green");
				flag=true;
			}else{
				//否则新增失败，显示邮箱已存在
				$("#resultEmail").html("此邮箱已存在");
				$("#resultEmail").css("color","red");
				//清空文本框
				$("#email").val("");
				//重新聚焦
				$("#email").focus();
				flag=false;
			}
		},
		error:function(){
			alert('请求数据失败。。。');
		}
	});
	return flag;
}

//验证真实姓名
function chkrealname(){
	//获取元素对象的属性值，获取真实姓名
	var realname=$("#realname").val();
	//定义匹配真实姓名的正则表达式
	var reg=new RegExp(CHKREALNAME);
	//判断输入的真实姓名是否匹配规定好的正则表达式
	if(reg.test(realname)){
		//给真实姓名是否输入成功的元素对象文本内容赋值
		$("#resultRealname").html("✔");
		//给真实姓名是否输入成功的元素对象颜色赋值
		$("#resultRealname").css("color","green");
		//给方法一个返回值为true，用于使用onBlur()失焦方法判断
		return true;
	}else{
		//给真实姓名是否输入成功的元素对象文本内容赋值
		$("#resultRealname").html("真实姓名必须使用汉字，至少两个字");
		//给真实姓名是否输入成功的元素对象颜色赋值
		$("#resultRealname").css("color","red");
		//清空文本框
		$("#realname").val("");
		//重新聚焦
		$("#realname").focus();
		//给方法一个返回值为false，用于使用onBlur()失焦方法判断
		return false;
	}
}


//验证邮箱
function chkemail(){
	//获取元素对象的属性值，获取邮箱
	var email=$("#email").val();
	//定义匹配邮箱的正则表达式
	var reg=new RegExp(CHKEMAIL);
	//判断输入的邮箱是否匹配规定好的正则表达式
	if(reg.test(email)){
		if(chkExistEmail(email)){
			return true;
		}else{
			return false;
		}
	}else{
		//给邮箱是否输入成功的元素对象文本内容赋值
		$("#resultEmail").html("邮箱格式不正确，请重新输入");
		//给邮箱是否输入成功的元素对象颜色赋值
		$("#resultEmail").css("color","red");
		//清空文本框
		$("#email").val("");
		//重新聚焦
		$("#email").focus();
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
<div>
	<img src="images/logo4.png"/>	
</div>
<div class="box-positon">
	<div class="rpos">当前位置: 用户表 - 修改</div>
	<form class="ropt">
		<input type="submit" onclick="this.form.action='userlist.do';" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>

<div class="body-box" style="float:right">
	<form id="jvForm" action="userupdate.do?id=${user.id }" method="post" >
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">${msg }</span>
			  		</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>用户名:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="loginname" id="loginname" maxlength="100" size="20" value="${user.loginname }" onblur="chkloginname()"/>
						<span id="resultName"></span>
					</td>
				</tr>
				
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>真实姓名:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="realname" id="realname" maxlength="100" size="20" value="${user.realname }" onblur="chkrealname()"/>
						<span id="resultRealname"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">性别:</td>
					<td width="80%" class="pn-fcontent">
						<c:if test="${user.sex=='男' }">
							<input type="radio" name="sex" value="1" checked="checked" />男
							<input type="radio" name="sex" value="2" />女
						</c:if>
						<c:if test="${user.sex=='女' }">
							<input type="radio" name="sex" value="1" />男
							<input type="radio" name="sex" value="2" checked="checked" />女
						</c:if>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">出生日期:</td>
						<td width="80%" class="pn-fcontent">
						<input type="text" class="Wdate" onclick="WdatePicker()" name="birthday" maxlength="100" size="20" value="${user.birthday }"/>
						</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">email:</td>
					<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="email" id="email" maxlength="100" size="20" value="${user.email }"  onblur="chkemail()"/>
						<span id="resultEmail"></span>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">部门名称:</td>
					<td width="80%" class="pn-fcontent">
							<select name="dept">
								<c:forEach items="${depts }" var="dept">
									<!-- 显示所属部门 -->
									<c:if test="${user.dept==dept.id }">
										<option value="${dept.id }" selected="selected">${dept.dname }</option>
									</c:if>
									<!-- 显示其他部门 -->
									<c:if test="${user.dept!=dept.id }">
										<option value="${dept.id }">${dept.dname }</option>
									</c:if>
										
								</c:forEach>
							</select>
								
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">是否可用:</td>				
					<td width="80%" class="pn-fcontent">
						<c:if test="${user.enabled==1 }">
							<input type="radio" name="enabled" value="1" checked="checked" />可用
							<input type="radio" name="enabled" value="0" />不可用
						</c:if>
						<c:if test="${user.enabled==0 }">
							<input type="radio" name="enabled" value="1"  />可用
							<input type="radio" name="enabled" value="0" checked="checked" />不可用
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