$(document).ready(function(){
	var userNo = 0;
	
	function showlist(){
		$.getJSON("user/get/all.mvc",function(data){

			var showinfo="";
			for(var i=0;i<data.length;i++){
				showinfo=showinfo+"<tr id='"+data[i].uid+"'><td><input type='checkbox'></td><td>"+data[i].uid+"</td><td>"+data[i].pw+"</td></tr>";
			}
			$("table#userlist tbody").html(showinfo);
			
			$("table#userlist tbody tr").on("click",function(){
				userNo = $(this).attr("id");
				$("table#userlist tbody tr").css("background-color","#FFFFFF");
				$(this).css("background-color","#AAA");
			});
		})
	}
	//增加按钮点击
	$("a#useraddlink").on("click",function(){
		$("div#userdialog").load("user/add.html",function(){
			$("div#userdialog").dialog({
				title: "增加价格",
				width:500,
				height:250
			});
			
			$("form#userAddForm").validate({
				 rules: {
				        "uid": {
					        required: true
				        },
				        "pw":{
				        	required: true
				        }
				 }
			 });
			
			$("form#userAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#userdialog").dialog("close");
				showlist();
			});

			$("button#userAddCancelButton").on("click",function(){
				$("div#userdialog").dialog("close");
			})
		})
	})
	
	//修改按钮点击
	$("a#usermodifylink").on("click",function(){
		if(userNo==0){
			alert("请选择需要修改的按钮");
		}else{
			$("div#userdialog").load("user/modify.html",function(){
				$("div#userdialog").dialog({
					title: "修改用户",
					width:500,
					height:300
				});
				
				$.getJSON("user/get.mvc",{no:userNo},function(data){
					$("input[name='uid']").val(data.uid);
					$("input[name='pw']").val(data.pw);
				});
				
				$("form#userModifyForm").validate({
					 rules: {
					        "uid": {
						        required: true
					        },
					        "pw":{
					        	required: true
					        }
					 }
				 });
				
				$("form#userModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#userdialog").dialog("close");
					showlist();
				});

				$("button#userModifyCancelButton").on("click",function(){
					$("div#userdialog").dialog("close");
				})
			})
		}
	})
	
	//删除按钮点击
	$("a#userdeletelink").on("click",function(){
		if(userNo==0){
			alert("请选择需要删除的用户");
		}else{
			var confrimresult = confirm("确认要删除此用户吗");
			if(confrimresult){
				$.getJSON("user/delete.mvc",{uid:userNo},function(data){
					if(data.result=="Y"){
						alert("删除用户成功");
						userNo = 0;
					}else{
						alert("删除用户失败");
					}
					showlist();
				});
			}
		}
	})
	
	//查看按钮点击
	$("a#userviewlink").on("click",function(){
		
	})
	
	showlist();
});