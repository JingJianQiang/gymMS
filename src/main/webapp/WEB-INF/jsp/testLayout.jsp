<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html> <html>
 <head>
 <title>购物车</title>
 <meta http-equiv="Content-Type"
 content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible"
 content="IE=edge" /> 
        <script src="../../Avalon/avalon.js"></script>

 <script>
  var
 vm = avalon.define({
  $id:
 "test",
  arr
 : [
   {
   id:1,
   num:1,
   price:45.5,
   text:'商品1'
   },
   {
   id:2,
   num:1,
   price:8.8,
   text:'商品2'
   }
  ],
  selected
 : ["1"],
  checkAllbool
 : false,
  checkAll
 : function() {
   if
 (this.checked) {
   var
 _arr = [];
   avalon.each(vm.arr,function(index,item){
    _arr[index]
 = item.id+'';
   });
   vm.selected
 = _arr;
   }
 else {
   vm.selected=[];
   }
  },
  plus:
 function(el){
   el.num++;
   vm.cal();
  },
  minus:
 function(el){
   if(el.num>1){
   el.num--;
   vm.cal();
   }
  },
  del:
 function(el){
   vm.arr.remove(el);
  },
  changeNum:
 function(el){
   var
 _value = this.value,
   _reg
 = /^[1-9]\d?$/
   ;
   if(!_reg.test(_value)){
   this.value
 = 1;
   el.num
 = 1;
   }else{
   el.num
 = _value;
   }
   vm.cal();
  },
  total:0,
  cal:
 function(){
   var
 _arr = this.arr,
   _selected
 = this.selected,
   i
 = 0,
   _obj
 = '',
   _prcie
 = 0
   ;
   if(_selected.length){
   for(;i<_selected.length;i++){
    _obj
 = this.findById(_selected[i]) ||{};
    if(_obj.price
 && _obj.num){
    _prcie
 = _prcie + _obj.price * _obj.num;
    }
   }
   }
   this.total
 = _prcie;
   },
  findById:
 function(id){
   if(!id)
 return '';
   var
 i=0,
   _arr
 = this.arr,
   _obj
 = '',
   _id
 = parseInt(id,10)
   ;
   for(;i<_arr.length;i++){
   if(_arr[i].id
 === _id){
    _obj
 = _arr[i];
   }
   }
   return
 _obj;
  }
  });
  vm.selected.$watch("length",
 function(n) {
  vm.checkAllbool
 = n === vm.arr.size()
  vm.cal();
  });
  vm.arr.$watch("length",
 function(n) {
  vm.cal();
  });
  vm.cal();
 </script>
 </head>
 <body ms-controller="test">
 <ul ms-visible="arr.length">
  <li><input type="checkbox"
 ms-click="checkAll" ms-duplex-checked="checkAllbool"/>全选</li>
  <li ms-repeat="arr"
 >
  <input type="checkbox"
 ms-attr-value="el.id" ms-duplex="selected" />
  {{el.text}}
  <input type="text"
 name="" ms-attr-value="el.num" ms-on-input="changeNum(el)">
  <a href="javascript:;" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow"
 ms-click="plus(el)">加</a>
  <a href="javascript:;" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow"
 ms-click="minus(el)">减</a>
  <a href="javascript:;" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow" rel="external nofollow"
 ms-click="del(el)">删除</a>
  <p>单价：{{el.price
 | currency}}</p>
  <p>金额：{{el.num*el.price
 | currency}}</p>
  </li>
 </ul>
 <p>总金额：{{total
 | currency}}</p>
 </body>
</html>