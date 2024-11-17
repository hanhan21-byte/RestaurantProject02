<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
        }
        .container h1 {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }
        .form-group label {
            display: inline-block;
            width: 100px;
            text-align: right;
            margin-right: 10px;
            font-size: 14px;
            color: #333;
        }
        .form-group input {
            width: 300px;
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .form-actions {
            margin-top: 20px;
        }
        .form-actions button {
            padding: 10px 20px;
            font-size: 14px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-actions a {
            margin-left: 20px;
            font-size: 14px;
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>LOGIN</h1>
        <form action="<%=request.getContextPath()%>/login" method="post" name="frm-login">
            <div class="form-group">
                <label for="username">User Name *</label>
                <input type="text" id="username" name="username" placeholder="Enter user name" required>
                <div id="usernameError" class="error"></div>
            </div>
            <div class="form-group">
                <label for="password">Password *</label>
                <input type="password" id="password" name="password" placeholder="Enter password" required>
                <div id="passwordError" class="error"></div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Login</button>
                <a href="<%=request.getContextPath()%>/register">Click here to Register</a>
            </div>
        </form>
    </div>
</body>
</html>