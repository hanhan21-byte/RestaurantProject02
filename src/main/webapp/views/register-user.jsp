<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register User</title>
<script src="../resources/js/register-user.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<style>
body {
	font-family: Arial, sans-serif;
}

.container {
	width: 50%;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

h1 {
	text-align: center;
}

form {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input[type="text"], input[type="email"], input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid black; /* Default border color */
	border-radius: 5px;
}

.error {
	color: red;
	font-size: 12px;
}

.success {
	color: green;
	font-size: 12px;
}

.btn {
	display: block;
	margin: 20px auto;
	width: 50%;
	padding: 10px;
	background-color: blue;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.btn:hover {
	background-color: darkblue;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ccc;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}
</style>

</head>

<body>
	<div class="container">
		<h1>USER DETAIL</h1>
		<form action="<%=request.getContextPath()%>/register" method="post"
			onsubmit="return validateRegister()" name="frm-register">

			<label for="firstName">First Name *</label> <input type="text"
				id="firstName" name="firstName">
			<div id="firstNameError" class="error"></div>

			<label for="lastName">Last Name *</label> <input type="text"
				id="lastName" name="lastName">
			<div id="lastNameError" class="error"></div>

			<label for="email">Email *</label> <input type="email" id="email"
				name="email">
			<div id="emailError" class="error"></div>

			<label for="userName">User Name *</label> <input type="text"
				id="userName" name="userName">
			<div id="userNameError" class="error"></div>

			<label for="password">Password *</label> <input type="password"
				id="password" name="password">
			<div id="passwordError" class="error"></div>

			<label for="confirmPassword">Confirm Password *</label> <input
				type="password" id="confirmPassword" name="confirmPassword">
			<div id="confirmPasswordError" class="error"></div>

			<button type="submit" class="btn">Register</button>

		</form>
	</div>
</body>
</html>