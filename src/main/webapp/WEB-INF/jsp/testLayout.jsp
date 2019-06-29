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

 
 </head>
<!DOCTYPE html>
<html>

<head>
    <title>avalon 例子</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/css/bootstrap-select.min.css">
		
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/js/i18n/defaults-*.min.js"></script>		
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.10/dist/js/bootstrap-select.min.js"></script>
        <script src="../../Avalon/avalon.js"></script>
    <script type="text/javascript" src="avalon.js"></script>
    <style type="text/css">
        body
        {
            font-size: 12px;
        }

        table td
        {
            padding: 3px;
            border: solid 1px #ddd;
            height: 30px;
        }

        .selected
        {
            background-color: #ddd;
        }

        .hide
        {
            display: none;
        }
    </style>
    <script>
        var items = ["name", "code"];
        var model = avalon.define({
            $id: "test",
            array: [
            ],
            remove: function(){},
            trclick: function (el) {
                if (!el.selected) {
                    if (validModel(items, model)) el.selected = true;
                }
            },
            add: function () {
                if (validModel(items, model)) {
                    model.array.push({
                        name: {
                            value: "",
                            valid: true,
                            msg: "",
                            rules: [{
                                rule: "notnull"
                            }, {
                                rule: /^(.|\n){0,5}$/,
                                msg: "长度不能大于5",
                                valid: true
                            }]
                        },
                        code: {
                            value: "",
                            valid: true,
                            msg: "",
                            rules: [{
                                rule: "notnull"
                            }, {
                                rule: /^[+-]?\d*\.?\d+$/,
                                msg: "请填写数字",
                                valid: true
                            }, {
                                rule: /^(.|\n){0,5}$/,
                                msg: "长度不能大于5",
                                valid: true
                            }]
                        },
                        selected: true
                    });
                }
            }
        });
    </script>
    <script>
        //验证
        function valid(items, el) {
            var bl = true;
            for (var k = 0; k < items.length; k++) {
                var item = el[items[k]];
                item.valid = true;
                item.msg = "";
                for (var i = 0; i < item.rules.length; i++) {
                    if (item.rules[i].rule == "notnull") {
                        if (item.value == "") {
                            bl = false;
                            item.valid = false;
                            item.msg += "必填；";
                        }
                    }
                    else {
                        var reg = new RegExp(item.rules[i].rule);
                        if (item.value != "" && !reg.test(item.value)) {
                            bl = false;
                            item.valid = false;
                            item.rules[i].valid = false;
                            item.msg += item.rules[i].msg + "；";
                        }
                    }
                }
            }
            return bl;
        }

        //验证
        function validModel(items, model) {
            var bl = true;
            for (var i = 0; i < model.array.length; i++) {
                if (model.array[i].selected) {
                    bl = valid(items, model.array[i]);
                    if (bl) {
                        model.array[i].selected = false;
                    }
                }
            }
            return bl;
        }
    </script>
</head>
<body style="background-color: #fff;">
    <div ms-controller="test">
        <input type="button" value="添加" ms-click="@add" />
        <br />
        <br />
        <table cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
            <thead>
                <tr>
                    <td style="width: 350px;">名称
                    </td>
                    <td style="width: 350px;">编号
                    </td>
                    <td style="width: 40px;">操作
                    </td>
                    <td style="width: 200px;">输入结果
                    </td>
                </tr>
            </thead>
            <tbody ms-repeat-el="array">
                <tr ms-class="selected:el.selected" ms-if="el.selected" ms-click="trclick(el)">
                    <td>
                        <input type="text" ms-duplex="el.name.value" />
                        <span ms-class="hide:el.name.valid" style="color: red; font-size: 12px;">{{el.name.msg}}</span>
                    </td>
                    <td>
                        <input type="text" ms-duplex="el.code.value" />
                        <span ms-class="hide:el.code.valid" style="color: red; font-size: 12px;">{{el.code.msg}}</span>
                    </td>
                    <td>
                        <a href="javascript:void(0)" ms-click="$remove">删除</a>
                    </td>
                    <td>{{el.name?el.name.value+"："+el.code.value:""}}   
                    </td>
                </tr>
                <tr ms-class="selected:el.selected" ms-if="!el.selected" ms-click="trclick(el)">
                    <td>{{el.name.value}}
                    </td>
                    <td>{{el.code.value}}
                    </td>
                    <td>
                        <a href="javascript:void(0)" ms-click="$remove">删除</a>
                    </td>
                    <td>{{el.name?el.name.value+"："+el.code.value:""}}   
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
</html>