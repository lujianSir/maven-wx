<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>菜单设置</h3>
		<div id='table'>
 
			先填写你的账号信息:<br /> AppId <input type="input" id="appId" name="appId"
				value="" class="px" style="width: 200px"/> AppSecret <input type="input"
				id="appSecret" name="appSecret" value="" class="px" style="width: 300px"/>
      <button type="button" id="addMainItem" class="btnGreens"
        >再添加一个一级菜单</button><br />
			<table id="tableMainItem1">
				<tr>
					<td>菜单名称</td>
					<td>菜单类型</td>
					<td>值</td>
					<td>操作</td>
				</tr>
				<tr>
					<td><input id="mainItemId" type="text" name="mainName[0]" /></td>
					<td><select name="mainType[0]">
							<option value="sub_button">子菜单</option>
							<option value="view">view</option>
							<option value="click">click</option>
					</select></td>
					<td><input type="text" name="mainKey[0]" disabled="true" /></td>
					<td><input type="button" class='btnGreens' id="addSubItem"
						value="添加子菜单" /> <input type="button" class='btnGreens'
						id="delThisMainItem" value="删除主项" /></td>
				</tr>
			</table>
			<br /><button type="submit" id="submit" class="btnGreens"
				>点击开始创建菜单</button>
		</div>
