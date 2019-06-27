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