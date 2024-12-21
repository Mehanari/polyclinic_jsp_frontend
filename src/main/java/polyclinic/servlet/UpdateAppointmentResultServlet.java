package polyclinic.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import polyclinic.entity.AppointmentResult;
import polyclinic.entity.MedicalCard;
import polyclinic.service.PolyclinicService;

import java.io.IOException;
import java.math.BigDecimal;
import javax.xml.datatype.DatatypeFactory;

@WebServlet("/UpdateAppointmentResultServlet")
public class UpdateAppointmentResultServlet extends HttpServlet {
    private PolyclinicService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        service = (PolyclinicService) ctx.getAttribute("PolyclinicService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int resultId = Integer.parseInt(request.getParameter("resultId"));
            int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));

            // Fetch the appointment result details
            MedicalCard card = service.getMedicalCard(cardNumber);
            AppointmentResult result = card.getResults().getAppointmentResult().stream()
                    .filter(r -> r.getId() == resultId)
                    .findFirst()
                    .orElse(null);

            if (result == null) {
                response.getWriter().write("Appointment result not found.");
                return;
            }

            request.setAttribute("appointmentResult", result);
            request.setAttribute("cardNumber", cardNumber);
            request.getRequestDispatcher("updateAppointmentResult.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().write("Error retrieving appointment result: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int resultId = Integer.parseInt(request.getParameter("resultId"));
            int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
            String reason = request.getParameter("reason");
            String anamnesis = request.getParameter("anamnesis");
            String objectively = request.getParameter("objectively");
            BigDecimal radiationDose = new BigDecimal(request.getParameter("radiationDose"));
            String diagnosis = request.getParameter("diagnosis");
            String prescription = request.getParameter("prescription");
            String recommendations = request.getParameter("recommendations");
            String actions = request.getParameter("actions");
            String conclusion = request.getParameter("conclusion");
            String resultDate = request.getParameter("appointmentDate");
            String resultTime = request.getParameter("appointmentTime");
            int doctorID = Integer.parseInt(request.getParameter("doctorID"));

            // Create the updated appointment result object
            AppointmentResult updatedResult = new AppointmentResult();
            updatedResult.setId(resultId);
            updatedResult.setPatientCardNumber(cardNumber);
            updatedResult.setReason(reason);
            updatedResult.setAnamnesis(anamnesis);
            updatedResult.setObjectively(objectively);
            updatedResult.setRadiationDose(radiationDose);
            updatedResult.setDiagnosis(diagnosis);
            updatedResult.setPrescription(prescription);
            updatedResult.setRecommendations(recommendations);
            updatedResult.setActions(actions);
            updatedResult.setConclusion(conclusion);
            int hours = Integer.parseInt(resultTime.substring(0, 2));
            int minutes = Integer.parseInt(resultTime.substring(3, 5));
            updatedResult.setAppointmentDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(resultDate));
            updatedResult.setAppointmentTime(DatatypeFactory.newInstance().newXMLGregorianCalendarTime(hours, minutes, 0, 0));
            updatedResult.setDoctorID(doctorID);

            service.updateAppointmentResult(updatedResult);
            response.sendRedirect("ViewMedicalCardServlet?cardNumber=" + cardNumber);
        } catch (Exception e) {
            response.getWriter().write("Error updating appointment result: " + e.getMessage());
        }
    }
}
