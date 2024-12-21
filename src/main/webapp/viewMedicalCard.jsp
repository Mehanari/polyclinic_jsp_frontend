<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="polyclinic.entity.Appointment"%>
<%@ page import="polyclinic.entity.AppointmentResult"%>
<%@ page import="polyclinic.entity.MedicalCard" %>
<!DOCTYPE html>
<html>
<head>
  <title>Medical Card Details</title>
  <link rel="stylesheet" type="text/css" href="css/styles.css">
  <style>
    table td form input[type="submit"] {
      padding: 5px 10px;
      border: none;
      border-radius: 4px;
      font-weight: bold;
      cursor: pointer;
      text-align: center;
      width: 80px;
    }

    table td form .update-btn {
      background-color: #007BFF;
      color: white;
    }

    table td form .delete-btn {
      background-color: #DC3545;
      color: white;
    }

    table td form .update-btn:hover {
      background-color: #0056b3;
    }

    table td form .delete-btn:hover {
      background-color: #a71d2a;
    }
  </style>
</head>
<body>
<h1>Medical Card Details</h1>
<div class="center">
  <button onclick="location.href='AllMedicalCardsServlet'">Back to All Cards</button>
</div>

<% SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); %>
<div class="card-details">
  <h2>Card Information</h2>
  <p><strong>Card Number:</strong> <%= ((MedicalCard) request.getAttribute("medicalCard")).getCardNumber() %></p>
  <p><strong>Name:</strong>
    <%= ((MedicalCard) request.getAttribute("medicalCard")).getPersonalInfo().getLastName() %>
    <%= ((MedicalCard) request.getAttribute("medicalCard")).getPersonalInfo().getFirstName() %>
    <%= ((MedicalCard) request.getAttribute("medicalCard")).getPersonalInfo().getPatronymic() %>
  </p>
  <p><strong>Birth Date:</strong> <%=dateFormat.format(((MedicalCard) request.getAttribute("medicalCard")).getPersonalInfo().getBirthDate().toGregorianCalendar().getTime()) %></p>
  <p><strong>Gender:</strong> <%= ((MedicalCard) request.getAttribute("medicalCard")).getPersonalInfo().getGender() %></p>
  <p><strong>Workplace:</strong> <%= ((MedicalCard) request.getAttribute("medicalCard")).getWorkplace() %></p>
  <p><strong>Address:</strong> <%= ((MedicalCard) request.getAttribute("medicalCard")).getAddress() %></p>
  <p><strong>Email:</strong> <%= ((MedicalCard) request.getAttribute("medicalCard")).getEmail() %></p>
  <p><strong>Phone:</strong> <%= ((MedicalCard) request.getAttribute("medicalCard")).getPhone() %></p>
</div>

<div class="appointments">
  <h2>Appointments</h2>
  <%
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    List<Appointment> appointments = ((MedicalCard) request.getAttribute("medicalCard")).getAppointments().getAppointment();
    if (appointments.isEmpty()) {
  %>
  <p style="text-align: center;">No appointments available.</p>
  <% } else { %>
  <table>
    <thead>
    <tr>
      <th>Date</th>
      <th>Start Time</th>
      <th>End Time</th>
      <th>Room Number</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% for (Appointment appointment : appointments) { %>
    <tr>
      <td><%= dateFormat.format(appointment.getDate().toGregorianCalendar().getTime()) %></td>
      <td><%= timeFormat.format(appointment.getStartTime().toGregorianCalendar().getTime()) %></td>
      <td><%= timeFormat.format(appointment.getEndTime().toGregorianCalendar().getTime()) %></td>
      <td><%= appointment.getRoomNumber() %></td>
      <td style="text-align: center;">
        <form action="UpdateAppointmentServlet" method="get">
          <input type="hidden" name="appointmentId" value="<%= appointment.getId() %>">
            <input type="hidden" name="cardNumber" value="<%= ((MedicalCard) request.getAttribute("medicalCard")).getCardNumber() %>">
          <input type="submit" class="update-btn" value="Update">
        </form>
        <form action="DeleteAppointmentServlet" method="post">
          <input type="hidden" name="appointmentId" value="<%= appointment.getId() %>">
          <input type="hidden" name="cardNumber" value="<%= ((MedicalCard) request.getAttribute("medicalCard")).getCardNumber() %>">
          <input type="submit" class="delete-btn" value="Delete">
        </form>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <% } %>
  <div class="center">
    <button onclick="location.href='AddAppointmentServlet?cardNumber=<%= ((MedicalCard) request.getAttribute("medicalCard")).getCardNumber() %>'">Add Appointment</button>
  </div>
</div>

<div class="results">
  <h2>Results</h2>
  <%
    List<AppointmentResult> results = ((MedicalCard) request.getAttribute("medicalCard")).getResults().getAppointmentResult();
    if (results.isEmpty()) {
  %>
  <p style="text-align: center;">No results available.</p>
  <% } else { %>
  <table>
    <thead>
    <tr>
      <th>Room Number</th>
      <th>Date</th>
      <th>Time</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% for (AppointmentResult result : results) { %>
    <tr>
      <td><%= result.getDoctorID() %></td>
      <td><%= dateFormat.format(result.getAppointmentDate().toGregorianCalendar().getTime()) %></td>
      <td><%= timeFormat.format(result.getAppointmentTime().toGregorianCalendar().getTime()) %></td>
      <td style="text-align: center;">
        <form action="UpdateAppointmentResultServlet" method="get">
          <input type="hidden" name="resultId" value="<%= result.getId() %>">
            <input type="hidden" name="cardNumber" value="<%= ((MedicalCard) request.getAttribute("medicalCard")).getCardNumber() %>">
          <input type="submit" class="update-btn" value="Update">
        </form>
        <form action="ViewAppointmentResultServlet" method="get">
          <input type="hidden" name="resultId" value="<%= result.getId() %>">
            <input type="hidden" name="cardNumber" value="<%= ((MedicalCard) request.getAttribute("medicalCard")).getCardNumber() %>">
          <input type="submit" value="View">
        </form>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <% } %>
  <div class="center">
    <button onclick="location.href='AddAppointmentResultServlet?cardNumber=<%= ((MedicalCard) request.getAttribute("medicalCard")).getCardNumber() %>'">Add Result</button>
  </div>
</div>
</body>
</html>
