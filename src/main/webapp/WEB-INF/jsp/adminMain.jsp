<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Demo</title>
<link rel="stylesheet" type="text/css" href="themes/metro/easyui.css" id="swicth-style">
<script type="text/javascript" src="js/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.easyui.min.js"></script>
<style type="text/css">
body {
	font: 12px/20px "微软雅黑", "宋体", Arial, sans-serif, Verdana, Tahoma;
	padding: 0;
	margin: 0;
}
.layout-split-proxy-h{
	position:absolute;
	width:2px;
	background:#888;
	font-size:1px;
	cursor:e-resize;
	display:none;
	z-index:5;
}

.layout-split-north{
	border-bottom:5px solid #efefef;
}
.layout-split-south{
	border-top:5px solid #efefef;
}
.layout-split-east{
	border-left:0px solid #efefef;
}
.layout-split-west{
	border-right:0px solid #efefef;
}
a:link {
 text-decoration: none;
}
a:visited {
 text-decoration: none;
}
a:hover {
 text-decoration: underline;
}
a:active {
 text-decoration: none;
}
.cs-north {
	height:60px;
}
.cs-north-bg {
	width: 100%;
	height: 100%;
	background: url(themes/gray/images/header_bg.png) repeat-x;
}
.cs-north-logo {
	height: 40px;
	margin: 15px 0px 0px 5px;
	display: inline-block;
	color:#000000;font-size:22px;font-weight:bold;text-decoration:none
}
.cs-west {
	width:200px;padding:0px;
}
.cs-navi-tab {
	padding: 5px;
}
.cs-tab-menu {
	width:120px;
}
.cs-home-remark {
	padding: 10px;
}
.wrapper {
    float: right;
    height: 30px;
    margin-left: 10px;
}
.ui-skin-nav {
    float: right;
	padding: 0;
	margin-right: 10px;
	list-style: none outside none;
	height: 30px;
}

.ui-skin-nav .li-skinitem {
    float: left;
    font-size: 12px;
    line-height: 30px;
	margin-left: 10px;
    text-align: center;
}
.ui-skin-nav .li-skinitem span {
	cursor: pointer;
	width:10px;
	height:10px;
	display:inline-block;
}
.ui-skin-nav .li-skinitem span.cs-skin-on{
	border: 1px solid #FFFFFF;
}

.ui-skin-nav .li-skinitem span.gray{background-color:gray;}
.ui-skin-nav .li-skinitem span.default{background-color:blue;}
.ui-skin-nav .li-skinitem span.bootstrap{background-color:#D7EBF9;}
.ui-skin-nav .li-skinitem span.black{background-color:black;}
.ui-skin-nav .li-skinitem span.metro{background-color:#FFE57E;}
</style>
<script type="text/javascript">
function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);//选中并刷新
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	} else {
		var content = createFrame(url);
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	tabClose();
}
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}
		
function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}		
//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != 'Home') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != 'Home') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

$(function() {
	tabCloseEven();

	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});

	var themes = {
		'gray' : 'themes/gray/easyui.css',
		'black' : 'themes/black/easyui.css',
		'bootstrap' : 'themes/bootstrap/easyui.css',
		'default' : 'themes/default/easyui.css',
		'metro' : 'themes/metro/easyui.css'
	};

	var skins = $('.li-skinitem span').click(function() {
		var $this = $(this);
		if($this.hasClass('cs-skin-on')) return;
		skins.removeClass('cs-skin-on');
		$this.addClass('cs-skin-on');
		var skin = $this.attr('rel');
		$('#swicth-style').attr('href', themes[skin]);
		setCookie('cs-skin', skin);
		skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
	});

	if(getCookie('cs-skin')) {
		var skin = getCookie('cs-skin');
		$('#swicth-style').attr('href', themes[skin]);
		$this = $('.li-skinitem span[rel='+skin+']');
		$this.addClass('cs-skin-on');
		skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
	}
});


function setCookie(name,value) {//两个参数，一个是cookie的名子，一个是值
    var Days = 30; //此 cookie 将被保存 30 天
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {//取cookies函数        
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;
}
</script>
</head>
<body class="easyui-layout">
	<div region="north" border="true" class="cs-north">
		<div class="cs-north-bg">
		<div class="cs-north-logo">Admin</div>
		<ul class="ui-skin-nav">				
			<li class="li-skinitem" title="gray"><span class="gray" rel="gray"></span></li>
			<li class="li-skinitem" title="default"><span class="default" rel="default"></span></li>
			<li class="li-skinitem" title="bootstrap"><span class="bootstrap" rel="bootstrap"></span></li>
			<li class="li-skinitem" title="black"><span class="black" rel="black"></span></li>
			<li class="li-skinitem" title="metro"><span class="metro" rel="metro"></span></li>
		</ul>	
		</div>
	</div>
	<div region="west" border="true" split="true" title="Navigation" class="cs-west">
		<div class="easyui-accordion" fit="true" border="false">
				<div title="Hot 5 Product">
					<a href="javascript:void(0);" src=<%=request.getContextPath() %>"/getGuessPriceFormData/2" class="cs-navi-tab">Proudct 1</a></p>
					<a href="javascript:void(0);" src="getGuessPriceFormData2222" class="cs-navi-tab">Proudct 22222</a></p>
					<a href="javascript:void(0);" src="getGuessPriceFormData" class="cs-navi-tab">Proudct 3</a></p>
					<a href="javascript:void(0);" src="getGuessPriceFormData" class="cs-navi-tab">Proudct 4</a></p>
					<a href="javascript:void(0);" src="getGuessPriceFormData" class="cs-navi-tab">Proudct 5</a></p>
				</div>
				<div title="Report And Charts">
					<a href="javascript:void(0);" src="getPieReport" class="cs-navi-tab">User Activity Chart</a></p>
					<a href="javascript:void(0);" src="getPieReport2" class="cs-navi-tab">PL Chart</a></p>
					<a href="javascript:void(0);" src="getColumnReport1" class="cs-navi-tab">Product Chart1</a></p>
					<a href="javascript:void(0);" src="getColumnReport2" class="cs-navi-tab">Product Chart2</a></p>
					<a href="javascript:void(0);" src="getColumnReport3" class="cs-navi-tab">Product Chart3</a></p>
					<a href="javascript:void(0);" src="getColumnReport4" class="cs-navi-tab">Product Chart4</a></p>
					<a href="javascript:void(0);" src="getColumnReport5" class="cs-navi-tab">Product Chart5</a></p>
				</div>
		</div>
	</div>
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
<!--                 <div title="Home"> -->
<!-- 				<div class="cs-home-remark"> -->
<!-- 					<h1>Let's Guess The Price Now</h1> -->
<!-- 				</div> -->
<!-- 				</div> -->
        </div>
	</div>

<!-- 	<div region="south" border="false" id="south"><center>@system.mpo@hp.com</center></div> -->
	
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">Refresh</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">Close</div>
		<div id="mm-tabcloseother">Close Other</div>
		<div id="mm-tabcloseall">Close All</div>
	</div>
</body>
</html>
