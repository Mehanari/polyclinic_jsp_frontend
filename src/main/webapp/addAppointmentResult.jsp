<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Add Appointment Result</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Add Appointment Result</h1>
<form action="AddAppointmentResultServlet" method="post">
  <input type="hidden" name="cardNumber" value="<%= request.getAttribute("cardNumber") %>">
  <label>Reason:</label>
  <textarea name="reason" rows="4" cols="50" required></textarea><br>
  <label>Anamnesis:</label>
  <textarea name="anamnesis" rows="4" cols="50" required></textarea><br>
  <label>Objectively:</label>
  <textarea name="objectively" rows="4" cols="50" required></textarea><br>
  <label>Radiation Dose:</label>
  <input type="number" name="radiationDose" step="0.01" required><br>
  <label>Diagnosis:</label>
  <textarea name="diagnosis" rows="4" cols="50" required></textarea><br>
  <label>Prescription:</label>
  <textarea name="prescription" rows="4" cols="50" required></textarea><br>
  <label>Recommendations:</label>
  <textarea name="recommendations" rows="4" cols="50"></textarea><br>
  <label>Actions:</label>
  <textarea name="actions" rows="4" cols="50" required></textarea><br>
  <label>Conclusion:</label>
  <textarea name="conclusion" rows="4" cols="50" required></textarea><br>
  <label>Date:</label>
  <input type="date" name="appointmentDate" required><br>
  <label>Time:</label>
  <input type="time" name="appointmentTime" required><br>
  <label>Doctor ID:</label>
  <input type="number" name="doctorID" required><br>
  <input type="submit" value="Add Appointment Result">
</form>
<div class="center">
  <button onclick="location.href='ViewMedicalCardServlet?cardNumber=<%= request.getAttribute("cardNumber") %>'">Cancel</button>
</div>
</body>
</html>
