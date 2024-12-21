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
import javax.xml.datatype.XMLGregorianCalendar;

@WebServlet("/AddAppointmentServlet")
public class AddAppointmentServlet extends HttpServlet {
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
        request.getRequestDispatcher("addAppointment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
            String date = request.getParameter("date");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));

            Appointment appointment = new Appointment();
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            appointment.setPatientCardNumber(cardNumber);
            appointment.setDate(datatypeFactory.newXMLGregorianCalendar(date));
            int hours = Integer.parseInt(startTime.substring(0, 2));
            int minutes = Integer.parseInt(startTime.substring(3, 5));
            appointment.setStartTime(datatypeFactory.newXMLGregorianCalendarTime(hours, minutes, 0, 0));
            hours = Integer.parseInt(endTime.substring(0, 2));
            minutes = Integer.parseInt(endTime.substring(3, 5));
            appointment.setEndTime(datatypeFactory.newXMLGregorianCalendarTime(hours, minutes, 0, 0));
            appointment.setRoomNumber(roomNumber);

            service.addAppointment(appointment);
            response.sendRedirect("ViewMedicalCardServlet?cardNumber=" + cardNumber);
        } catch (Exception e) {
            response.getWriter().write("Error adding appointment: ");
            e.printStackTrace();
        }
    }
}
