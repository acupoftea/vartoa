<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>

div{margin:10 auto}
.selectbox{width:500px;height:220px;margin:0px auto;}
.selectbox div{float:left;}
.selectbox .select-bar{padding:0 20px;}
.selectbox .select-bar select{width:150px;height:200px;border:1px #A0A0A4 solid;padding:4px;font-size:14px;font-family:"microsoft yahei";}
.btn-bar{}
.btn-bar p{margin-top:16px;}
.btn-bar p .btn{width:50px;height:30px;cursor:pointer;font-family:simsun;font-size:14px;}
</style>

<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="plugins/angularJs/angular.min.js"></script>

<script type="text/javascript">
$(function(){	
	//移到右边
	$('#add').click(function(){
		//先判断是否有选中
		if(!$("#select1 option").is(":selected")){			
			alert("请选择需要移动的选项")
		}
		//获取选中的选项，删除并追加给对方
		else{
			$('#select1 option:selected').appendTo('#select2');
		}	
	});
	
	//移到左边
	$('#remove').click(function(){
		//先判断是否有选中
		if(!$("#select2 option").is(":selected")){			
			alert("请选择需要移动的选项")
		}
		else{
			$('#select2 option:selected').appendTo('#select1');
		}
	});
	
	//全部移到右边
	$('#add_all').click(function(){
		//获取全部的选项,删除并追加给对方
		$('#select1 option').appendTo('#select2');
	});
	
	//全部移到左边
	$('#remove_all').click(function(){
		$('#select2 option').appendTo('#select1');
	});
	
	//双击选项
	$('#select1').dblclick(function(){ //绑定双击事件
		//获取全部的选项,删除并追加给对方
		$("option:selected",this).appendTo('#select2'); //追加给对方
	});
	
	//双击选项
	$('#select2').dblclick(function(){
		$("option:selected",this).appendTo('#select1');
	});
	
});
</script>

<div class="selectbox">
<div class="select-bar">
    <select multiple="multiple" id="select1">
        <option value="1">超级管理员</option>
        <option value="2">普通管理员</option>
        <option value="3">信息发布员</option>
        <option value="4">财务管理员</option>
        <option value="5">产品管理员</option>
        <option value="6">资源管理员</option>
        <option value="7">系统管理员</option>
        <option value="8">超级管理员</option>
        <option value="9">普通管理员</option>
    </select>
</div>

<div class="btn-bar">
    <p><span id="add"><input type="button" class="btn" value=">" title="移动选择项到右侧"/></span></p>
    <p><span id="add_all"><input type="button" class="btn" value=">>" title="全部移到右侧"/></span></p>
    <p><span id="remove"><input type="button" class="btn" value="<" title="移动选择项到左侧"/></span></p>
    <p><span id="remove_all"><input type="button" class="btn" value="<<" title="全部移到左侧"/></span></p>
</div>
<div class="select-bar">
    <select multiple="multiple" id="select2"></select>
</div>	
</div>
