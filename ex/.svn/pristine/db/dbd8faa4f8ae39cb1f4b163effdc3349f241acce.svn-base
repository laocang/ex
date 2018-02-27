function getdomain(str)
{  
    var st = str.indexOf("//",1);
   
    var _domain = str.substring(st+1, str.length) ;
    var et = _domain.indexOf("/",1);
    _domain = _domain.substring(1,et) ;
    return _domain
}

String.prototype.GetValue= function(para) { 
var reg = new RegExp("(^|&)"+ para +"=([^&]*)(&|$)"); 
var r = this.substr(this.indexOf("\?")+1).match(reg); 
if (r!=null) return unescape(r[2]); return null; 
}
//var loc = location.href;
var webFromURL= document.referrer;
var orderurl= location.href;
//var orderurl = document.referrer;
var uid= orderurl.GetValue("uid");
var t= orderurl.GetValue("t");
var p= orderurl.GetValue("p");
var creative= orderurl.GetValue("creative");
var media= orderurl.GetValue("media");
var group= orderurl.GetValue("group");


var Days = 30;
var exp  = new Date();
exp.setTime(exp.getTime() + Days*24*60*60*1000);
document.cookie = "webFromURL" + "="+ escape(webFromURL) + ";path=/;expires="+ exp.toGMTString();
document.cookie = "orderurl" + "="+ escape(orderurl) + ";path=/;expires="+ exp.toGMTString();


if(uid!="")
{
    var Days = 30;
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = "uid" + "="+ escape(uid) + ";path=/;expires="+ exp.toGMTString();
}

if(t!="")
{
    var Days = 30;
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = "t" + "="+ escape(t) + ";path=/;expires="+ exp.toGMTString();
}

if(p!="")
{
    var Days = 30;
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = "p" + "="+ escape(p) + ";path=/;expires="+ exp.toGMTString();
}

if(creative!="")
{
    var Days = 30;
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = "creative" + "="+ escape(creative) + ";path=/;expires="+ exp.toGMTString();
}

if(media!="")
{
    var Days = 30;
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = "media" + "="+ escape(media) + ";path=/;expires="+ exp.toGMTString();
}

if(group!="")
{
    var Days = 30;
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = "group" + "="+ escape(group) + ";path=/;expires="+ exp.toGMTString();
}