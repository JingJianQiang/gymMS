<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/css/bootstrap-select.min.css">
		
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/js/i18n/defaults-*.min.js"></script>		
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/js/bootstrap-select.min.js"></script>
        <script src="../../Avalon/avalon.js"></script>
				
		<script src="../JavaScript/PageOperator.js"></script>
		<script>		
		var dataSpace = avalon.define({
			$id : "dataSpace",
			dataList : {},
			//selectList : [],
			//查询
			query : function(){
				$.ajax({
					type:"get",
					url:"/equip/query",
					success : function (data){
						console.log("success"+data);
						dataSpace.dataList = data;
					},
					error:function( jqXHR, textStatus, errorThrown ){
						console.log( jqXHR );
						console.log( textStatus );
						console.log( errorThrown );
					}
				});//ajax
			},
			//勾选
			select : function(e){
				/*let i =0;
				dataSpace.dataList.forEach(function(data){
						dataSpace.selectList[i] = data.isSelect;
						i++;
					})
					console.log(dataSpace.selectList);*/
				},
			//租赁
			order : function(){		
				var selectData = [];
				for(var i in dataSpace.dataList){
					if(dataSpace.dataList[i].isSelect == true){
						selectData.push(dataSpace.dataList[i].id);
					}
				}
				console.log("selectData为"+selectData);
				$.ajax({
					type : "post",
					url : "/equip/order",
					data :  JSON.stringify({
						selectList : selectData
					}
					),		
					contentType: "application/json",				
					dataType : "json", 
					success : function(data){
						console.log("success"+data);
						},
					error : function( jqXHR, textStatus, errorThrown ){
						console.log( jqXHR );
						console.log( textStatus );
						console.log( errorThrown );
					}
				});//ajax
				
			},
			//取消
			cancel : function(){
				//dataSpace.selectList.clear();
				dataSpace.dataList.forEach(function(data){
					data.isSelect = false;
					})	
				}
		});//vm
		/*dataSpace.$watch("selectList.length",function(){
			console.log(dataSpace.selectList);
			})*/
		</script>
		
   </head>
   <body ms-controller="dataSpace">      
   <div class="container">
	<div class="row clearfix">
		<div class="col-md-2 column">
		</div>
		<div class="col-md-8 column">
			<h2 class="text-center">
				器材管理系统
			</h2>
			<div class="row clearfix">
				<div class="col-md-8 column" >
			 	<select id="usertype" name="usertype" class="selectpicker show-tick form-control" multiple data-max-options="3" data-live-search="true">
					<option value="0" >苹果</option>
					<option value="1" >菠萝</option>
					<option value="2" >香蕉</option>
					<option value="3" >火龙果</option>
				</select>
				</div>
				<div class="col-md-4 column">
					 <button type="button" class="btn btn-default"  ms-click = "@query">查询</button>
				</div>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>器材种类</th>
						<th>场地种类</th>
						<th>器材价格<th>
					</tr>
				</thead>
				<tbody>	
					<tr ms-for="data in @dataList">
						<td>{{data.id}}</td>
						<td>{{data.type}}</td>
						<td>{{data.price}}<td>
						<td><input type = "checkbox" ms-duplex-checked="data.isSelect"  data-duplex-changed="@select" ></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="">屠龙宝刀</a></td>
						<td><a href="">点击就送</a></td>
					</tr>
				</tbody>
			</table>
			<div class="row clearfix">
				<div class="col-md-4 column">
					 <button type="button" class="btn btn-default" ms-click = "@cancel">取消</button>
				</div>
				<div class="col-md-4 column">
				</div>
				<div class="col-md-4 column">
					 <button type="button" class="btn btn-default"	ms-click = "@order">租赁</button>
				</div>
			</div>
		</div>
		<div class="col-md-2 column"></div>
	</div>
</div>
</body>
</html>
