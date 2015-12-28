
jQuery(document).ready(function(){
	jQuery('#parallax').jparallax({});
	getTagsCloudy('Label12',10,'cloud-percent',true,{
		'border':'none 0',
		'padding':'2px 2px 1px 1px',
		'margin':'0 0 0 0',
		'list-style-type':'none',
		'float':'left',
		'display':'inline',
		'white-space':'nowrap'
	});
	// Menu Start
	$("#topnav li").prepend("<span></span>"); //Throws an empty span tag right before the a tag
	$("#topnav li").each(function() { //For each list item...
		var linkText = $(this).find("a").html(); //Find the text inside of the <a> tag
		$(this).find("span").show().html(linkText); //Add the text in the <span> tag
	}); 
	$("#topnav li").hover(function() {	//On hover...
		$(this).find("span").stop().animate({
			marginTop: "-40" //Find the <span> tag and move it up 40 pixels
		}, 250);
	} , function() { //On hover out...
		$(this).find("span").stop().animate({
			marginTop: "0"  //Move the <span> back to its original state (0px)
		}, 250);
	});
	// Menu End
});


// Tags Clouds
function getTagsCloudy(placeHolderID, scale, percentClassPrefix, keepFreq, overwriteCSS) {
	var list = $('#'+placeHolderID+' li');
	// overwrite CSS
	list.css(overwriteCSS);
	var max=0, min=0;
	//First pass : calc max and min
	list.each(function (i) {
		//extract and parse frequency
		freq = getTagFrequency($(this));
		//Calc max and min
		if (freq>max) max = freq;
		if (freq<min || min==0) min = freq;
	});
	//Second pass : calc percent
	list.each(function (i) {
		//extract and parse frequency
		freq = getTagFrequency($(this));
		// Remove tags frequency ?
		if (!keepFreq) $(this).html($(this).html().replace(/\n/g,'').replace(/\(\s*\d+\s*\)/g, '') );
		//Calc percentage
		var percent = Math.ceil( ((freq-min+1)/(max-min+1)) * scale );
		$(this).attr('class',percentClassPrefix+percent);
	});
}
function getTagFrequency(x) {
	var matchs= x.text().replace(/\n/g,'').match(/\s*\d+\s*/g);
	if (!matchs) return 0;
	var freq = parseInt(matchs[matchs.length -1]);
	return freq>0? freq : 0;
}