
$(function(){
	
	var $pageid = $('#adminbar');
	
	if($pageid.length>0){
	
		var mail = $('#inputmail'),
			pwd = $('#inputmail'),
			
			data = {mail:mail,password:pwd};
			
	
		
		identity.insertUser(data, function(data){
			$pageid
		});
	
	}	
	
});