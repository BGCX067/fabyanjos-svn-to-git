A4J.AJAX.onError = function(req,status,message) {
	//alert("Error: "+req+":"+status+":"+":"+message);
	window.location = 'sphera/app/home.htm';
};
A4J.AJAX.onExpired = function(loc,expiredMsg){
	alert(loc +": " +expiredMsg);
	window.location = '/app/login/login.htm';
};
function showLoad(){	
	$('pnlLoad').style.display='';
}
function hideLoad(){
	$('pnlLoad').style.display='none';
}
function enlarge(who){
	who.width=who.width*1.1;
	who.height=who.height*1.1;
}
function decrease(who){
	who.width=who.width*0.9;
	who.height=who.height*0.9;
}
function setSize(who,_width,_heigth){
	who.width=_width;
	who.height=_heigth;
}