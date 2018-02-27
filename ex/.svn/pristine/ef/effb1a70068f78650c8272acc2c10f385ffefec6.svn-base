<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <html>
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title></title>
  <style type="text/css">
     body{background-color:#000;}
     *{padding:0px;margin:0px;}
	 #perspective{perspective: 800px;}
	 #wrap{width:120px;height:180px;position:relative;top:0px;left:0px;margin:50px auto;transform-style:preserve-3D;transform:rotateX(-10deg) }
	 #wrap img{width:100%;height:100%;position:absolute;top:0px;left:0px;border-radius:6px;box-shadow:0px 0px 15px #fcf;-webkit-box-reflect:below 6px  -webkit-gradient(linear, left top, left bottom, from(transparent), color-stop(0.5, transparent), to(white));cursor:pointer;}
	 #wrap p.dibu{width:1200px;height:1200px;background:-webkit-radial-gradient(center center,600px 600px,rgba(242,79,242,0.4),rgba(0,0,0,0));transform:rotateX(90deg);position:absolute;top:100%;left:50%;margin-top:-600px;margin-left:-600px;text-align:center;color:rgba(0,0,0,.7);line-height:1200px;}
  	 #wrap p.dibu{ width:1200px; height:1200px; position:absolute; border-radius:100%;
				left:50%; top:100%; margin-left:-600px; margin-top:-600px;
				background:-webkit-radial-gradient(center center,600px 600px,rgba(50,50,50,1),rgba(0,0,0,0));
				transform:rotateX(90deg);text-align:center;color:rgba(0,0,0,.7);line-height:1200px;font-size:28px;}
		
  </style>
 </head>
 <body>
      <div id="perspective"> 
		<div id="wrap">
		   <img onmouseover="bigImg(this)" src="<%=basePath %>resources/images/love/1.jpg"/>
		   <img src="<%=basePath %>resources/images/love/2.jpg"/>
		   <img src="<%=basePath %>resources/images/love/3.jpg"/>
		   <img src="<%=basePath %>resources/images/love/4.jpg"/>
		   <img src="<%=basePath %>resources/images/love/5.jpg"/>
		   <img src="<%=basePath %>resources/images/love/6.jpg"/>
		   <img src="<%=basePath %>resources/images/love/7.jpg"/>
		   <img src="<%=basePath %>resources/images/love/8.jpg"/>
		   <img src="<%=basePath %>resources/images/love/9.jpg"/>
		   <img src="<%=basePath %>resources/images/love/10.jpg"/>
		   <img src="<%=basePath %>resources/images/love/11.jpg"/>
		   <p class="dibu">海上月是天上月，眼前人是心上人</p>
		</div>
      </div>
	<script type="text/javascript">
	   	
	 
	    window.onload =function(){

		       //querySelector 找到一个后就返回节点对象   querySelectorAll 找到所有匹配的节点并返回所有匹配的元素
				var wrapDom = document.querySelector("#wrap");
				var imgDom = document.getElementsByTagName("img");
				var len = imgDom.length;
				var imgDeg = 360/len;
				for(var i=0;i<len;i=i+1){
					 
					  imgDom[i].style.transform= "rotateY("+i*imgDeg+"deg) translateZ(350px)";
					  imgDom[i].style.transition ="transform 1s "+(len-1-i)*0.8+"s";
				}
				
				var lastx,lasty,nowx,nowy,minusx,minusy,roX=-10;roY=0;
				var timer = null;
				put_center();
				window.onresize =put_center;
				function put_center(){

				   var wh = window.innerHeight/2-wrapDom.offsetHeight;
				   wrapDom.style.cssText = "margin-top:"+wh+"px";

				}
				document.onmousedown = function(e){
				   
					 var ev = e||window.event;
					 lastx = ev.clientX;
					 lasty = ev.clientY;
					 this.onmousemove = function(e){
					   
					   clearInterval(timer)
					   var ev = e||window.event;
					   var nowx = ev.clientX;
					   var nowy = ev.clientY;
					   minusx=nowx-lastx;
					   minusy=nowy-lasty;
					   roY+=minusx*0.2;
					   roX-=minusy*0.2;
					  
					   wrapDom.style.transform="rotateX("+roX+"deg) rotateY("+roY+"deg)";
					   lastx=nowx;
					   lasty=nowy;
					   //var newDom = document.createElement("div");
					  // newDom.style.cssText ="position:fixed;width:10px;height:10px;background:yellow;top:"+nowy+"px;left:"+nowx+"px";
					  // this.body.appendChild(newDom);
						
					 }
					 this.onmouseup = function(){
						
						this.onmousemove = null;
						timer=setInterval(function(){
							  
							   minusx*=0.95;
							   minusy*=0.95;
							   roY+=minusx*0.2;
							   roX-=minusy*0.2;
							   if(Math.abs(roY)<0.5||Math.abs(roX)<0.5) clearInterval(timer);
							   wrapDom.style.transform="rotateX("+roX+"deg) rotateY("+roY+"deg)";
						   
						 },30)
					 }
					 return false;
				}
		}
	</script>
 </body>
</html>