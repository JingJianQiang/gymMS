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
		
		<!-- 
		
			VM 代码段
		
		 -->
		<script>	
		var dataSpace = avalon.define({
			$id : "dataSpace",
			dataList : {},//展示的器材List
			typeList : {},//查询出的种类List id-type Map
			totalEquip : {},//所有器材
			currentTotalEquip : {},//当前按条件查找出来的所有
			currentEquipshowing :{},//将要画出来的全部
			usefulEquip : {},//存放未被选中的器材
			typeSelect :[],
			remove : function(el){
/* 				console.log(el.type);	
				dataSpace.dataList.remove(el);
				dataSpace.totalEquip.remove(el);
				dataSpace.currentEquipshowing.remove(el);
				dataSpace.usefulEquip.remove(el); 	
				refreshPage();*/
				 $.ajax({
					type:"post",
					url:"/equip/delete/id",
					data:JSON.stringify({
						equipID : el.id
						}),
				    contentType: 'application/json;charset=utf-8',
					success : function(data){
						//所有本地容器删除此条目
							dataSpace.dataList.remove(el);
							dataSpace.totalEquip.remove(el);
							dataSpace.currentEquipshowing.remove(el);
							dataSpace.usefulEquip.remove(el);
							refreshPage();
						},
					error:function( jqXHR, textStatus, errorThrown ){
						console.log( jqXHR );
						console.log( textStatus );
						console.log( errorThrown );
					}
					})//ajax  
				},
				update : function(el){
					$.ajax({
					type:"post",
					url:"/equip/update",
					data:JSON.stringify({
						equipID : el.id,
						equipType : el.type,
						equipPrice : el.price,
						euqipState : el.isSelect
						}),
					contentType: 'application/json;charset=utf-8',
					success : function(){
						},
					error:function( jqXHR, textStatus, errorThrown ){
						console.log( jqXHR );
						console.log( textStatus );
						console.log( errorThrown );
					}
					})//ajax
				},
			//查询 查询之后的currentEquipshowing = totalEquip
			query : function(){
				$.ajax({
					type:"get",
					url:"/equip/query",
					success : function (data){
						dataSpace.totalEquip = data;
						dataSpace.currentEquipshowing = dataSpace.totalEquip;
						dataSpace.currentTotalEquip = dataSpace.totalEquip;
						equipClassify();
						refreshPage();
					},
					error:function( jqXHR, textStatus, errorThrown ){
						console.log( jqXHR );
						console.log( textStatus );
						console.log( errorThrown );
					}
				});//ajax
			},
			queryByType : function(){	
				console.log("我在dataSpace要查询这几个东西"+ dataSpace.typeSelect);
				$.ajax({
					type:"post",
					url:"/equip/query/type",
					data:JSON.stringify({
						typeSelect : dataSpace.typeSelect
						}),
				    contentType: 'application/json;charset=utf-8',
					success : function (data){
						dataSpace.totalEquip = data;
						dataSpace.currentEquipshowing = dataSpace.totalEquip;
						dataSpace.currentTotalEquip = dataSpace.totalEquip;
						equipClassify();
						refreshPage();
					},
					error:function( jqXHR, textStatus, errorThrown ){
						console.log( jqXHR );
						console.log( textStatus );
						console.log( errorThrown );
					}
				});//ajax
			},
			 queryByID : function(id){
				console.log("我要查询ID");
				$.ajax({
				type : "post",
				url : "equip/query/id",
				data : JSON.stringify({
					equipID : id
					}),
				dataType : "json", 
			    contentType: 'application/json;charset=utf-8',
				success : function(data){
					dataSpace.totalEquip = data;
					dataSpace.currentEquipshowing = dataSpace.totalEquip;
					dataSpace.currentTotalEquip = dataSpace.totalEquip;
					equipClassify();
					refreshPage();
				},
				error:function( jqXHR, textStatus, errorThrown ){
					console.log( jqXHR );
					console.log( textStatus );
					console.log( errorThrown );
				}	
				})//ajax
			}, 

			//租赁
			order : function(){		
				var selectData = [];
				for(let i in dataSpace.dataList){//遍历出被勾选的
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
					}),		
					contentType: "application/json",				
					dataType : "json", 
					success : function(data){
						if(data==""){//没后台数据，全部成功
							dataSpace.query();
						}
						else{//接收后台数据，有失败的
							let win = confirm("ID号为"+data+"已经被租用了");
							if(win == true){
								dataSpace.query();
							}
							else{
								dataSpace.query();
							}
						}
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
				},
			//加载完成时查询所有器材id-类型名，并且显示在bootstrap-select下拉框中
			queryType : function(){
				$.ajax({
					type:"get",
					url:"/equip/query/typeMap",
					success : function (data){
						dataSpace.typeList = data;
						for(let i  = 0; i < data.length; i++){
				             $('.selectpicker').append("<option>" + data[i].name + "</option>");  
							}
						$('#usertype').selectpicker('refresh');  
						$('#usertype').selectpicker('render');	 
					},
					error : function( jqXHR, textStatus, errorThrown ){
						console.log( jqXHR );
						console.log( textStatus );
						console.log( errorThrown );
					}
				})//ajax
			}
		});//vm
		</script>
		
		<!-- 
		
			JavaScript 代码段
			JQuery 代码段
		
		 -->
		<script type="text/javascript">
		/* javaScript作用域 */
		var pageIndex = 0;	
		var pageScale = 5;

