$(document).ready(function(){
	var ppNo=0;
	
	function showlist(){
		$.getJSON("procurementprogram/get/all.mvc",function(data){
			var showinfo="";
			for(var i=0;i<data.length;i++){
				showinfo=showinfo+"<tr id='"+data[i].pid+"'><td><input type='checkbox'></td><td>"+data[i].pid+"</td><td>"+data[i].pdate+"</td><td>"+data[i].ppname+"</td><td>"+data[i].pquantity+"</td><td>"+data[i].sid+"</td></tr>";
			}
			$("table#procurementprogramlist tbody").html(showinfo);
			$("table#procurementprogramlist tbody tr").on("click",function(){
				ppNo = $(this).attr("id");
				$("table#procurementprogramlist tbody tr").css("background-color","#FFFFFF");
				$(this).css("background-color","#AAA");
			});
		})
	}
	
	//增加按钮
	$("a#ProcurementProgramAddLink").on("click",function(){
		$("div#ProcurementProgramdialog").load("procurementprogram/add.html",function(){
			$("div#ProcurementProgramdialog").dialog({
				title: "增加采购计划",
				width:600,
				height:500
				});	
			//日期选择
			$("input[name='pdate']").datepicker({
				dateFormat:"yy-mm-dd"
			});
					
			
			$("form#ProcurementProgramAddForm").validate({
				rules:{
					"pid":{
						required:true
					},
					"pdate":{
						required:true
					},
					"ppname":{
						required:true
					},
					"pquantity":{
						required:true
					},
					"sid":{
						required:true
					}
				}
			});
			
			$("form#ProcurementProgramAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("增加成功");
				}
				else{
					alert("增加失败");
				}
				$("div#ProcurementProgramdialog").dialog("close");
				showlist();
				
			});
			
			$("button#ProcurementProgramAddCancelButton").on("click",function(){
				$("div#ProcurementProgramdialog").dialog("close");	
			});
		});
	});
	
	//修改按钮
	$("a#ProcurementProgramModifyLink").on("click",function(){
		
		if(ppNo==0){
			alert("请选择要修改的采购计划");
		}
		else{
			$("div#ProcurementProgramdialog").load("procurementprogram/modify.html",function(){
				$("div#ProcurementProgramdialog").dialog({
					title: "修改采购计划",
					width:600,
					height:500
				});
				
				$.getJSON("procurementprogram/get.mvc",{no:ppNo},function(data){
					$("input[name='pid']").val(data.pid);
					$("input[name='pdate']").val(data.pdate);
					$("input[name='ppname']").val(data.ppname);
					$("input[name='pquantity']").val(data.pquantity);
					$("input[name='sid']").val(data.sid);				
				});	
				
				$("form#ProcurementProgramModifyForm").validate({
					rules:{
						"pid":{
							required:true
						},
						"pdate":{
							required:true
						},
						"ppname":{
							required:true
						},
						"pquantity":{
							required:true
						},
						"sid":{
							required:true
						}
					}
				});
				$("form#ProcurementProgramModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}
					else{
						alert("修改失败");
					}
					$("div#ProcurementProgramdialog").dialog("close");
					showlist();
					
				});
					
				$("button#ProcurementProgramModifyCancelButton").on("click",function(){
					$("div#ProcurementProgramdialog").dialog("close");	
				});
			});	
		}
	});
	
	//删除按钮
	$("a#ProcurementProgramDeleteLink").on("click",function(){
		if(ppNo==0){
			alert("请选择需要删除的采购计划");
		}else{
			var confrimresult = confirm("确认要删除此采购计划吗");
			if(confrimresult){
				$.getJSON("procurementprogram/delete.mvc",{pid:ppNo},function(data){
					if(data.result=="Y"){
						alert("删除采购计划成功");
						ppNo = 0;
					}else{
						alert("删除采购计划失败");
					}
					showlist();
				});
			}
		}
	});
	//查询按钮
	$("a#ProcurementProgramViewLink").on("click",function(){
		if(ppNo==0){
			alert("请选择要查看的采购计划");
		}
		else{
			$("div#ProcurementProgramdialog").load("procurementprogram/view.html",function(){
				
				$("div#ProcurementProgramdialog").dialog({
					title: "查看采购计划信息",
					width:700,
					height:500
				});
				
				$.getJSON("procurementprogram/get.mvc",{no:ppNo},function(data){
					$("#pid").html(data.pid);
					$("#pdate").html(data.pdate);
					$("#ppname").html(data.ppname);
					$("#pquantity").html(data.pquantity);
					$("#sid").html(data.sid);
					
				});	
				
				
					
			});
		}
	
	
	});
	
	showlist();
	
});