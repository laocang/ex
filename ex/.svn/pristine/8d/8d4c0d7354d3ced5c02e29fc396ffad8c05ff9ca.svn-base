<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
 <html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"> 
<meta content="yes" name="apple-mobile-web-app-capable"> 
<meta content="yes" name="apple-touch-fullscreen">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="320" name="MobileOptimized">
<meta content="telephone=no" name="format-detection">
<link rel="stylesheet" href="<%=basePath %>css/watchshop/qdwap.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/watchshop/style.css">
<LINK href="favicon.ico" type="image/x-icon" rel=icon>
<title>XX官方旗舰店</title>
<meta name="keywords" content="XX官方旗舰店">
<meta name="description" content="XX官方旗舰店">
<script src="<%=basePath %>js/weui/jquery-2.1.4.js"></script>
<script src="<%=basePath %>js/watchshop/time.lesser.js"></script>
<script src="<%=basePath %>js/watchshop/demo.js"></script>
<script src="<%=basePath %>js/watchshop/setcookie.js"></script>
<script type="text/javascript">

	function submitorder(){
		var product = $("#product").val();
		var name = $("#name").val();
		var tel = $("#tel").val();
		var privince = $("#privince").val();
		var city = $("#city").val();
		var area = $("#area").val();
		var addr = $("#addr").val();
		var price = $("#price").val();
		var payway = '货到付款';
		var beizhu = $("#beizhu").val();
		if(product==null||product==''){
			alert("产品名字不能为空");
			return;
		}
		if(name==null||name==''){
			alert("名字不能为空");
			return;
		}
		if(tel==null||tel==''){
			alert("电话号码不能为空");
			return;
		}
		if(privince==null||privince==''){
			alert("省份不能为空");
			return;
		}
		if(city==null||city==''){
			alert("城市不能为空");
			return;
		}
		if(area==null||area==''){
			alert("区域不能为空");
			return;
		}
		if(addr==null||addr==''){
			alert("详细地址不能为空");
			return;
		}
		if(payway==null||payway==''){
			alert("付款方式不能为空");
			return;
		}
		var data={
			product:product,
			name:name,
			tel:tel,
			privince:privince,
			city:city,
			area:area,
			addr:addr,
			price:price,
			payway:payway,
			beizhu:beizhu
		}
		$.post("../addOrder",data,function(result){
			if(result==1){
				alert('订单提交成功!');
			}else{
				alert('订单提交失败');
			}
		});			
	}
</script>
  </head>
   
  <body>
  <div style="text-align:center;">
		<img class="lazy-img" src="<%=basePath %>images/watchshop/1.gif" width="50%"/>
		<img class="lazy-img" src="<%=basePath %>images/watchshop/1.gif" width="50%"/>
		<img class="lazy-img" src="<%=basePath %>images/watchshop/1.gif" width="50%"/>

		</div>
  <div id="order" style="">
    <div class="warp">
        <form id="form" name="form" method="post">
        <input type="hidden" name="orderid" id="orderid" value="">
		<input id="action" name="action" type="hidden" value="payorder" />
		<input id="webFromURL" name="webFromURL" type="hidden" value="" />
		<input id="orderurl" name="orderurl" type="hidden" value="" />
		<input id="uid" name="uid" type="hidden" value="" />
		<input id="p" name="p" type="hidden" value="" />
		<input id="t" name="t" type="hidden" value="" />
		<input id="creative" name="creative" type="hidden" value="" />
		<input id="media" name="media" type="hidden" value="" />
		<input id="group" name="group" type="hidden" value="" />
		
		
		<script type="text/javascript"> 
		//js获取cookie
		var acookie=document.cookie.split("; ");
		function getck(sname)
		{
			//获取单个cookies
			for(var i=0;i<acookie.length;i++)
			{
				var arr=acookie[i].split("=");
				if(sname==arr[0])
				{
					if(arr.length>1)
						return unescape(arr[1]);
					else
						return "";
				}
			}
			return "";
		}
		//alert(getck("uid"));
		//给相应的form里的input赋值
		document.form.webFromURL.value=getck("webFromURL");
		document.form.orderurl.value=getck("orderurl");
		document.form.uid.value=getck("uid");
		document.form.p.value=getck("p");
		document.form.t.value=getck("t");
		document.form.creative.value=getck("creative");
		document.form.media.value=getck("media");
		document.form.group.value=getck("group");
		//alert(document.form.t.value);
		</script>

