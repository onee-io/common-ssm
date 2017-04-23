<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="common/head.jsp"%>
    <title>用户信息列表</title>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>用户信息列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>用户ID</th>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>余额</th>
                    <th>是否VIP</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="info" items="${userInfos}">
                    <tr>
                        <td>${info.id}</td>
                        <td>${info.userName}</td>
                        <td>${info.age}</td>
                        <td>${info.balance}</td>
                        <td>
                            <c:if test="${info.vip}">是</c:if>
                            <c:if test="${!info.vip}">否</c:if>
                        </td>
                        <td>
                            <fmt:formatDate value="${info.gmtCreate}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                            <fmt:formatDate value="${info.gmtModified}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                            <button class="btn btn-info btn-sm">编辑</button>
                            <button class="btn btn-danger btn-sm">删除</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>