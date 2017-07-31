$(document).ready(function(){
	var ptNo=0;
	
	function showlist(){
		$.getJSON("producttype/get/all.mvc",function(data){
			
			var showinfo="";
			for(var i=0;i<data.length;i++){				
				showinfo=showinfo+"<tr id='"+data[i].ptid+"'><td><input type='checkbox'></td><td>"+data[i].ptid+"</td><td>"+data[i].ptname+"</td></tr>";
			}
			$("table#producttypelist tbody").html(showinfo);
			$("table#producttypelist tbody tr").on("click",function(){
				ptNo = $(this).attr("id");
				$("table#producttypelist tbody tr").css("background-color","#FFFFFF");
				$(this).css("background-color","#AAA");
			});
		})
	}
	
	//增加按钮
	$("a#ProductTypeAddLink").on("click",function(){
		$("div#ProductTypedialog").load("producttype/add.html",function(){
			$("div#ProductTypedialog").dialog({
				title: "增加产类型信息",
				width:600,
				height:500
				});	
			//日期选择
			/*$("input[name='sid']").datepicker({
				dateFormat:"yy-mm-dd"
			});*/
					
			
			$("form#ProductTypeAddForm").validate({
				rules:{
					"ptid":{
						required:true
					},
					"ptname":{
						required:true
					}
				}
			});
			
			$("form#ProductTypeAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("增加成功");
				}
				else{
					alert("增加失败");
				}
				$("div#ProductTypedialog").dialog("close");
				showlist();
				
			});
			
			$("button#ProductTypeAddCancelButton").on("click",function(){
				$("div#ProductTypedialog").dialog("close");	
			});
		});
	});
	
	//修改按钮
	$("a#ProductTypeModifyLink").on("click",function(){
		
		if(ptNo==0){
			alert("请选择要修改的产品类型");
		}
		else{
			$("div#ProductTypedialog").load("producttype/modify.html",function(){
				$("div#ProductTypedialog").dialog({
					title: "修改产品类型",
					width:600,
					height:500
				});
				
				$.getJSON("producttype/get.mvc",{no:ptNo},function(data){
					$("input[name='ptid']").val(data.ptid);
					$("input[name='ptname']").val(data.ptname);		
				});	
				
				$("form#ProductTypeModifyForm").validate({
					rules:{
						"ptid":{
							required:true
						},
						"ptname":{
							required:true
						}
						
					}
				});
				$("form#ProductTypeModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}
					else{
						alert("修改失败");
					}
					$("div#ProductTypedialog").dialog("close");
					showlist();
					
				});
					
				$("button#ProductTypeModifyCancelButton").on("click",function(){
					$("div#ProductTypedialog").dialog("close");	
				});
			});	
		}
	});
	
	//删除按钮
	$("a#ProductTypeDeleteLink").on("click",function(){
		if(ptNo==0){
			alert("请选择需要删除的产品类型");
		}else{
			var confrimresult = confirm("确认要删除此产品类型吗");
			if(confrimresult){
				$.getJSON("producttype/delete.mvc",{ptid:ptNo},function(data){
					if(data.result=="Y"){
						alert("删除产品类型成功");
						ptNo = 0;
					}else{
						alert("删除产品类型失败");
					}
					showlist();
				});
			}
		}
	});
	//查询按钮
	$("a#ProductTypeViewLink").on("click",function(){
		if(ptNo==0){
			alert("请选择要查看的产品类型");
		}
		else{
			$("div#ProductTypedialog").load("producttype/view.html",function(){
				
				$("div#ProductTypedialog").dialog({
					title: "查看产品类型",
					width:700,
					height:500
				});
				
				$.getJSON("producttype/get.mvc",{no:ptNo},function(data){
					
					if(data!=null){
						$("#ptid").html(data.ptid);
						$("#ptname").html(data.ptname);
						
					}
				});
				
				$.getJSON("product/point/producttype.mvc",{ptid:ptNo},function(data){
					
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