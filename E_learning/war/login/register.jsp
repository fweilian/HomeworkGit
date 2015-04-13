<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="error.jsp" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="/css/home.css">
<title>Insert title here</title>
</head>

<script type="text/javascript">
	function checkform(nickname, email, password, affirm_password) {
		if (nickname.value == "" || !(nickname.value).indexOf("/")
				|| !(nickname.value).indexOf(".")) {
			alert("the username is null or has illegal character! ");
			return false;
		} else if (password.value == "" || (password.value).length < 6) {
			alert("the password can not null and the length need exceed six! ");
			return false;
		} else if (affirm_password.value == ""
				|| (affirm_password.value) != (password.value)) {
			alert("the affirm password should be same with the password! ");
			return false;
		} else if (email.value == "") {
			alert("the email can not null! ");
			return false;
		} else {
			return true;
		}
	}
</script>

<body>
	<form action="/login/handleRegister.jsp" method="post">
		<div class="reg_item">
			<input type="text" value="" name="nickname" id="nickname"
				maxlength="60" placeholder="username" autocomplete="off" autofocus />
		</div>
		<div class="reg_item">
			<input type="text" vlaue="" name="email" id="email" maxlength="120"
				placeholder="email" autocomplete="off" />
		</div>
		<div class="reg_item">
			<input type="password" value="" name="password" id="password"
				maxlength="20" placeholder="password" autocomplete="off" />
		</div>
		<div class="reg_item">
			<input type="password" value"" name="affirm_password"
				id="affirm_password" maxlength="20" placeholder="affirm password"
				autocomplete="off" />
		</div>
		<div class="reg_item_submit">
			<input type="submit" vlaue="Register" />
		</div>
	</form>
</body>
</html>