//		刷新页面操作	
		function refreshPage(operator){
			if(operator == "down"){//下一页
				let pageMax = Math.ceil(dataSpace.currentEquipshowing.length / pageScale);
				if( pageIndex < pageMax-1){
					pageIndex ++;	
				}
			}
			if(operator == "up"){//上一页
				if(pageIndex > 0 ){
					pageIndex --;
				}
			}
			let pageStart = pageIndex * pageScale;
			let pageEnd = (pageIndex+1) * pageScale;
			if(pageEnd >= dataSpace.currentEquipshowing.length){
				pageEnd = dataSpace.currentEquipshowing.length;
			}
			dataSpace.dataList=[]; 
			for(let index = pageStart ; index < pageEnd ; index++){
				dataSpace.dataList.push(dataSpace.currentEquipshowing[index]);
			}	
		}

		//器材根据状态分类
		 function equipClassify(){
			dataSpace.usefulEquip = [];
			for(let j =0 ; j < dataSpace.currentEquipshowing.length; j++){
				let index = 0;
				if(dataSpace.currentEquipshowing[j].isSelect == false){//未被选中，即可用的
					dataSpace.usefulEquip.push( dataSpace.currentEquipshowing[j] );
					index++;
					//console.log("Tcyily"+dataSpace.dataList[j].type);
				}
			}
			/* console.log("所有："+JSON.stringify(dataSpace.currentEquipshowing));
			console.log("可用的有："+JSON.stringify(dataSpace.usefulEquip)); */
		} 
		//
	 	//正则表达式匹配本地数据
		function regaxpID( id ){
			let reg = eval("/"+id+"/")
			dataSpace.currentEquipshowing = [];
			for(let i = 0 ; i < dataSpace.totalEquip.length; i++){
				if(reg.test(dataSpace.totalEquip[i].id)){
					dataSpace.currentEquipshowing.push(dataSpace.totalEquip[i]);
				}
			}
			refreshPage();
		} 

		/* Jquery作用域 */
		$(function() {
	 		
			//加载完后显示所有可以选择的器材种类
			function onLoad(){
				$("#showState").prop("checked",true);
				$("#equipID").val("器材ID");
				dataSpace.query();
				dataSpace.queryType();
			}
			window.onload = onLoad();
			
				

			//点击查询时判断查询方式
			$("#queryBtn").click(function(){
				dataSpace.typeSelect = $('#usertype').selectpicker('val');				
				if(dataSpace.typeSelect != null){	
					dataSpace.queryByType();
					}
				else {
					dataSpace.query();
				}
				$("#showState").prop("checked",true);
			});

			//切换只显示可用
			$("#showState").click(function(){
				if($("#showState").prop("checked")==true){//展示全部
						console.log("dafsdf"); 
						dataSpace.currentEquipshowing = dataSpace.currentTotalEquip;
						refreshPage();
					}
				else{//展示可用的
						dataSpace.currentEquipshowing = dataSpace.usefulEquip;
						refreshPage();
					}
			});
			//分页
			//下一页
			$("#pageDown").click(function(){
				refreshPage("down");
			});
			//上一页
			$("#pageUp").click(function(){
				refreshPage("up");
			});
			
			//id搜索框
			/*  $('#equipID').bind('keydown', function (event) {
	            var event = window.event || arguments.callee.caller.arguments[0];
	            if (event.keyCode == 13){
	            	let equipID = $('#equipID').val();
	                if(/^[0-9]+$/.test( equipID )){
	                	dataSpace.queryByID( equipID );
	                }
	                else{
	                	dataSpace.query();
	                }
	            }
	        });*/
	        $("#equipID").focus('leave', function(event){
	        	 $('#equipID').val("");
		        })
			 $("#equipID").blur('leave', function (event) {
	            let equipID = $('#equipID').val();//获取id框输入
                if(/^[0-9]+$/.test( equipID )){//有输入且为数字时查出本地数据中id匹配的
                	regaxpID(equipID);    
                	$('#equipID').val("查询中");
                }	
                else{//没输入或输入不为数字时 显示当前条件的全部
                	dataSpace.currentEquipshowing = dataSpace.currentTotalEquip;
                	refreshPage();
                	$('#equipID').val("器材ID");
                }    
	        }); 
	        $("#addBtn").click(function(){
				console.log("点击了添加")
				window.open("./adder","_blank - URL","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=400, height=400","true"); 
			});


	       /*  $(function remove(el){
				console.log("name"+el.name);
			}) */
		})
		</script>
		
   </head>
   <body ms-controller="dataSpace">      
   <div class="container">
  	<button type="button" id = "addBtn" class="btn btn-default" >增加</button>
	<div class="row clearfix">
		<div class="col-md-2 column">
		</div>
		<div class="col-md-8 column">
			<h2 class="text-center">
				器材管理系统
			</h2>
			<div class="row clearfix">
				<div class="col-md-1 column" >
				 	&nbsp;&nbsp;
			 		<input type="checkbox" id="showState" />
			 		<label style="font-weight:110;font-size:62%;style="vertical-align:middle">显示全部</label>		
				</div> 
				<div class="col-md-4 column" >
				 	<select id="usertype" name="usertype" class="selectpicker show-tick form-control" 
				 			multiple data-max-options="3" data-live-search="true">
					</select>
				</div>
			 	<div class="col-md-2 column">
			 		<input type="equipID" class="form-control" id="equipID" value="器材ID"   onfocus="if(value=='器材ID'){value=''}" onblur="if(value==''){value='器材ID'}"/>
				</div> 
				<div class="col-md-1 column" >
				</div> 
				<div class="col-md-4 column">
					 <button type="button" id = "queryBtn" class="btn btn-default"  >查询</button>
				</div>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>序号</th>
						<th>器材编号</th>
						<th>器材种类</th>
						<th>器材价格<th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>	
					<tr ms-for="(index,data) in @dataList">
						<td>{{index}}</td>
						<td>{{data.id}}</td>
						
						<td>
						<input type="text" class="form-control" id="exampleInput1" ms-duplex = data.type /> 
						</td>
						<td><input type="text" class="form-control" id="exampleInput2" ms-duplex = data.price /></td>
						<td><input type = "checkbox" ms-duplex-checked="data.isSelect"  data-duplex-changed="@select" ></td>
						<td><a href="javascript:void(0)" ms-click="@update(data)">修改</a></td>
						<td><a href="javascript:void(0)" ms-click="@remove(data)">删除</a></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td><button type="button" id = "pageUp" class="btn btn-default"  >上一页</button></td>
						<td><button type="button" id = "pageDown" class="btn btn-default"  >下一页</button></td>
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
