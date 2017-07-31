$(document).ready(function(){
	var vipNo=0;
	function showlist(){
		$.getJSON("vip/get/all.mvc",function(data){
			alert(data.length);
			var showinfo="";
			for(var i=0;i<data.length;i++){
				showinfo=showinfo+"<tr id='"+data[i].management+"'><td><input type='checkbox'></td><td>"+data[i].management+"</td><td>"+data[i].memType+"</td><td>"+data[i].depRa+"</td></tr>";
			}
			$("#allvip").html(showinfo);
			
			$("table#viplist tbody tr").on("click",function(){
				vipNo = $(this).attr("id");
				$("table#viplist tbody tr").css("background-color","#eee");
				$(this).css("background-color","#AAA");
			});
		})
	}
	$("#addvip").on("click",function(){
		$("#vip").load("vip/add.html",function(){
			$("#vip").dialog({
				title:'增加会员',
				width:400,
				height:350
			});
			
			$("form#vipAddForm").validate({
				 rules: {
				        "management": {
					        required: true
				        },
				        "memType":{
				        	required: true
				        }
				 }
			 });
			
			$("#vipAddFrom").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("add OK");
				}
				else{
					alert("add failure");
				}
				$("#vip").dialog("close");
				showlist();
				
			});	
		});
		
	})
	$("#quxiao").on("click",function(){
		$("#vip").dialog("close");
	})
	
	//修改按钮点击
	$("#modifyvip").on("click",function(){
		if(vipNo==0){
			alert("请选择需要修改的按钮");
		}else{
			$("#vip").load("vip/modify.html",function(){
				$("#vip").dialog({
					title: "修改价格",
					width:500,
					height:350
				});
				
				$.getJSON("vip/get.mvc",{no:vipNo},function(data){
					$("input[name='management']").val(data.management);
					$("input[name='memType']").val(data.memType);
					$("input[name='depRa']").val(data.depRa);
				});
				
				$("form#vipModifyForm").validate({
					 rules: {
					        "management": {
						        required: true
					        },
					        "memType":{
					        	required: true
					        },
					        "depRa":{
					        	required: true
					        }
					 }
				 });
				
				$("#vipModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("#vip").dialog("close");
					showlist();
				});

				$("#vipModifyCancelButton").on("click",function(){
					$("#vip").dialog("close");
				})
			})
		}
	})
	//删除按钮点击
	$("#deletevip").on("click",function(){
		if(vipNo==0){
			alert("请选择需要删除的价格");
		}else{
			var confrimresult = confirm("确认要删除此价格吗");
			if(confrimresult){
				$.getJSON("vip/delete.mvc",{management:vipNo},function(data){
					if(data.result=="Y"){
						alert("删除价格成功");
						vipNo = 0;
					}else{
						alert("删除价格失败");
					}
					showlist();
				});
			}
		}
	})
	
	$("#searchvip").on("click",function(){
		
	})
	
	showlist();
	//查看产地
	$("#searchvip").on("click",function(){
		
		if(vipNo==null){
			alert("请选择要查看的会员");
		}
		else{
			$("#vip").load("vip/view.html",function(){
				
				$("#vip").dialog({
					title: "查看产地",
					width:700,
					height:500
				});
				
				$.getJSON("vip/get.mvc",{no:vipNo},function(data){
					
					if(data!=null){
						$("#vid3").html(data.management);
						$("#vtype3").html(data.memType);
						$("#vre3").html(data.depRa);
					}
				});
					
			});
		}
		
	});
});