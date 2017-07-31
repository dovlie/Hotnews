$(document).ready(function(){
	var wNo=0;
	
	function showlist(){
		$.getJSON("warehouse/get/all.mvc",function(data){
			
			var showinfo="";
			for(var i=0;i<data.length;i++){
				showinfo=showinfo+"<tr id='"+data[i].wid+"'><td><input type='checkbox'></td><td>"+data[i].wid+"</td><td>"+data[i].waddress+"</td><td>"+data[i].sname+"</td><td>"+data[i].sphone+"</td></tr>";
			}
			$("table#warehouselist tbody").html(showinfo);
			$("table#warehouselist tbody tr").on("click",function(){
				wNo = $(this).attr("id");
				$("table#warehouselist tbody tr").css("background-color","#FFFFFF");
				$(this).css("background-color","#AAA");
			});
		})
	}
	
	//增加按钮
	$("a#WarehouseAddLink").on("click",function(){
		$("div#Warehousedialog").load("warehouse/add.html",function(){
			$("div#Warehousedialog").dialog({
				title: "增加供应商信息",
				width:600,
				height:500
				});	
			//日期选择
			/*$("input[name='sid']").datepicker({
				dateFormat:"yy-mm-dd"
			});*/
					
			
			$("form#WarehouseAddForm").validate({
				rules:{
					"wid":{
						required:true
					},
					"waddress":{
						required:true
					},
					"sname":{
						required:true
					},
					"sphone":{
						required:true
					}
				}
			});
			
			$("form#WarehouseAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("增加成功");
				}
				else{
					alert("增加失败");
				}
				$("div#Warehousedialog").dialog("close");
				showlist();
				
			});
			
			$("button#WarehouseAddCancelButton").on("click",function(){
				$("div#Warehousedialog").dialog("close");	
			});
		});
	});
	
	//修改按钮
	$("a#WarehouseModifyLink").on("click",function(){
		
		if(wNo==0){
			alert("请选择要修改的仓库");
		}
		else{
			$("div#Warehousedialog").load("warehouse/modify.html",function(){
				$("div#Warehousedialog").dialog({
					title: "修改仓库信息",
					width:600,
					height:500
				});
				
				$.getJSON("warehouse/get.mvc",{no:wNo},function(data){
					$("input[name='wid']").val(data.wid);
					$("input[name='waddress']").val(data.waddress);
					$("input[name='sname']").val(data.sname);
					$("input[name='sphone']").val(data.sphone);				
				});	
				
				$("form#WarehouseModifyForm").validate({
					rules:{
						"wid":{
							required:true
						},
						"waddress":{
							required:true
						},
						"sname":{
							required:true
						},
						"sphone":{
							required:true
						}
					}
				});
				$("form#WarehouseModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}
					else{
						alert("修改失败");
					}
					$("div#Warehousedialog").dialog("close");
					showlist();
					
				});
					
				$("button#WarehouseModifyCancelButton").on("click",function(){
					$("div#Warehousedialog").dialog("close");	
				});
			});	
		}
	});
	
	//删除按钮
	$("a#WarehouseDeleteLink").on("click",function(){
		if(wNo==0){
			alert("请选择需要删除的仓库");
		}else{
			var confrimresult = confirm("确认要删除此仓库吗？");
			if(confrimresult){
				$.getJSON("warehouse/delete.mvc",{wid:wNo},function(data){
					if(data.result=="Y"){
						alert("删除仓库成功");
						wNo = 0;
					}else{
						alert("删除仓库失败");
					}
					showlist();
				});
			}
		}
	});
	
	//查询按钮
	$("a#WarehouseViewLink").on("click",function(){
		if(wNo==0){
			alert("请选择要查看的仓库");
		}
		else{
			$("div#Warehousedialog").load("warehouse/view.html",function(){
				
				$("div#Warehousedialog").dialog({
					title: "查看仓库信息",
					width:700,
					height:500
				});
				
				$.getJSON("warehouse/get.mvc",{no:wNo},function(data){
					$("#wid").html(data.wid);
					$("#waddress").html(data.waddress);
					$("#sname").html(data.sname);
					$("#sphone").html(data.sphone);
					
				});	
				
				$.getJSON("product/point/warehouse.mvc",{wid:wNo},function(data){
					var showinfo="";
					for(var i=0;i<data.length;i++){
						showinfo=showinfo+data[i].pname+"&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					
					$("#product").html(showinfo);

				});
					
			});
		}
	
	});
	
	showlist();
	
});