<!--附加属性b-->
		<br/>
		<br/>
		 <div class="bdbox">
			<label class="bdxx"><em>*</em>产品</label>
			<div class="dxbox red">
			<label><input type="radio" name="product" id="product" value="手表198" alt="198" checked>&nbsp;&nbsp;手表 198元</label><br>
			</div>
		</div>
 
<!--附加属性e-->
        <div class="bdbox">
            <label class="bdxx"><em>*</em>姓名</label>
            <div class="textbox">
                <input type="text" id="name" name="name">
            </div>
        </div>
        <div class="bdbox">
            <label class="bdxx"><em>*</em>手机</label>
            <div class="textbox">
                <input type="number" id="tel" name="mob">
            </div>
        </div>
        <div class="bdbox">
            <label class="bdxx"><em>*</em>地区</label>
            <div class="xlbox">
                <select name="province" id="privince" class="dqxl"></select><select name="city" id="city" class="dqxl"><option value="">选城市</option></select><select name="area" id="area" class="dqxl"><option value="">选地区</option></select>
            </div>
        </div>
        <div class="bdbox">
            <label class="bdxx"><em>*</em>地址</label>
            <div class="textbox">
                <input type="text" id="addr" name="address">
            </div>
        </div>
        <div class="bdbox">
            <label class="bdxx"><em>*</em>金额</label>
            <div class="text3box">
                <input type="hidden" name="zfbprice"  id="price" value="198" />
                <input name="price" value="198" readonly  style="width:80px;"> 元 <span id="zfbyh"></span>
            </div>
        </div>
        <div class="bdbox">
            <label class="bdxx"><em>*</em>付款</label>
            <div class="dxbox">
                <input type="radio" checked="checked" name="pay" id="payway" value="cod" onClick="return changeItem(0);" value="货到付款"><label for="b1"><img src="<%=basePath %>images/watchshop/hdfk.gif"></label>
                
             
            </div>
        </div>

        <div class="bdbox">
            <label class="bdxx">留言</label>
            <div class="text2box">
                <textarea id="beizhu" name="guest"></textarea>
            </div>
        </div>
          
        <div class="subbox"  onclick="submitorder()">
            <input name="submit" value="立即提交订单">
        </div>
        </form>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <div style="clear:both;"></div>
    </div>
</div>
<script type="text/javascript" src="<%=basePath %>js/watchshop/diqu.js"></script>
<script type="text/javascript" src="<%=basePath %>js/watchshop/not3.js"></script>
<!--订单-->
  </article><!--   
   <footer>
	<p><center><img src="https://img.alicdn.com/imgextra/i1/1699978161/TB27E0NeSiK.eBjSZFsXXbxZpXa_!!1699978161.png"></center></p>
	<p><center>东莞市鼎辰表业有限公司</center></p>
   </footer>
-->
</div><!--/page-->
 <br><br>
<div class="bottom" style="height: 70px;">
<div class="bottom1">
  <div class="bottom_l">
    <div class="bottom_la">
      <div class="djs" style="width:200px">
					<div class="time-count"><!--<img src="">--><font>XX旗舰店</font>
					</div>
				</div></div>
  </div>
  <a href="javascript:;" onClick="location.href='#order'" class="bottom_r">立即抢购</a>
</div>
<div class="bottom2">
  <a href="#">首页</a>
  <span>|</span>
  <a href="tel:15259972727">电话咨询</a>
  <span>|</span>
  <a href="javascript:;" onClick=""><strong>用户评价</strong></a>
  <span>|</span>
  <a href="sms:15259972727">短信订购</a>
</div>
</div>
<script src="<%=basePath %>js/watchshop/jquery.countdown.min.js"></script>
<script src="<%=basePath %>js/watchshop/jquery.countUp.min.js"></script>
    
  </body>
</html>