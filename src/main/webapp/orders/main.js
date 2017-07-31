$(document).ready(function(){
	var ordersId = 0;
	$("#orderslist").jqGrid({
        url: "orders/get/all/pages.mvc",
        datatype: "json",
        width:930,
        height:300,
        mtype: "GET",
        colNames: ["订单编号", "产品编号", "配送编号", "客户编号"],
        colModel: [
            { name: "oid", width: 100 },
            { name: "product.pid", width: 150 },
            { name: "dispatch.did", width: 100, align: "right" },
            { name: "customer.cid", width: 100, align: "right" }
        ],
        
        jsonReader:{
        	id:"oid"
        },
        
        pager: "#orderspager",
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: "oid",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "客户列表",
        multiselect:false,
        onSelectRow:function(oid){
        	ordersId = oid;
        }
	})
	
	//增加产品
	$("a#ordersaddlink").on("click",function(){
		$("div#ordersdialog").load("orders/add.html",function(){		
			$("div#ordersdialog").dialog({
				title: "增加订单",
				width:700,
				height:500
			});
			
			
			//取得价格信息列表，填充价格信息下拉框选项
			$.getJSON("product/get/all.mvc",function(data){
				if(data.length>0){
					var options="";
					for(var i=0;i<data.length;i++){
						options=options+"<option value='"+data[i].pid+"'>"+data[i].pname+"</option>"
					}
					$("select[name='product.pid']").html(options);
				}
			});
			
			//取得产地信息列表，填充产地信息下拉框选项
			$.getJSON("dispatch/get/all.mvc",function(data){
				if(data.length>0){
					var options="";
					for(var i=0;i<data.length;i++){
						options=options+"<option value='"+data[i].did+"'>"+data[i].did+data[i].dname+"</option>"
					}
					$("select[name='dispatch.did']").html(options);
				}
			});
			
			//取得优惠信息列表，填充优惠信息下拉框选项
			$.getJSON("customer/get/all.mvc",function(data){
				if(data.length>0){
					var options="";
					for(var i=0;i<data.length;i++){
						options=options+"<option value='"+data[i].cid+"'>"+data[i].cname+"</option>"
					}
					$("select[name='customer.cid']").html(options);
				}
			});
			
			
			
			$("form#ordersAddForm").validate({
				 rules: {
				        "oid": {
					        required: true
				        }
				 }
			 });
			
			$("form#ordersAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#ordersdialog").dialog("close");
				$("div#ordersdialog").html("");
				$("table#orderslist").jqGrid().trigger("reloadGrid");
				
			});

			$("button#ordersAddCancelButton").on("click",function(){
				$("div#ordersdialog").dialog("close");
				$("div#ordersdialog").html("");
			})
			
		})
	})
			
	//修改产品
	$("a#ordersmodifylink").on("click",function(){
		if(ordersId==0){
			alert("请选择要修改的订单");
		}
		else{
			$("div#ordersdialog").load("orders/modify.html",function(){
				$("div#ordersdialog").dialog({
					title: "修改订单信息",
					width:500,
					height:500
				});
				
				$.getJSON("orders/get.mvc",{oid:ordersId},function(data){
					$("input[name='oid']").val(data.oid);
				});
				
				//取得价格信息列表，填充价格信息下拉框选项
				$.getJSON("product/get/all.mvc",function(data){
					if(data.length>0){
						var options="";
						for(var i=0;i<data.length;i++){
							options=options+"<option value='"+data[i].pid+"'>"+data[i].pname+"</option>"
						}
						$("select[name='product.pid']").html(options);
					}
				});
				
				//取得产地信息列表，填充产地信息下拉框选项
				$.getJSON("dispatch/get/all.mvc",function(data){
					if(data.length>0){
						var options="";
						for(var i=0;i<data.length;i++){
							options=options+"<option value='"+data[i].did+"'>"+data[i].did+data[i].dname+"</option>"
						}
						$("select[name='dispatch.did']").html(options);
					}
				});
				
				//取得优惠信息列表，填充优惠信息下拉框选项
				$.getJSON("customer/get/all.mvc",function(data){
					if(data.length>0){
						var options="";
						for(var i=0;i<data.length;i++){
							options=options+"<option value='"+data[i].cid+"'>"+data[i].cname+"</option>"
						}
						$("select[name='customer.cid']").html(options);
					}
				});
				
				$("form#ordersModifyForm").validate({
					 rules: {
					        "oid": {
						        required: true
					        }
					 }
				 });
				
				$("form#ordersModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#ordersdialog").dialog("close");
					$("div#ordersdialog").html("");
					$("table#orderslist").jqGrid().trigger("reloadGrid");
				});

				$("button#ordersModifyCancelButton").on("click",function(){
					$("div#ordersdialog").dialog("close");
					$("div#ordersdialog").html("");
				})
			})
		}
	})
	
	//删除客户
		$("a#ordersdeletelink").on("click",function(){
		if(ordersId==0){
			alert("请选择需要删除的产品");
		}else{
			var confrimresult = confirm("确认要删除此产品吗");
			if(confrimresult){
				$.getJSON("orders/delete.mvc",{oid:ordersId},function(data){
					if(data.result=="Y"){
						alert("删除产品成功");
						ordersId = 0;
					}else{
						alert("删除产品失败");
					}
					$("table#orderslist").jqGrid().trigger("reloadGrid");
				});
			}
		}
	})
	//查看产地
	$("#ordersviewlink").on("click",function(){
		
		if(ordersId==0){
			alert("请选择要查看的会员");
		}
		else{
			$("#ordersdialog").load("orders/view.html",function(){
				
				$("#ordersdialog").dialog({
					title: "查看产地",
					width:700,
					height:500
				});
				
				$.getJSON("orders/get.mvc",{oid:ordersId},function(data){
					
					if(data!=null){
						$("#oid1").html(data.oid);
						$("#pid1").html(data.product.pid);
						$("#did1").html(data.dispatch.did);
						$("#cid1").html(data.customer.cid);
					}
				});
					
			});
		}
		
	});
	//查看客户
})
