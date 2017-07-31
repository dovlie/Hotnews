$(document).ready(function(){
	var customerId = 0;
	$("#customerlist").jqGrid({
        url: "customer/get/all/pages.mvc",
        datatype: "json",
        width:930,
        height:300,
        mtype: "GET",
        colNames: ["客户编号", "客户名称", "客户类型", "联系电话","会员类型","折扣率"],
        colModel: [
            { name: "cid", width: 100 },
            { name: "cname", width: 150 },
            { name: "ctype", width: 100, align: "right" },
            { name: "cphone", width: 100, align: "right" },
            { name: "vip.memType", width: 100, align: "right" },
            { name: "vip.depRa", width: 100, align: "right" },
        ],
        
        jsonReader:{
        	id:"cid"
        },
        
        pager: "#customerpager",
        rowNum: 10,
        rowList: [10, 20, 30],
        sortname: "cid",
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        autoencode: true,
        caption: "客户列表",
        multiselect:false,
        onSelectRow:function(cid){
        	customerId = cid;
        }
	})
	
	//增加客户
	$("a#customeraddlink").on("click",function(){
		$("div#customerdialog").load("customer/add.html",function(){		
			$("div#customerdialog").dialog({
				title: "增加客户",
				width:700,
				height:500
			});
			
			//取得会员信息列表，填充会员信息下拉框选项
			$.getJSON("vip/get/all.mvc",function(data){
				if(data.length>0){
					var options="";
					for(var i=0;i<data.length;i++){
						options=options+"<option value='"+data[i].management+"'>"+data[i].memType+"</option>"
					}
					$("select[name='vip.management']").html(options);
				}
			});
			
			$("form#customerAddForm").validate({
				 rules: {
				        "cid": {
					        required: true
				        },
				        "cname":{
				        	required: true
				        },
				        "ctype":{
				        	required: true
				        },
				        "cphone":{
				        	required: true
				        }
				 }
			 });
			
			$("form#customerAddForm").ajaxForm(function(data){
				if(data.result=="Y"){
					alert("添加成功");
				}else{
					alert("添加失败");
				}
				$("div#customerdialog").dialog("close");
				$("div#customerdialog").html("");
				$("table#customerlist").jqGrid().trigger("reloadGrid");
				
			});

			$("button#customerAddCancelButton").on("click",function(){
				$("div#customerdialog").dialog("close");
				$("div#customerdialog").html("");
			})
			
		})
	})
			
	//修改客户
	$("a#customermodifylink").on("click",function(){
		if(customerId==0){
			alert("请选择要修改的客户");
		}
		else{
			$("div#customerdialog").load("customer/modify.html",function(){
				$("div#customerdialog").dialog({
					title: "修改客户",
					width:500,
					height:500
				});
				
				$.getJSON("customer/get.mvc",{cid:customerId},function(data){
					$("input[name='cid']").val(data.cid);
					$("input[name='cname']").val(data.cname);
					$("input[name='ctype']").val(data.ctype);
					$("input[name='cphone']").val(data.cphone);
				});
				
				//取得会员信息列表，填充会员信息下拉框选项
				$.getJSON("vip/get/all.mvc",function(data){
					if(data.length>0){
						var options="";
						for(var i=0;i<data.length;i++){
							options=options+"<option value='"+data[i].management+"'>"+data[i].memType+"</option>"
						}
						$("select[name='vip.management']").html(options);
					}
				});
				
				$("form#customerAddForm").validate({
					 rules: {
					        "cid": {
						        required: true
					        },
					        "cname":{
					        	required: true
					        },
					        "ctype":{
					        	required: true
					        },
					        "cphone":{
					        	required: true
					        }
					 }
				 });
				
				$("form#customerModifyForm").ajaxForm(function(data){
					if(data.result=="Y"){
						alert("修改成功");
					}else{
						alert("修改失败");
					}
					$("div#customerdialog").dialog("close");
					$("div#customerdialog").html("");
					$("table#customerlist").jqGrid().trigger("reloadGrid");
				});

				$("button#customerModifyCancelButton").on("click",function(){
					$("div#customerdialog").dialog("close");
					$("div#customerdialog").html("");
				})
			})
		}
	})
	
	//删除客户
		$("a#customerdeletelink").on("click",function(){
		if(customerId==0){
			alert("请选择需要删除的客户");
		}else{
			var confrimresult = confirm("确认要删除此客户吗");
			if(confrimresult){
				$.getJSON("customer/delete.mvc",{cid:customerId},function(data){
					if(data.result=="Y"){
						alert("删除客户成功");
						customerId = 0;
					}else{
						alert("删除客户失败");
					}
					$("table#customerlist").jqGrid().trigger("reloadGrid");
				});
			}
		}
	})
	//查看产地
	$("a#customerviewlink").on("click",function(){
		
		if(customerId==null){
			alert("请选择要查看的会员");
		}
		else{
			$("div#customerdialog").load("customer/view.html",function(){
				
				$("div#customerdialog").dialog({
					title: "查看产地",
					width:700,
					height:500
				});
				
				$.getJSON("customer/get.mvc",{cid:customerId},function(data){
					
					if(data!=null){
						$("#cid").html(data.cid);
						$("#cname").html(data.cname);
						$("#ctype").html(data.ctype);
						$("#cphone").html(data.cphone);
						$("#vid").html(data.vip.memType);
						$("#vre").html(data.vip.depRa);
						
					}
				});
					
			});
		}
		
	});
	//查看客户
})
