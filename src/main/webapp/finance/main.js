$(document).ready(function(){
	var financeNo = 0;
	
	function showlist(){
		$.getJSON("finance/get/all.mvc",function(data){

			var showinfo="";
			for(var i=0;i<data.length;i++){
				showinfo=showinfo+"<tr id='"+data[i].fid+"'><td>"+data[i].fid+"</td><td>"+data[i].fleixing+"</td><td>"+data[i].fmoney+"</td></tr>";
			}
			$("table#financelist tbody").html(showinfo);
			
			$("table#financelist tbody tr").on("click",function(){
				financeNo = $(this).attr("id");
				$("table#financelist tbody tr").css("background-color","#FFFFFF");
				$(this).css("background-color","#AAA");
			});
		})
	}
	//增加按钮点击
	$("a#financeaddlink").on("click",function(){
		$("div#financedialog").load("finance/add.html",function(){
			$("div#financedialog").dialog({
				title: "增加财务信息",
				width:500,
				height:250
			});
			
			$("form#financeAddForm").validate({
				 rules: {
				        "fid": {
					        required: true
				        },
				        "fleixing": {
					        required: true
				        },
				        "fmoney": {
					        required: true
				        }
				 }
			 });
			
			$("form#financeAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#financedialog").dialog("close");
				showlist();
			});

			$("button#financeAddCancelButton").on("click",function(){
				$("div#financedialog").dialog("close");
			})
		})
	})
	
	//修改按钮点击
	$("a#financemodifylink").on("click",function(){
		if(financeNo==0){
			alert("请选择需要修改的按钮");
		}else{
			$("div#financedialog").load("finance/modify.html",function(){
				$("div#financedialog").dialog({
					title: "修改财务信息",
					width:500,
					height:300
				});
				
				$.getJSON("finance/get.mvc",{no:financeNo},function(data){
					$("input[name='fid']").val(data.fid);
					$("input[name='fleixing']").val(data.fleixing);
					$("input[name='fmoney']").val(data.fmoney);
				});
				
				$("form#financeModifyForm").validate({
					 rules: {
					        "fid": {
						        required: true
					        },
					        "fleixing": {
						        required: true
					        },
					        "fmoney": {
						        required: true
					        }
					 }
				 });
				
				$("form#financeModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#financedialog").dialog("close");
					showlist();
				});

				$("button#financeModifyCancelButton").on("click",function(){
					$("div#financedialog").dialog("close");
				})
			})
		}
	})
	
	//删除按钮点击
	$("a#financedeletelink").on("click",function(){
		if(financeNo==0){
			alert("请选择需要删除的财务信息");
		}else{
			var confrimresult = confirm("确认要删除此信息吗？");
			if(confrimresult){
				$.getJSON("finance/delete.mvc",{fid:financeNo},function(data){
					if(data.result=="Y"){
						alert("删除信息成功");
						financeNo = 0;
					}else{
						alert("删除信息失败");
					}
					showlist();
				});
			}
		}
	})
	
	//查看按钮点击
	$("a#financeviewlink").on("click",function(){
		
	})
	
	showlist();
});