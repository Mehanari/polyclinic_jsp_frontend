package polyclinic.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import polyclinic.entity.AppointmentResult;
import polyclinic.service.PolyclinicService;

import java.io.IOException;
import java.math.BigDecimal;
import javax.xml.datatype.DatatypeFactory;

@WebServlet("/AddAppointmentResultServlet")
public class AddAppointmentResultServlet extends HttpServlet {
    private PolyclinicService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        service = (PolyclinicService) ctx.getAttribute("PolyclinicService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
        request.setAttribute("cardNumber", cardNumber);
        request.getRequestDispatcher("addAppointmentResult.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
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

            AppointmentResult appointmentResult = new AppointmentResult();
            appointmentResult.setPatientCardNumber(cardNumber);
            appointmentResult.setReason(reason);
            appointmentResult.setAnamnesis(anamnesis);
            appointmentResult.setObjectively(objectively);
            appointmentResult.setRadiationDose(radiationDose);
            appointmentResult.setDiagnosis(diagnosis);
            appointmentResult.setPrescription(prescription);
            appointmentResult.setRecommendations(recommendations);
            appointmentResult.setActions(actions);
            appointmentResult.setConclusion(conclusion);
            int hours = Integer.parseInt(resultTime.substring(0, 2));
            int minutes = Integer.parseInt(resultTime.substring(3, 5));
            appointmentResult.setAppointmentDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(resultDate));
            appointmentResult.setAppointmentTime(DatatypeFactory.newInstance().newXMLGregorianCalendarTime(hours, minutes, 0, 0));
            appointmentResult.setDoctorID(doctorID);

            service.addAppointmentResult(appointmentResult);
            response.sendRedirect("ViewMedicalCardServlet?cardNumber=" + cardNumber);
        } catch (Exception e) {
            response.getWriter().write("Error adding appointment result: " + e.getMessage());
        }
    }
}
