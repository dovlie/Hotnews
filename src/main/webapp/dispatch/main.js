$(document).ready(function(){
	var dispatchId = 0;
	$("#dispatchlist").jqGrid({
        url: "dispatch/get/all/pages.mvc",
        datatype: "json",
        width:930,
        height:300,
        mtype: "GET",
        colNames: ["配送编号", "配送名称"],
        colModel: [
            { name: "did", width: 100 },
            { name: "dname", width: 150 },
        ],
        
        jsonReader:{
        	id:"did"
        },
        
        pager: "#dispatchpager",
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: "did",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "配送列表",
        multiselect:false,
        onSelectRow:function(did){
        	dispatchId = did;
        }
	})
	
	//增加客户
	$("#dispatchaddlink").on("click",function(){
		$("#dispatchdialog").load("dispatch/add.html",function(){		
			$("#dispatchdialog").dialog({
				title: "增加配送信息",
				width:700,
				height:500
			});
			
			
			
			$("form#dispatchAddForm").validate({
				 rules: {
				        "did": {
					        required: true
				        },
				        "dname":{
				        	required: true
				        }
				 }
			 });
			
			$("form#dispatchAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#dispatchdialog").dialog("close");
				$("div#dispatchdialog").html("");
				$("table#dispatchlist").jqGrid().trigger("reloadGrid");
				
			});

			$("button#dispatchAddCancelButton").on("click",function(){
				$("div#dispatchdialog").dialog("close");
				$("div#dispatchdialog").html("");
			})
			
		})
	})
			
	//修改客户
	$("a#dispatchmodifylink").on("click",function(){
		if(dispatchId==0){
			alert("请选择要修改的配送信息");
		}
		else{
			$("div#dispatchdialog").load("dispatch/modify.html",function(){
				$("div#dispatchdialog").dialog({
					title: "修改配送信息",
					width:500,
					height:500
				});
				
				$.getJSON("dispatch/get.mvc",{did:dispatchId},function(data){
					$("input[name='did']").val(data.did);
					$("input[name='dname']").val(data.dname);
				});
				
				
				
				$("form#dispatchAddForm").validate({
					 rules: {
					        "did": {
						        required: true
					        },
					        "dname":{
					        	required: true
					        }
					 }
				 });
				
				$("form#dispatchModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#dispatchdialog").dialog("close");
					$("div#dispatchdialog").html("");
					$("table#dispatchlist").jqGrid().trigger("reloadGrid");
				});

				$("button#dispatchModifyCancelButton").on("click",function(){
					$("div#dispatchdialog").dialog("close");
					$("div#dispatchdialog").html("");
				})
			})
		}
	})
	
	//删除客户
		$("a#dispatchdeletelink").on("click",function(){
		if(dispatchId==0){
			alert("请选择需要删除的客户");
		}else{
			var confrimresult = confirm("确认要删除此客户吗");
			if(confrimresult){
				$.getJSON("dispatch/delete.mvc",{did:dispatchId},function(data){
					if(data.result=="Y"){
						alert("删除客户成功");
						dispatchId = 0;
					}else{
						alert("删除客户失败");
					}
					$("table#dispatchlist").jqGrid().trigger("reloadGrid");
				});
			}
		}
	});
	//查看产地
	$("#dispatchviewlink").on("click",function(){
		
		if(dispatchId==0){
			alert("请选择要查看的会员");
		}
		else{
			$("#dispatchdialog").load("dispatch/view.html",function(){
				
				$("#dispatchdialog").dialog({
					title: "查看产地",
					width:700,
					height:500
				});
				
				$.getJSON("dispatch/get.mvc",{did:dispatchId},function(data){
					
					if(data!=null){
						$("#did2").html(data.did);
						$("#dname2").html(data.dname);
					}
				});
					
			});
		}
		
	});
	
	//查看客户
})
