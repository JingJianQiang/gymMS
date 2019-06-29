<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/css/bootstrap-select.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/js/i18n/defaults-*.min.js"></script>		
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/js/bootstrap-select.min.js"></script>
        <script src="../../Avalon/avalon.js"></script>
<title>添加器材</title>
<script type="text/javascript">
	$(function() {
		$("#equipAdder").click(function(){
			console.log("点击了" + $("#equipPrice").val());
			if(/^[0-9]+$/.test( $("#equipPrice").val()	)){
				if($("#equipType").val() == "" || $("#equipType").val() == null){
					let win = confirm("价格必须为数字且类型不能为空");
					return ;
					}
				console.log("大队了");
				$.ajax({
					type : "post",
					url : "/equip/insert",
					contentType: "application/json",				
					dataType : "json", 
					data :  JSON.stringify({
						equipType : $("#equipType").val(),
						equipPrice : $("#equipPrice").val()
					}),	
					success : function(){
						
						},
					error : function( jqXHR, textStatus, errorThrown ){
						console.log( jqXHR );
						console.log( textStatus );
						console.log( errorThrown );
					}
					})
					console.log("添加成功啦")
					let win = confirm("添加成功啦");
					$("#equipType").val("");
					$("#equipPrice").val("");
			}else{
				let win = confirm("价格必须为数字且类型不能为空");
			}  
		});
	})
</script>
</head>
<body>
	<div class="col-md-4 column">
		</div>
		<div class="col-md-4 column">
			<h3>
				添加器材
			</h3>
			<form class="form-horizontal" role="form">
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">器材类型</label>
					<div class="col-sm-10">
						<input type="equipType" class="form-control" id="equipType" />
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">器材价格</label>
					<div class="col-sm-10">
						<input type="equipPrice" class="form-control" id="equipPrice" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						   	<button type="button" id = "equipAdder" class="btn btn-default" >增加</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-4 column">
		</div>
</body>
</html>