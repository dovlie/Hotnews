$(document).ready(function(){
	$("#sidebar ul li a").on("click",function(event){
		var href=$(this).attr("href");
		//alert(href);
		event.preventDefault();
		$("#maincontent").load(href);
	});
});