</body>
</html>
<script src="http://libs.baidu.com/jquery/1.8.3/jquery.js"></script>
<script>
$(function(){
	
    function checkAddMainItemState() {
      if ($('table').length < 3) {
          $('#addMainItem').attr('disabled', false);
      }
    }
 
    function checkAddSubItemState(tableId) {
      if ($('#'+tableId+'>tbody').children().length < 7)
        $('#'+tableId+'>tbody').find('#addSubItem').attr('disabled', false);
    }
 
    $('select').change(function() {
 
        $(this).parent().next().children().attr('disabled', $(this).val() == 'sub_button');
    });
 
    $('#addMainItem').click(function() {
        var tableMainItem = $('table:last');
        var tempTable = tableMainItem
        var newTableMainItem = tableMainItem.clone(true);
        
        var thisMainItemTr = newTableMainItem.find("tbody:eq(0)>tr:eq(1)");
        thisMainItemTr.find('td:eq(2)>input').attr("disabled", thisMainItemTr.find('select').val() == "sub_button");
 
        var newTableMainItemId = 'tableMainItem' + (parseInt($('table:last').attr('id').substring(13,14)) + 1);
        newTableMainItem.attr('id', newTableMainItemId);
        $('table:last').after(newTableMainItem);
        var firstValuedTr = newTableMainItem.find("tbody:eq(0)>tr:eq(1)");
        var firstValuedTrTdInputNameIndex = parseInt(newTableMainItemId.substring(13, 14)) - 1;
        firstValuedTr.find("td:eq(0)").find(":input").attr('name', "mainName[" + firstValuedTrTdInputNameIndex + "]");
        firstValuedTr.find("td:eq(1)").find("select").attr('name', "mainType[" + firstValuedTrTdInputNameIndex + "]");
        firstValuedTr.find("td:eq(2)").find(":input").attr('name', "mainKey[" + firstValuedTrTdInputNameIndex + "]");
        $('#addMainItem').attr('disabled', $('table').length >= 3);
        
    });
 
    $('#addSubItem').click(function() {
    	 
      var thisMainItemTr = $(this).parent().parent();
      trSelectValue = thisMainItemTr.find('select').val();
      if (trSelectValue != 'sub_button') {
      	alert("只有子菜单类型才能添加子菜单!");
      	return false;
      }
      
      thisMainItemTr.find("select").attr("disabled", true);
      var thisTable = thisMainItemTr.parent().parent();
      var oldAddId = $(this).attr('id');
      var newAddId = oldAddId + "Curr";
      $(this).attr('id', newAddId);
      var newTrStr = '<tr>'
          + '<td><input type="text" name="subName[0][0]" /></td>'
          + '<td align="center"><select name="subType[0][0]"><option value="view">view</option><option value="click">click</option></select></td>'
          + '<td><input type="text" name="subKey[0][0]" /></td>'
          + '<td><input type="button" id="delThisSubItem" value="删除子项" /></td>'
          + '</tr>' ;
      var newTr = thisMainItemTr.clone(true).replaceWith(newTrStr);
      thisMainItemTr.parent().parent().append(newTr);
      var thisTableNum = parseInt(thisTable.attr('id').substring(13, 14)) - 1;
      var newTrIndex = newTr.index() - 1 - 1;
      newTr.find("td:eq(0)").find(":input").attr('name', "subName[" + thisTableNum + "][" + newTrIndex + "]");
      newTr.find("td:eq(1)").find("select").attr('name', "subType[" + thisTableNum + "][" + newTrIndex + "]");
      newTr.find("td:eq(2)").find(":input").attr('name', "subKey[" + thisTableNum + "][" + newTrIndex + "]");
 
      $('#addSubItemCurr').attr('disabled',thisMainItemTr.parent().children().length >= 7);
      $(this).attr('id', oldAddId);
      thisTable.find('tr:not(:last)').find('#delThisSubItem').attr('disabled', true);
      thisTable.find('tr:last').find('#delThisSubItem').attr('disabled', false);
    });
 
    $('#delThisMainItem').click(function() {
        
    	var table = $(this).parent().parent().parent().parent();
    	
      if ($(this).parent().parent().parent().children().length > 2) {
        alert('请先删除子项');
        return false;
      }
 
    	if (table.attr('id') == 'tableMainItem1') {
    		alert("第一个主菜单不可以删除！");
    		return false;
    	}
    	
      else {
          // 后面一项的id
          var thisTableId = $(this).parent().parent().parent().parent().attr('id');
       //   alert(thisTableId);
          var nextTableId = 'tableMainItem' + (parseInt(thisTableId.substring(13, 14)) + 1);
       //   alert(nextTableId);
          $(this).parent().parent().parent().parent().remove();
          // 提升后面一项
          $('#'+ nextTableId).attr('id', thisTableId);
          checkAddMainItemState();
      }
    });
 
    $('#delThisSubItem').live('click',function() {
        //  alert($(this).parent().parent().html()); // tr
        var thisTr = $(this).parent().parent();
        var thisTable = thisTr.parent().parent();
        $(this).parent().parent().remove();
        
        trNum = thisTable.find('tr').length;
        if (trNum <= 2) {
          thisTable.find('select').attr('disabled', false);       	
        }
       
        checkAddSubItemState(thisTable.attr('id'));
        thisTable.find('tr:not(:last)').find('#delThisSubItem').attr('disabled', true);
        thisTable.find('tr:last').find('#delThisSubItem').attr('disabled', false); 
        
    });
    
    
    $('#submit').on('click', function() {
    	
      var goonFlag = true;
      
      if ($.trim($("#appId").val()).length <= 0 || $.trim($("#appSecret").val()).length <= 0) {
      	alert("请输入AppId 与 AppSecret！");
      	return false;
      }
      
      if ($.trim($("#mainItemId").val()).length <= 0) {
        alert("请输入菜单名称！");
        return false;
      }
      
      $('table').each(function() {
        var tr = $(this).find("tbody:eq(0)>tr:eq(1)");
        tr.find('td:eq(1)').find('select').attr('disabled', false);
        var td2 = tr.find("td:eq(1)");
        trNum = tr.parent().children().length;
        if (td2.find('select').val() == 'sub_button' && trNum <= 2 ) {
          alert("如果你选择了子菜单类型，请务必添加子菜单, 否则菜单将创建不成功！");
          goonFlag = false;
        }
      });
      if (!goonFlag)
        return goonFlag;
    });
 
});


</script>