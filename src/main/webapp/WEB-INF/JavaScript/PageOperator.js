$(function() {
			$(".selectpicker").selectpicker({
				noneSelectedText: '请选择',
				countSelectedText: function(){}
			});
		});
function selectValue() {
//获取选择的值
	alert($('#usertype').selectpicker('val'));
}



