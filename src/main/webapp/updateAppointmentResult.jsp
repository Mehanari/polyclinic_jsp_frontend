<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="polyclinic.entity.AppointmentResult"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Appointment Result</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>Update Appointment Result</h1>
<form action="UpdateAppointmentResultServlet" method="post">
    <%
        AppointmentResult result = (AppointmentResult) request.getAttribute("appointmentResult");
    %>
    <input type="hidden" name="resultId" value="<%= result.getId() %>">
    <input type="hidden" name="cardNumber" value="<%= request.getAttribute("cardNumber") %>">
    <label>Reason:</label>
    <textarea name="reason" rows="4" cols="50" required><%= result.getReason() %></textarea><br>
    <label>Anamnesis:</label>
    <textarea name="anamnesis" rows="4" cols="50" required><%= result.getAnamnesis() %></textarea><br>
    <label>Objectively:</label>
    <textarea name="objectively" rows="4" cols="50" required><%= result.getObjectively() %></textarea><br>
    <label>Radiation Dose:</label>
    <input type="number" name="radiationDose" step="0.01" value="<%= result.getRadiationDose() %>" required><br>
    <label>Diagnosis:</label>
    <textarea name="diagnosis" rows="4" cols="50" required><%= result.getDiagnosis() %></textarea><br>
    <label>Prescription:</label>
    <textarea name="prescription" rows="4" cols="50" required><%= result.getPrescription() %></textarea><br>
    <label>Recommendations:</label>
    <textarea name="recommendations" rows="4" cols="50"><%= result.getRecommendations() %></textarea><br>
    <label>Actions:</label>
    <textarea name="actions" rows="4" cols="50" required><%= result.getActions() %></textarea><br>
    <label>Conclusion:</label>
    <textarea name="conclusion" rows="4" cols="50" required><%= result.getConclusion() %></textarea><br>
    <label>Date:</label>
    <input type="date" name="appointmentDate" value="<%= result.getAppointmentDate() %>" required><br>
    <label>Time:</label>
    <input type="time" name="appointmentTime" value="<%= result.getAppointmentTime() %>" required><br>
    <label>Doctor ID:</label>
    <input type="number" name="doctorID" value="<%= result.getDoctorID() %>" required><br>
    <br>
    <input type="submit" value="Update Appointment Result">
</form>
<div class="center">
    <button onclick="location.href='ViewMedicalCardServlet?cardNumber=<%= request.getAttribute("cardNumber") %>'">Cancel</button>
</div>
</body>
</html>
