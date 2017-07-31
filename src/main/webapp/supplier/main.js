$(document).ready(function(){
	var sNo=0;
	
	function showlist(){
		$.getJSON("supplier/get/all.mvc",function(data){
			
			var showinfo="";
			for(var i=0;i<data.length;i++){
				showinfo=showinfo+"<tr id='"+data[i].sid+"'><td><input type='checkbox'></td><td>"+data[i].sid+"</td><td>"+data[i].sname+"</td><td>"+data[i].saddress+"</td><td>"+data[i].lmname+"</td><td>"+data[i].lmphone+"</td></tr>";
			}
			$("table#supplierlist tbody").html(showinfo);
			$("table#supplierlist tbody tr").on("click",function(){
				sNo = $(this).attr("id");
				$("table#supplierlist tbody tr").css("background-color","#FFFFFF");
				$(this).css("background-color","#AAA");
			});
		})
	}
	
	//增加按钮
	$("a#SupplierAddLink").on("click",function(){
		$("div#Supplierdialog").load("supplier/add.html",function(){
			$("div#Supplierdialog").dialog({
				title: "增加供应商信息",
				width:600,
				height:500
				});	
			//日期选择
			/*$("input[name='sid']").datepicker({
				dateFormat:"yy-mm-dd"
			});*/
					
			
			$("form#SupplierAddForm").validate({
				rules:{
					"sid":{
						required:true
					},
					"sname":{
						required:true
					},
					"saddress":{
						required:true
					},
					"lmname":{
						required:true
					},
					"lmphone":{
						required:true
					}
				}
			});
			
			$("form#SupplierAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("增加成功");
				}
				else{
					alert("增加失败");
				}
				$("div#Supplierdialog").dialog("close");
				showlist();
				
			});
			
			$("button#SupplierAddCancelButton").on("click",function(){
				$("div#Supplierdialog").dialog("close");	
			});
		});
	});
	
	//修改按钮
	$("a#SupplierModifyLink").on("click",function(){
		
		if(sNo==0){
			alert("请选择要修改的供应商");
		}
		else{
			$("div#Supplierdialog").load("supplier/modify.html",function(){
				$("div#Supplierdialog").dialog({
					title: "修改供应商",
					width:600,
					height:500
				});
				
				$.getJSON("supplier/get.mvc",{no:sNo},function(data){
					$("input[name='sid']").val(data.sid);
					$("input[name='sname']").val(data.sname);
					$("input[name='saddress']").val(data.saddress);
					$("input[name='lmname']").val(data.lmname);
					$("input[name='lmphone']").val(data.lmphone);				
				});	
				
				$("form#SupplierModifyForm").validate({
					rules:{
						"sid":{
							required:true
						},
						"sname":{
							required:true
						},
						"saddress":{
							required:true
						},
						"lmname":{
							required:true
						},
						"lmphone":{
							required:true
						}
					}
				});
				$("form#SupplierModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}
					else{
						alert("修改失败");
					}
					$("div#Supplierdialog").dialog("close");
					showlist();
					
				});
					
				$("button#SupplierModifyCancelButton").on("click",function(){
					$("div#Supplierdialog").dialog("close");	
				});
			});	
		}
	});
	
	//删除按钮
	$("a#SupplierDeleteLink").on("click",function(){
		if(sNo==0){
			alert("请选择需要删除的供应商");
		}else{
			var confrimresult = confirm("确认要删除此供应商吗");
			if(confrimresult){
				$.getJSON("supplier/delete.mvc",{sid:sNo},function(data){
					if(data.result=="Y"){
						alert("删除供应商成功");
						sNo = 0;
					}else{
						alert("删除供应商失败");
					}
					showlist();
				});
			}
		}
	});
	//查询按钮
	$("a#SupplierViewLink").on("click",function(){
		if(sNo==0){
			alert("请选择要查看的供应商");
		}
		else{
			$("div#Supplierdialog").load("supplier/view.html",function(){
				
				$("div#Supplierdialog").dialog({
					title: "查看供应商信息",
					width:700,
					height:500
				});
				
				$.getJSON("supplier/get.mvc",{no:sNo},function(data){
					$("#sid").html(data.sid);
					$("#sname").html(data.sname);
					$("#saddress").html(data.saddress);
					$("#lmname").html(data.mlname);
					$("#lmphone").html(data.lmphone);
					
				});	
				
			});
		}
	
	});
	
	showlist();
	
});