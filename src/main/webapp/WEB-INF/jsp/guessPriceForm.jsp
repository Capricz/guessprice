<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Guess The Price</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery.easyui.min.js"></script>
	<style type="text/css">
	.panel{margin:0 auto !important}
	</style>
</head>
<body>
	<h2>Hot 5 Product</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>Guess the Price and submit it.</div>
	</div>
	<div style="margin:10px 0;"></div>
	<div class="easyui-panel" title="Product" style="width:400px;margin:0 auto">
		<div style="padding:10px 0 10px 60px">
	    <form id="ff" method="post">
	    <input id="pid" type="hidden" name="productId" value="${productId}">
	    <table>
	    		<tr>
	    			<td>Product Name:</td>
	    			<td>${productDto.productName}<!-- <input class="easyui-validatebox" type="text" name="name" data-options="required:true"> --></input></td>
	    		</tr>
	    		<tr>
	    			<td>Product Description:</td>
	    			<td>${productDto.productDesc}<!-- <input class="easyui-validatebox" type="text" name="email" data-options="required:true,validType:'email'"> --></input></td>
	    		</tr>
	    		<tr>
	    			<td>Product Line:</td>
	    			<td>${productDto.productLine}<!-- <input class="easyui-validatebox" type="text" name="subject" data-options="required:true"/> --></td>
	    		</tr>
	    		<tr>
	    			<td>Price :</td>
	    			<td><input id="pri" class="easyui-validatebox" type="text" name="price" data-options="required:true"/></td>
	    		</tr>
	    		<!-- <tr>
	    			<td>Message:</td>
	    			<td><textarea name="message" style="height:60px;"></textarea></td>
	    		</tr> -->
	    		<!-- <tr>
	    			<td>Language:</td>
	    			<td>
	    				<select class="easyui-combobox" name="language"><option value="ar">Arabic</option><option value="bg">Bulgarian</option><option value="ca">Catalan</option><option value="zh-cht">Chinese Traditional</option><option value="cs">Czech</option><option value="da">Danish</option><option value="nl">Dutch</option><option value="en" selected="selected">English</option><option value="et">Estonian</option><option value="fi">Finnish</option><option value="fr">French</option><option value="de">German</option><option value="el">Greek</option><option value="ht">Haitian Creole</option><option value="he">Hebrew</option><option value="hi">Hindi</option><option value="mww">Hmong Daw</option><option value="hu">Hungarian</option><option value="id">Indonesian</option><option value="it">Italian</option><option value="ja">Japanese</option><option value="ko">Korean</option><option value="lv">Latvian</option><option value="lt">Lithuanian</option><option value="no">Norwegian</option><option value="fa">Persian</option><option value="pl">Polish</option><option value="pt">Portuguese</option><option value="ro">Romanian</option><option value="ru">Russian</option><option value="sk">Slovak</option><option value="sl">Slovenian</option><option value="es">Spanish</option><option value="sv">Swedish</option><option value="th">Thai</option><option value="tr">Turkish</option><option value="uk">Ukrainian</option><option value="vi">Vietnamese</option></select>
	    			</td>
	    		</tr> -->
	    	</table>
	    </form>
	    </div>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitPrice()">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
	    </div>
	</div>
	<script>
		function submitPrice(){
			var productId = $("#pid").val();
			var price = $("#pri").val();
     		if(productId=="" || price==""){
     			/*
     			$("#showMsg").html("Username or Password can not be blank");
         		$("input[name='username']").focus();
     			*/
     			alert('price is missing!');
    		}else{
    				var dataObject =  {"productId" : productId, "price" : price};
           			$.ajax({            
                  		type:"POST", 
                  		url:"/guessprice/pricesetup/setPrice", 
                  		datatype:"json",
                  		contentType:"application/json;charset=UTF-8",               
            			data:JSON.stringify(dataObject),                 
                  		success:function(data) {
                  			 document.location = "index";
                      	}
                     });       
        	} 
       }
	
		function submitForm(){
			$('#ff').form('submit');
			var dataObject =  {"username" : username, "password" : password};
   			$.ajax({            
          		type:"POST", 
          		url:"login", 
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
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>