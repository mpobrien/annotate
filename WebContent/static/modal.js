$(document).ready(
	function(){
		$('a[name=modal]').live('click',
			function(e) {  
                console.debug('yo')
				e.preventDefault();  
				var id = $(this).attr('href');  
				var maskHeight = $(document).height();  
				var maskWidth = $(window).width();  
				$('#mask').css({'width':maskWidth,'height':maskHeight});  
				$('#mask').show();      
				$('#mask').css("opacity","0.8");    
				var winH = $(window).height();  
				var winW = $(window).width();  
                console.debug(id)
				$(id).css('top',  winH/2-$(id).height()/2);  
				$(id).css('left', winW/2-$(id).width()/2);  
				$(id).show();   
			});  

		$('.close').live('click',
			function(e){
				e.preventDefault();
				$('#mask, .modal').hide();
			}
		);
	}
)
