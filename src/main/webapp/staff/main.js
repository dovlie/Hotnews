$(document).ready(function(){
	var staffNo = 0;
	
	function showlist(){
		$.getJSON("staff/get/all.mvc",function(data){

			var showinfo="";
			for(var i=0;i<data.length;i++){
				showinfo=showinfo+"<tr id='"+data[i].sid+"'><td><input type='checkbox'></td><td>"+data[i].sid+"</td><td>"+data[i].sname+"</td><td>"+data[i].sphone+"</td><td>"+data[i].sbranch+"</td></tr>";
			}
			$("table#stafflist tbody").html(showinfo);
			
			$("table#stafflist tbody tr").on("click",function(){
				staffNo = $(this).attr("id");
				$("table#stafflist tbody tr").css("background-color","#FFFFFF");
				$(this).css("background-color","#AAA");
			});
		})
	}
	//增加按钮点击
	$("a#staffaddlink").on("click",function(){
		$("div#staffdialog").load("staff/add.html",function(){
			$("div#staffdialog").dialog({
				title: "增加员工",
				width:500,
				height:250
			});
			
			$("form#staffAddForm").validate({
				 rules: {
				        "sid": {
					        required: true
				        },
				        "sname": {
					        required: true
				        },
				        "sphone": {
					        required: true
				        },
				        "sbranch":{
				        	required: true
				        }
				 }
			 });
			
			$("form#staffAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#staffdialog").dialog("close");
				showlist();
			});

			$("button#staffAddCancelButton").on("click",function(){
				$("div#staffdialog").dialog("close");
			})
		})
	})
	
	//修改按钮点击
	$("a#staffmodifylink").on("click",function(){
		if(staffNo==0){
			alert("请选择需要修改的按钮");
		}else{
			$("div#staffdialog").load("staff/modify.html",function(){
				$("div#staffdialog").dialog({
					title: "修改用户",
					width:500,
					height:300
				});
				
				$.getJSON("staff/get.mvc",{no:staffNo},function(data){
					$("input[name='sid']").val(data.sid);
					$("input[name='sname']").val(data.sname);
					$("input[name='sphone']").val(data.sphone);
					$("input[name='sbranch']").val(data.sbranch);
				});
				
				$("form#staffModifyForm").validate({
					 rules: {
					        "sid": {
						        required: true
					        },
					        "sname": {
						        required: true
					        },
					        "sphone": {
						        required: true
					        },
					        "sbranch":{
					        	required: true
					        }
					 }
				 });
				
				$("form#staffModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#staffdialog").dialog("close");
					showlist();
				});

				$("button#staffModifyCancelButton").on("click",function(){
					$("div#staffdialog").dialog("close");
				})
			})
		}
	})
	
	//删除按钮点击
	$("a#staffdeletelink").on("click",function(){
		if(staffNo==0){
			alert("请选择需要删除的用户");
		}else{
			var confrimresult = confirm("确认要删除此用户吗");
			if(confrimresult){
				$.getJSON("staff/delete.mvc",{sid:staffNo},function(data){
					if(data.result=="Y"){
						alert("删除用户成功");
						staffNo = 0;
					}else{
						alert("删除用户失败");
					}
					showlist();
				});
			}
		}
	})
	
	//查看按钮点击
	$("a#staffviewlink").on("click",function(){
		
	})
	
	showlist();
});