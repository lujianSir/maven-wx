<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="weui-cells mt5">
              <div class="weui-cell ">
                <div class="weui-cell__bd">
               	   昵称
                </div>
                <div class="weui-cell__price">${nickname}</div>
              </div>
              <div class="weui-cell">
                <div class="weui-cell__bd">
              	    国家
                </div>
                <div class="weui-cell__price">${country}</div>
              </div>
              <div class="weui-cell">
                <div class="weui-cell__bd">
              	    所在城市
                </div>
                <div class="">${province} ${city}</div>
              </div>
              <div class="weui-cell weui-cell_access">
                <div class="weui-cell__bd">
               		   姓别
                </div>
                <div class="weui-cell__price">
                    <c:choose>
                        <c:when test="${sex==2}">
                         	   女
                        </c:when>
                        <c:when test="${sex==1}">
                          	  男
                        </c:when>
                    </c:choose>
                </div>              
              </div>
 
 
</body>
</html>