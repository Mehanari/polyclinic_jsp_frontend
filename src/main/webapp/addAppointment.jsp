<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Add Appointment</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Add Appointment</h1>
<form action="AddAppointmentServlet" method="post">
  <input type="hidden" name="cardNumber" value="<%= request.getAttribute("cardNumber") %>">
  <label>Date:</label>
  <input type="date" name="date" required><br>
  <label>Start Time:</label>
  <input type="time" name="startTime" required><br>
  <label>End Time:</label>
  <input type="time" name="endTime" required><br>
  <label>Room Number:</label>
  <input type="number" name="roomNumber" required><br>
  <input type="submit" value="Add Appointment">
</form>
</body>
</html>
