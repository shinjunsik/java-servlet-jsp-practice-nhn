<%--
  Created by IntelliJ IDEA.
  User: chosun-nhn29
  Date: 26. 2. 26.
  Time: 오전 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
    <tbody>
    <!-- todo view 구현 -->
        <table>
            <tr>
                <td>아이디</td>
                <td>${student.id}</td>
            </tr>
            <tr>
                <td>이름</td>
                <td>${student.name}</td>
            </tr>
            <tr>
                <td>성별</td>
                <td>${student.gender}</td>
            </tr>
            <tr>
                <td>나이</td>
                <td>${student.age}</td>
            </tr>
            <tr>
                <td>생성일</td>
                <td>${formattedDate}</td>
            </tr>
        </table>
    </tbody>
</table>
<ul>
    <li><a href="/student/list.do">리스트</a></li>
    <li>
        <!-- todo ${update_link} 설정 c:url -->
        <c:url var="update_link" value="/student/update.do">
            <c:param name="id" value="${student.id}"/>
        </c:url>
        <a href="${update_link}">수정</a>
    </li>
    <li>
        <!-- todo 삭제버튼 구현, method=post -->
        <button type="submit" form="deleteForm">삭제</button>
        <form id="deleteForm" method="post" action="/student/delete.do">
            <input type="hidden" name="id" value="${student.id}"/>
        </form>
     </li>

 </ul>

</body>
</html>