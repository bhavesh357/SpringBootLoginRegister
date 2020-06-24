<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <meta charset="US-ASCII">
    <title>Login Page</title>
    <link href="${pageContext.request.contextPath}/resources/styles.css" rel="stylesheet" />
</head>
<body>
<div class="form-body form-body-login">
    <h1>Login Form</h1>
    <%
        String msg=(String)request.getAttribute("message");
        if(msg==null)
        {
            msg="";
        }
    %>
    <%=msg%>
    <form action="welcome" method="post">
        <div class="form-input">
            <input type="text" name="email" placeholder="Email ID" class="form-input-text">
        </div>
        <br>
        <div class="form-input">
            <input type="password" name="password" placeholder="Password" class="form-input-text">
        </div>
        <br>
        <br>
        <button type="submit" value="Login" class="form-primary">Login</button>
    </form>
    <h4>Not a User?</h4>
    <a href="register"><button>Register</button></a>
</div>
</body>
</html>