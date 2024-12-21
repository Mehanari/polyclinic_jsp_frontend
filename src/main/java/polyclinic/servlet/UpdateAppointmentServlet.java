package polyclinic.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import polyclinic.entity.Appointment;
import polyclinic.service.PolyclinicService;

import java.io.IOException;
import javax.xml.datatype.DatatypeFactory;

@WebServlet("/UpdateAppointmentServlet")
public class UpdateAppointmentServlet extends HttpServlet {
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
            int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
            int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));

            Appointment appointment = service.getAppointments(cardNumber, 0).stream()
                    .filter(a -> a.getId() == appointmentId)
                    .findFirst()
                    .orElse(null);

            if (appointment == null) {
                response.getWriter().write("Appointment not found.");
                return;
            }

            request.setAttribute("appointment", appointment);
            request.setAttribute("cardNumber", cardNumber);
            request.getRequestDispatcher("updateAppointment.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().write("Error retrieving appointment: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
            int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
            String newDate = request.getParameter("date");
            String newStartTime = request.getParameter("startTime");
            String newEndTime = request.getParameter("endTime");

            Appointment updatedAppointment = service.getAppointments(cardNumber, 0).stream()
                    .filter(a -> a.getId() == appointmentId)
                    .findFirst()
                    .orElse(null);

            if (updatedAppointment == null) {
                response.getWriter().write("Appointment not found.");
                return;
            }
            updatedAppointment.setId(appointmentId);
            updatedAppointment.setPatientCardNumber(cardNumber);
            updatedAppointment.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(newDate));
            int hours = Integer.parseInt(newStartTime.split(":")[0]);
            int minutes = Integer.parseInt(newStartTime.split(":")[1]);
            updatedAppointment.setStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendarTime(hours, minutes, 0, 0));
            hours = Integer.parseInt(newEndTime.split(":")[0]);
            minutes = Integer.parseInt(newEndTime.split(":")[1]);
            updatedAppointment.setEndTime(DatatypeFactory.newInstance().newXMLGregorianCalendarTime(hours, minutes, 0, 0));

            service.updateAppointment(updatedAppointment);
            response.sendRedirect("ViewMedicalCardServlet?cardNumber=" + cardNumber);
        } catch (Exception e) {
            response.getWriter().write("Error updating appointment: " + e.getMessage());
        }
    }
}
