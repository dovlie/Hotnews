$(document).ready(function(){
	var productId = 0;
	$("#productlist").jqGrid({
        url: "product/get/all/pages.mvc",
        datatype: "json",
        width:930,
        height:300,
        mtype: "GET",
        colNames: ["产品编号", "产品名称", "产品价格", "产品优惠类型","国家","省/州/郡","市","县/区","产品仓库","产品类型"],
        colModel: [
            { name: "pid", width: 100 },
            { name: "pname", width: 150 },
            { name: "price.price", width: 100, align: "right" },
            { name: "promotion.prtype", width: 100, align: "right" },
            { name: "origin.ocountry", width: 100, align: "right" },
            { name: "origin.province", width: 100, align: "right" },
            { name: "origin.city", width: 100, align: "right" },
            { name: "origin.country", width: 100, align: "right" },
            { name: "warehose.waddress", width: 100, align: "right" },
            { name: "producttype.ptname", width: 100, align: "right" }
        ],
        
        jsonReader:{
        	id:"pid"
        },
        
        pager: "#productpager",
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: "pid",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "客户列表",
        multiselect:false,
        onSelectRow:function(pid){
        	productId = pid;
        }
	})
	
	//增加产品
	$("a#productaddlink").on("click",function(){
		$("div#productdialog").load("product/add.html",function(){		
			$("div#productdialog").dialog({
				title: "增加产品",
				width:700,
				height:500
			});
			
			$("input[name='pid']").on("blur",function(){
				var productid=$("input[name='pid']").val();
				$.getJSON("product/checkexist.mvc",{pid:productid},function(rdata){
					if(rdata.result=="Y"){
						alert("此产品编号已经存在");
						$("input[name='pid']").val("")
					}
				});
			});
			
			//取得价格信息列表，填充价格信息下拉框选项
			$.getJSON("price/get/all.mvc",function(data){
				if(data.length>0){
					var options="";
					for(var i=0;i<data.length;i++){
						options=options+"<option value='"+data[i].pid+"'>"+data[i].price+"</option>"
					}
					$("select[name='price.pid']").html(options);
				}
			});
			
			//取得产地信息列表，填充产地信息下拉框选项
			$.getJSON("origin/get/all.mvc",function(data){
				if(data.length>0){
					var options="";
					for(var i=0;i<data.length;i++){
						options=options+"<option value='"+data[i].oid+"'>"+data[i].ocountry+data[i].province+data[i].city+data[i].country+"</option>"
					}
					$("select[name='origin.oid']").html(options);
				}
			});
			
			//取得优惠信息列表，填充优惠信息下拉框选项
			$.getJSON("promotion/get/all.mvc",function(data){
				if(data.length>0){
					var options="";
					for(var i=0;i<data.length;i++){
						options=options+"<option value='"+data[i].prid+"'>"+data[i].prtype+"</option>"
					}
					$("select[name='promotion.prid']").html(options);
				}
			});
			
			
			$.getJSON("producttype/get/all.mvc",function(data){
				if(data.length>0){
					var options="";
					for(var i=0;i<data.length;i++){
						options=options+"<option value='"+data[i].ptid+"'>"+data[i].ptname+"</option>"
					}
					$("select[name='producttype.ptid']").html(options);
				}
			});
			

			$.getJSON("warehouse/get/all.mvc",function(data){
				if(data.length>0){
					var options="";
					for(var i=0;i<data.length;i++){
						options=options+"<option value='"+data[i].wid+"'>"+data[i].waddress+"</option>"
					}
					$("select[name='warehose.wid']").html(options);
				}
			});
			
			$("form#productAddForm").validate({
				 rules: {
				        "pid": {
					        required: true
				        },
				        "pname":{
				        	required: true
				        }
				 }
			 });
			
			$("form#productAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#productdialog").dialog("close");
				$("div#productdialog").html("");
				$("table#productlist").jqGrid().trigger("reloadGrid");
				
			});

			$("button#productAddCancelButton").on("click",function(){
				$("div#productdialog").dialog("close");
				$("div#productdialog").html("");
			})
			
		})
	})
			
	//修改产品
	$("a#productmodifylink").on("click",function(){
		if(productId==0){
			alert("请选择要修改的客户");
		}
		else{
			$("div#productdialog").load("product/modify.html",function(){
				$("div#productdialog").dialog({
					title: "修改产品",
					width:500,
					height:500
				});
				
				$.getJSON("product/get.mvc",{pid:productId},function(data){
					$("input[name='pid']").val(data.pid);
					$("input[name='pname']").val(data.pname);
				});
				
				//取得价格信息列表，填充价格信息下拉框选项
				$.getJSON("price/get/all.mvc",function(data){
					if(data.length>0){
						var options="";
						for(var i=0;i<data.length;i++){
							options=options+"<option value='"+data[i].pid+"'>"+data[i].price+"</option>"
						}
						$("select[name='price.pid']").html(options);
					}
				});
				
				//取得产地信息列表，填充产地信息下拉框选项
				$.getJSON("origin/get/all.mvc",function(data){
					if(data.length>0){
						var options="";
						for(var i=0;i<data.length;i++){
							options=options+"<option value='"+data[i].oid+"'>"+data[i].ocountry+data[i].province+data[i].city+data[i].country+"</option>"
						}
						$("select[name='origin.oid']").html(options);
					}
				});
				
				//取得优惠信息列表，填充优惠信息下拉框选项
				$.getJSON("promotion/get/all.mvc",function(data){
					if(data.length>0){
						var options="";
						for(var i=0;i<data.length;i++){
							options=options+"<option value='"+data[i].prid+"'>"+data[i].prtype+"</option>"
						}
						$("select[name='promotion.prid']").html(options);
					}
				});
				
				$.getJSON("producttype/get/all.mvc",function(data){
					if(data.length>0){
						var options="";
						for(var i=0;i<data.length;i++){
							options=options+"<option value='"+data[i].ptid+"'>"+data[i].ptname+"</option>"
						}
						$("select[name='producttype.ptid']").html(options);
					}
				});
				

				$.getJSON("warehouse/get/all.mvc",function(data){
					if(data.length>0){
						var options="";
						for(var i=0;i<data.length;i++){
							options=options+"<option value='"+data[i].wid+"'>"+data[i].waddress+"</option>"
						}
						$("select[name='warehose.wid']").html(options);
					}
				});
				
				$("form#productAddForm").validate({
					 rules: {
					        "pid": {
						        required: true
					        },
					        "pname":{
					        	required: true
					        }
					 }
				 });
				
				$("form#productModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#productdialog").dialog("close");
					$("div#productdialog").html("");
					$("table#productlist").jqGrid().trigger("reloadGrid");
				});

				$("button#productModifyCancelButton").on("click",function(){
					$("div#productdialog").dialog("close");
					$("div#productdialog").html("");
				})
			})
		}
	})
	
	//删除产品
		$("a#productdeletelink").on("click",function(){
		if(productId==0){
			alert("");
		}else{
			var confrimresult = confirm("确认要删除此产品吗");
			if(confrimresult){
				$.getJSON("product/delete.mvc",{pid:productId},function(data){
					if(data.result=="Y"){
						alert("删除产品成功");
						productId = 0;
					}else{
						alert("删除产品失败");
					}
					$("table#productlist").jqGrid().trigger("reloadGrid");
				});
			}
		}
	})
	
	//查看产品
	$("a#productviewlink").on("click",function(){
		
		if(productId==null){
			alert("请选择要查看的产品");
		}
		else{
			$("div#productdialog").load("product/view.html",function(){
				
				$("div#productdialog").dialog({
					title: "查看产品",
					width:700,
					height:500
				});
				
				$.getJSON("product/get.mvc",{pid:productId},function(data){
					
					if(data!=null){
						$("#pid").html(data.pid);
						$("#pname").html(data.pname);
						$("#price").html(data.price.price);
						$("#origin").html(data.origin.ocountry+data.origin.province+data.origin.city+data.origin.country);
						$("#promotion").html(data.promotion.prtype);
						$("#producttype").html(data.producttype.ptid);
						$("#warehose").html(data.warehose.wid);
					}
				});
					
			});
		}
		
	});
})
