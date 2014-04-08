<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html style="height:100%;width:100%;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<script type="text/javascript" src="js/jquery/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.easyui.min.js"></script>
	<script>
		$(function(){
			//$('#w').window();
			$(document).ready(function() {
				$('#loginWin').window('open');
        		$('#registerWin').window('close');
			});
		});
		
		function login(){
			var username = $("input[name='loginname']").val();
			var password = $("input[name='loginpassword']").val();
     		if(password=="" || username==""){
     			$("#showMsg").html("Username or Password can not be blank");
         		$("input[name='username']").focus();
    		}else{
    				var dataObject =  {"username" : username, "password" : password};
           			$.ajax({            
                  		type:"POST", 
                  		url:"user/login", 
                  		datatype:"json",
                  		contentType:"application/json;charset=UTF-8",               
            			data:JSON.stringify(dataObject),                 
                  		success:function(data) {
                  			if(data.success == true) {
                  			 	if(data.userType == 0) {
                  			 		document.location = "admin";
                  			 	}
                  			 	else 
                  					document.location = "index";
                  			}
                  			else {
                  				$("#showMsg").html("Username or Password is wrong");
                  			}
                      	}
                     });       
        	} 
       }
  	   function changeRegisterWin() {
        	$('#loginWin').window('close');
        	$('#registerWin').window('open');
       }
       
       function register(){
			var username = $("input[name='registerusername']").val();
			var password = $("input[name='registerpassword']").val();
			var region = $("#region").combobox("getValue");
     		if(password=="" || username=="" || region == ""){
     			$("#showMsg2").html("Username or Password or Region can not be blank");
         		$("input[name='registerusername']").focus();
    		}else{
    				var dataObject =  {"username" : username, "password" : password, "region" : region};
           			$.ajax({            
                  		type:"POST", 
                  		url:"register", 
                  		datatype:"json",
                  		contentType:"application/json;charset=UTF-8",               
            			data:JSON.stringify(dataObject),                 
                  		success:function(data) {
                  			if(data.success == true) {
                  				document.location = "index";
                  			}
                  			else {
                  				$("#showMsg2").html(data.msg);
                  			}
                      	}
                     });       
        	} 
       }
</script>
</head>
<body style="height:100%;width:100%;overflow:hidden;border:none;" >
<!-- 	<h1>Window</h1> -->
<!-- 	<button onclick="javascript:$('#win').window('close')">open</button> -->
	
	<div id="loginWin" class="easyui-window" title="Login" style="width:350px;height:220px;">
		<form id="loginForm" style="padding:10px 20px 10px 50px;">
			<p>Username: <input type="text" name="loginname"></p>
			<p>Password: <input type="password" name="loginpassword"></p>
			<div style="padding:5px 0;text-align: center;color: red;" id="showMsg"></div>
			<div style="padding:5px;text-align:center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-ok" onclick="login()">Login</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-redo" onclick="changeRegisterWin()">Register</a>
			</div>
		</form>
	</div>
	
	<div id="registerWin" class="easyui-window" title="Register" style="width:350px;height:220px;">
	    <div style="padding:10px 0 10px 40px">
			<form id="registerForm">
				<table>
		    		<tr>
		    			<td>Username: </td>
		    			<td><input class="easyui-validatebox" type="text" name="registerusername" ></input></td>
		    		</tr>
		    		<tr>
		    			<td>Password:</td>
		    			<td><input class="easyui-validatebox" type="password" name="registerpassword"></input></td>
		    		</tr>
		    		<tr>
		    			<td>Region:</td>
		    			<td>
		    				<select id="region" class="easyui-combobox" name="region">
		    					<option value="AMS">AMS</option>
		    					<option value="EMEA">EMEA</option>
		    					<option value="APJ">APJ</option>
		    				</select>
		    			</td>
		    		</tr>
	    		</table>
		    </form>
		    <div style="padding:5px 0;text-align: center;color: red;" id="showMsg2"></div>
			<div style="padding:5px;text-align:center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-ok" onclick="register()">Register</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-no" onclick="reset()">Reset</a>
			</div>
		</div>
	</div>

</body>
</html>