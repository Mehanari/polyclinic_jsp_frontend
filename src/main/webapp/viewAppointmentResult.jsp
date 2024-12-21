<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="polyclinic.entity.AppointmentResult"%>
<!DOCTYPE html>
<html>
<head>
  <title>View Appointment Result</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="container">
  <h1 style="text-align: center;">Appointment Result Details</h1>
  <div class="appointment-result-details">
    <%
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
      AppointmentResult result = (AppointmentResult) request.getAttribute("appointmentResult");
      int cardNumber = (Integer) request.getAttribute("cardNumber");
    %>
    <p><strong>Reason:</strong> <%= result.getReason() %></p>
    <p><strong>Anamnesis:</strong> <%= result.getAnamnesis() %></p>
    <p><strong>Objectively:</strong> <%= result.getObjectively() %></p>
    <p><strong>Radiation Dose:</strong> <%= result.getRadiationDose() %></p>
    <p><strong>Diagnosis:</strong> <%= result.getDiagnosis() %></p>
    <p><strong>Prescription:</strong> <%= result.getPrescription() %></p>
    <p><strong>Recommendations:</strong> <%= result.getRecommendations() != null ? result.getRecommendations() : "None" %></p>
    <p><strong>Actions:</strong> <%= result.getActions() %></p>
    <p><strong>Conclusion:</strong> <%= result.getConclusion() %></p>
    <p><strong>Appointment Date:</strong> <%= dateFormat.format(result.getAppointmentDate().toGregorianCalendar().getTime()) %></p>
    <p><strong>Appointment Time:</strong> <%= timeFormat.format(result.getAppointmentTime().toGregorianCalendar().getTime()) %></p>
    <p><strong>Doctor ID:</strong> <%= result.getDoctorID() %></p>
    <p><strong>Patient Card Number:</strong> <%= result.getPatientCardNumber() %></p>
  </div>
  <div class="center" style="margin-top: 20px;">
    <button onclick="location.href='ViewMedicalCardServlet?cardNumber=<%= cardNumber %>'">Back to Medical Card</button>
  </div>
</div>
</body>
</html>