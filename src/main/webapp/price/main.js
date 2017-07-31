$(document).ready(function(){
	var priceNo = 0;
	
	function showlist(){
		$.getJSON("price/get/all.mvc",function(data){

			var showinfo="";
			for(var i=0;i<data.length;i++){
				showinfo=showinfo+"<tr id='"+data[i].pid+"'><td><input type='checkbox'></td><td>"+data[i].pid+"</td><td>"+data[i].price+"</td><td>"+data[i].ptype+"</td><td>"+data[i].prank+"</td><td>"+data[i].premarks+"</td></tr>";
			}
			$("table#pricelist tbody").html(showinfo);
			
			$("table#pricelist tbody tr").on("click",function(){
				priceNo = $(this).attr("id");
				$("table#pricelist tbody tr").css("background-color","#FFFFFF");
				$(this).css("background-color","#AAA");
			});
		})
	}
	//增加按钮点击
	$("a#priceaddlink").on("click",function(){
		$("div#pricedialog").load("price/add.html",function(){
			$("div#pricedialog").dialog({
				title: "增加价格",
				width:500,
				height:250
			});

			
			$("form#priceAddForm").validate({
				 rules: {
				        "pid": {
					        required: true
				        },
				        "price":{
				        	required: true
				        },
				        "ptype":{
				        	required: true
				        },
				        "prank":{
				        	required: true
				        }
				 }
			 });
			
			$("form#priceAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#pricedialog").dialog("close");
				showlist();
			});

			$("button#priceAddCancelButton").on("click",function(){
				$("div#pricedialog").dialog("close");
			})
		})
	})
	
	//修改按钮点击
	$("a#pricemodifylink").on("click",function(){
		if(priceNo==0){
			alert("请选择需要修改的按钮");
		}else{
			$("div#pricedialog").load("price/modify.html",function(){
				$("div#pricedialog").dialog({
					title: "修改价格",
					width:500,
					height:300
				});
				
				$.getJSON("price/get.mvc",{pid:priceNo},function(data){
					$("input[name='pid']").val(data.pid);
					$("input[name='price']").val(data.price);
					$("input[name='ptype']").val(data.ptype);
					$("input[name='prank']").val(data.prank);
					$("input[name='premarks']").val(data.premarks);
				});
				
				$("form#priceModifyForm").validate({
					 rules: {
					        "pid": {
						        required: true
					        },
					        "price":{
					        	required: true
					        },
					        "ptype":{
					        	required: true
					        },
					        "prank":{
					        	required: true
					        }
					 }
				 });
				
				$("form#priceModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#pricedialog").dialog("close");
					showlist();
				});

				$("button#priceModifyCancelButton").on("click",function(){
					$("div#pricedialog").dialog("close");
				})
			})
		}
	})
	
	//删除按钮点击
	$("a#pricedeletelink").on("click",function(){
		if(priceNo==0){
			alert("请选择需要删除的价格");
		}else{
			var confrimresult = confirm("确认要删除此价格吗");
			if(confrimresult){
				$.getJSON("price/delete.mvc",{pid:priceNo},function(data){
					if(data.result=="Y"){
						alert("删除价格成功");
						priceNo = 0;
					}else{
						alert("删除价格失败");
					}
					showlist();
				});
			}
		}
	})
	
	//查看按钮点击
	$("a#priceviewlink").on("click",function(){
		if(priceNo==0){
			alert("请选择要查看的价格");
		}
		else{
			$("div#pricedialog").load("price/view.html",function(){
				
				$("div#pricedialog").dialog({
					title: "查看价格",
					width:700,
					height:500
				});
				
				$.getJSON("price/get.mvc",{pid:priceNo},function(data){
					
					if(data!=null){
						$("#pid").html(data.pid);
						$("#price").html(data.price);
						$("#ptype").html(data.ptype);
						$("#prank").html(data.prank);
						$("#premarks").html(data.premarks);
					}
				});
				
				$.getJSON("product/point/price.mvc",{pid:priceNo},function(data){
					
					var showinfo="";
					for(var i=0;i<data.length;i++){
						showinfo=showinfo+data[i].pname+"&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					
					$("#product").html(showinfo);

				});
					
			});
		}
	})
	
	showlist();
});