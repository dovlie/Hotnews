$(document).ready(function(){
	var originId = 0;
	$("#originlist").jqGrid({
        url: "origin/get/all/pages.mvc",
        datatype: "json",
        width:930,
        height:300,
        mtype: "GET",
        colNames: ["产地编号", "国家", "省/州/郡", "市", "县/区"],
        colModel: [
            { name: "oid", width: 100 },
            { name: "ocountry", width: 150 },
            { name: "province", width: 100, align: "right" },
            { name: "city", width: 100, align: "right" },
            { name: "country", width: 100, align: "right" },
        ],
        
        jsonReader:{
        	id:"oid"
        },
        
        pager: "#originpager",
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: "oid",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "产地列表",
        multiselect:false,
        onSelectRow:function(oid){
        	originId = oid;
        }
	})
	
	//增加产地
	$("a#originaddlink").on("click",function(){
		$("div#origindialog").load("origin/add.html",function(){		
			$("div#origindialog").dialog({
				title: "增加产地",
				width:700,
				height:500
			});
			
			$("form#originAddForm").validate({
				 rules: {
				        "oid": {
					        required: true
				        },
				        "ocountry":{
				        	required: true
				        },
				        "province":{
				        	required: true
				        },
				        "city":{
				        	required: true
				        },
				        "country":{
				        	required: true
				        }
				 }
			 });
			
			$("form#originAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#origindialog").dialog("close");
				$("div#origindialog").html("");
				$("table#originlist").jqGrid().trigger("reloadGrid");
				
			});

			$("button#originAddCancelButton").on("click",function(){
				$("div#origindialog").dialog("close");
				$("div#origindialog").html("");
			})
			
		})
	})
			
	//修改产地
	$("a#originmodifylink").on("click",function(){
		if(originId==0){
			alert("请选择要修改的产地");
		}
		else{
			$("div#origindialog").load("origin/modify.html",function(){
				$("div#origindialog").dialog({
					title: "修改产地",
					width:500,
					height:500
				});
				
				$.getJSON("origin/get.mvc",{oid:originId},function(data){
					$("input[name='oid']").val(data.oid);
					$("input[name='ocountry']").val(data.ocountry);
					$("input[name='province']").val(data.province);
					$("input[name='city']").val(data.city);
					$("input[name='country']").val(data.country);
				});
				
				$("form#originModifyForm").validate({
					 rules: {
					        "oid": {
						        required: true
					        },
					        "ocountry":{
					        	required: true
					        },
					        "province":{
					        	required: true
					        },
					        "city":{
					        	required: true
					        },
					        "country":{
					        	required: true
					        }
					 }
				 });
				
				$("form#originModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#origindialog").dialog("close");
					$("div#origindialog").html("");
					$("table#originlist").jqGrid().trigger("reloadGrid");
				});

				$("button#originModifyCancelButton").on("click",function(){
					$("div#origindialog").dialog("close");
					$("div#origindialog").html("");
				})
			})
		}
	})
	
	//删除产地
		$("a#origindeletelink").on("click",function(){
		if(originId==0){
			alert("请选择需要删除的产地");
		}else{
			var confrimresult = confirm("确认要删除此产地吗");
			if(confrimresult){
				$.getJSON("origin/delete.mvc",{oid:originId},function(data){
					if(data.result=="Y"){
						alert("删除产地成功");
						originId = 0;
					}else{
						alert("删除产地失败");
					}
					$("table#originlist").jqGrid().trigger("reloadGrid");
				});
			}
		}
	})
	
	//查看产地
	$("a#originviewlink").on("click",function(){
		
		if(originId==0){
			alert("请选择要查看的产品");
		}
		else{
			$("div#origindialog").load("origin/view.html",function(){
				
				$("div#origindialog").dialog({
					title: "查看产地",
					width:700,
					height:500
				});
				
				$.getJSON("origin/get.mvc",{oid:originId},function(data){
					
					if(data!=null){
						$("#oid").html(data.oid);
						$("#ocountry").html(data.ocountry);
						$("#province").html(data.province);
						$("#city").html(data.city);
						$("#country").html(data.country);
					}
				});
				
				$.getJSON("product/point/origin.mvc",{oid:originId},function(data){
					
					var showinfo="";
					for(var i=0;i<data.length;i++){
						showinfo=showinfo+data[i].pname+"&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					
					$("#origin").html(showinfo);

				});
					
			});
		}
		
	});
})
