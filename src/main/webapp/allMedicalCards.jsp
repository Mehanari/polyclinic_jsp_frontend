<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="polyclinic.entity.MedicalCard" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Medical Cards</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<h1>All Medical Cards</h1>
<div class="center">
    <button onclick="location.href='index.jsp'">Back to Main Menu</button>
</div>
<div class="card-list">
    <table>
        <thead>
        <tr>
            <th>Card Number</th>
            <th>Patient Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<MedicalCard> medicalCards = (List<MedicalCard>) request.getAttribute("medicalCards");
            if (medicalCards != null && !medicalCards.isEmpty()) {
                for (MedicalCard card : medicalCards) {
                    String fullName = card.getPersonalInfo().getLastName() + " " +
                            card.getPersonalInfo().getFirstName() + " " +
                            card.getPersonalInfo().getPatronymic();
        %>
        <tr>
            <td><%= card.getCardNumber() %></td>
            <td><%= fullName %></td>
            <td>
                <form action="ViewMedicalCardServlet" method="get">
                    <input type="hidden" name="cardNumber" value="<%= card.getCardNumber() %>">
                    <input type="submit" value="View">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3" style="text-align: center;">No medical cards found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
