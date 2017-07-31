$(document).ready(function(){
	var publicityId = 0;
	$("#publicitylist").jqGrid({
        url: "publicity/get/all/pages.mvc",
        datatype: "json",
        width:930,
        height:300,
        mtype: "GET",
        colNames: ["宣传编号", "宣传名称", "宣传方式", "宣传成本", "备注"],
        colModel: [
            { name: "puid", width: 100 },
            { name: "puname", width: 150 },
            { name: "puway", width: 100, align: "right" },
            { name: "pucost", width: 100, align: "right" },
            { name: "puremarks", width: 100, align: "right" },
        ],
        
        jsonReader:{
        	id:"puid"
        },
        
        pager: "#publicitypager",
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: "puid",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "宣传列表",
        multiselect:false,
        onSelectRow:function(puid){
        	publicityId = puid;
        }
	})
	
	//增加产地
	$("a#publicityaddlink").on("click",function(){
		$("div#publicitydialog").load("publicity/add.html",function(){		
			$("div#publicitydialog").dialog({
				title: "增加产地",
				width:700,
				height:500
			});
			
			$("form#publicityAddForm").validate({
				 rules: {
				        "puid": {
					        required: true
				        },
				        "puname":{
				        	required: true
				        },
				        "puway":{
				        	required: true
				        },
				        "pucost":{
				        	required: true
				        },
				        "puremarks":{
				        	required: true
				        }
				 }
			 });
			
			$("form#publicityAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#publicitydialog").dialog("close");
				$("div#publicitydialog").html("");
				$("table#publicitylist").jqGrid().trigger("reloadGrid");
				
			});

			$("button#publicityAddCancelButton").on("click",function(){
				$("div#publicitydialog").dialog("close");
				$("div#publicitydialog").html("");
			})
			
		})
	})
			
	//修改产地
	$("a#publicitymodifylink").on("click",function(){
		if(publicityId==0){
			alert("请选择要修改的宣传");
		}
		else{
			$("div#publicitydialog").load("publicity/modify.html",function(){
				$("div#publicitydialog").dialog({
					title: "修改产地",
					width:500,
					height:500
				});
				
				$.getJSON("publicity/get.mvc",{puid:publicityId},function(data){
					$("input[name='puid']").val(data.puid);
					$("input[name='puname']").val(data.puname);
					$("input[name='puway']").val(data.puway);
					$("input[name='pucost']").val(data.pucost);
					$("input[name='puremarks']").val(data.puremarks);
				});
				
				$("form#puremarksModifyForm").validate({
			        "puid": {
				        required: true
			        },
			        "puname":{
			        	required: true
			        },
			        "puway":{
			        	required: true
			        },
			        "pucost":{
			        	required: true
			        },
			        "puremarks":{
			        	required: true
			        }
				 });
				
				$("form#publicityModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#publicitydialog").dialog("close");
					$("div#publicitydialog").html("");
					$("table#publicitylist").jqGrid().trigger("reloadGrid");
				});

				$("button#publicityModifyCancelButton").on("click",function(){
					$("div#publicitydialog").dialog("close");
					$("div#publicitydialog").html("");
				})
			})
		}
	})
	
	//删除产地
		$("a#publicitydeletelink").on("click",function(){
		if(publicityId==0){
			alert("请选择需要删除的宣传");
		}else{
			var confrimresult = confirm("确认要删除此宣传吗");
			if(confrimresult){
				$.getJSON("publicity/delete.mvc",{puid:publicityId},function(data){
					if(data.result=="Y"){
						alert("删除宣传成功");
						originId = 0;
					}else{
						alert("删除宣传失败");
					}
					$("table#publicitylist").jqGrid().trigger("reloadGrid");
				});
			}
		}
	})
	
	//查看产地
	$("a#publicityviewlink").on("click",function(){
		
		if(publicityId==0){
			alert("请选择要查看的宣传");
		}
		else{
			$("div#publicitydialog").load("publicity/view.html",function(){
				
				$("div#publicitydialog").dialog({
					title: "查看宣传",
					width:700,
					height:500
				});
				
				$.getJSON("publicity/get.mvc",{puid:publicityId},function(data){
					
					if(data!=null){
						$("#puid").html(data.puid);
						$("#puname").html(data.puname);
						$("#puway").html(data.puway);
						$("#pucost").html(data.pucost);
						$("#puremarks").html(data.puremarks);
					}
				});
					
			});
		}
		
	});
})
