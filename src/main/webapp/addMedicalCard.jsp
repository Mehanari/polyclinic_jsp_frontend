<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Medical Card</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Add Medical Card</h1>
<form action="AddMedicalCardServlet" method="post">
    <label>First Name:</label>
    <input type="text" name="firstName" required><br>
    <label>Last Name:</label>
    <input type="text" name="lastName" required><br>
    <label>Patronymic:</label>
    <input type="text" name="patronymic" required><br>
    <label>Birth Date:</label>
    <input type="date" name="birthDate" required><br>
    <label>Gender:</label>
    <select name="gender" required>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
        <option value="Other">Other</option>
    </select><br>
    <label>Workplace:</label>
    <input type="text" name="workplace" required><br>
    <label>Address:</label>
    <input type="text" name="address" required><br>
    <label>Email:</label>
    <input type="email" name="email" required><br>
    <label>Phone:</label>
    <input type="tel" name="phone" required><br>
    <label>Identification (Passport/Driver License/ID Card):</label>
    <input type="text" name="identification" required><br>
    <br>
    <input type="submit" value="Add Medical Card">
</form>
</body>
</html>
