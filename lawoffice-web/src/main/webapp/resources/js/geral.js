jQuery(function(){ 
	jQuery("ul.sf-menu").supersubs({ 
        minWidth:    12,    
        maxWidth:    20,    
        extraWidth:  1      
                            
    }).superfish({delay:0});
}); 


jQuery(function(){
	jQuery('ul.sf-menu > li').hover(
		function (){
			jQuery(this)
			.children('a')
			.css('color', '#FFFFFF');
		},
		function (){
			jQuery(this)
			.children('a')
			.css('color', '#1B5790');
		}		
	);
});




function showHideNotificationBar() {

	var text = jQuery("#msg").text();
	
	if(text != "")
		nofificationBar.show();
	else
		nofificationBar.hide();	
}

