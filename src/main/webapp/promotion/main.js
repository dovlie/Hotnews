$(document).ready(function(){
	var promotionId = 0;
	$("#promotionlist").jqGrid({
        url: "promotion/get/all/pages.mvc",
        datatype: "json",
        width:930,
        height:300,
        mtype: "GET",
        colNames: ["优惠编号", "优惠类型", "优惠价格","优惠级别","备注"],
        colModel: [
            { name: "prid", width: 100 },
            { name: "prtype", width: 150 },
            { name: "prprice", width: 100, align: "right" },
            { name: "rank", width: 100, align: "right" },
            { name: "remarks", width: 100, align: "right" }
        ],
        
        jsonReader:{
        	id:"prid"
        },
        
        pager: "#promotionpager",
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: "prid",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "优惠列表",
        multiselect:false,
        onSelectRow:function(prid){
        	promotionId = prid;
        }
	})
	
	//增加优惠
	$("a#promotionaddlink").on("click",function(){
		$("div#promotiondialog").load("promotion/add.html",function(){		
			$("div#promotiondialog").dialog({
				title: "增加优惠",
				width:700,
				height:500
			});
			
			$("form#promotionAddForm").validate({
				 rules: {
				        "prid": {
					        required: true
				        },
				        "prtype":{
				        	required: true
				        },
				        "prprice":{
				        	required: true
				        },
				        "rank":{
				        	required: true
				        }
				 }
			 });
			
			$("form#promotionAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#promotiondialog").dialog("close");
				$("div#promotiondialog").html("");
				$("table#promotionlist").jqGrid().trigger("reloadGrid");
				
			});

			$("button#promotionAddCancelButton").on("click",function(){
				$("div#promotiondialog").dialog("close");
				$("div#promotiondialog").html("");
			})
			
		})
	})
			
	//修改优惠
	$("a#promotionmodifylink").on("click",function(){
		if(promotionId==0){
			alert("请选择要修改的优惠");
		}
		else{
			$("div#promotiondialog").load("promotion/modify.html",function(){
				$("div#promotiondialog").dialog({
					title: "修改优惠",
					width:500,
					height:300
				});
				
				$.getJSON("promotion/get.mvc",{prid:promotionId},function(data){
					$("input[name='prid']").val(data.prid);
					$("input[name='prtype']").val(data.prtype);
					$("input[name='prprice']").val(data.prprice);
					$("input[name='rank']").val(data.rank);
					$("input[name='remarks']").val(data.remarks);
				});
				
				$("form#promotionModifyForm").validate({
					 rules: {
					        "prid": {
						        required: true
					        },
					        "prtype":{
					        	required: true
					        },
					        "prprice":{
					        	required: true
					        },
					        "rank":{
					        	required: true
					        }
					 }
				 });
				
				$("form#promotionModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#promotiondialog").dialog("close");
					$("div#promotiondialog").html("");
					$("table#promotionlist").jqGrid().trigger("reloadGrid");
				});

				$("button#promotionModifyCancelButton").on("click",function(){
					$("div#promotiondialog").dialog("close");
					$("div#promotiondialog").html("");
				})
			})
		}
	})
	
	//删除优惠
		$("a#promotiondeletelink").on("click",function(){
		if(promotionId==0){
			alert("请选择需要删除的优惠");
		}else{
			var confrimresult = confirm("确认要删除此优惠吗");
			if(confrimresult){
				$.getJSON("promotion/delete.mvc",{prid:promotionId},function(data){
					if(data.result=="Y"){
						alert("删除优惠成功");
						originId = 0;
					}else{
						alert("删除优惠失败");
					}
					$("table#promotionlist").jqGrid().trigger("reloadGrid");
				});
			}
		}
	})
	
	//查看优惠
	$("a#promotionviewlink").on("click",function(){
		
		if(promotionId==0){
			alert("请选择要查看的优惠");
		}
		else{
			$("div#promotiondialog").load("promotion/view.html",function(){
				
				$("div#promotiondialog").dialog({
					title: "查看优惠",
					width:700,
					height:500
				});
				
				$.getJSON("promotion/get.mvc",{prid:promotionId},function(data){
					
					if(data!=null){
						$("#prid").html(data.prid);
						$("#prtype").html(data.prtype);
						$("#prprice").html(data.prprice);
						$("#rank").html(data.rank);
						$("#remarks").html(data.remarks);
					}
				});
				
				$.getJSON("product/point/promotion.mvc",{prid:promotionId},function(data){
					
					var showinfo="";
					for(var i=0;i<data.length;i++){
						showinfo=showinfo+data[i].pname+"&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					
					$("#promotion").html(showinfo);

				});
					
			});
		}
		
	});
})
