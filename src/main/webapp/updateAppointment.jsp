<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="polyclinic.entity.Appointment"%>
<!DOCTYPE html>
<html>
<head>
  <title>Update Appointment</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Update Appointment</h1>
<form action="UpdateAppointmentServlet" method="post">
  <%
    Appointment appointment = (Appointment) request.getAttribute("appointment");
  %>
  <input type="hidden" name="appointmentId" value="<%= appointment.getId() %>">
  <input type="hidden" name="cardNumber" value="<%= request.getAttribute("cardNumber") %>">
  <label>Date:</label>
  <input type="date" name="date" value="<%= appointment.getDate() %>" required><br>
  <label>Start Time:</label>
  <input type="time" name="startTime" value="<%= appointment.getStartTime() %>" required><br>
  <label>End Time:</label>
  <input type="time" name="endTime" value="<%= appointment.getEndTime() %>" required><br>
  <br>
  <input type="submit" value="Update Appointment">
</form>
<div class="center">
  <button onclick="location.href='ViewMedicalCardServlet?cardNumber=<%= request.getAttribute("cardNumber") %>'">Cancel</button>
</div>
</body>
</html>
