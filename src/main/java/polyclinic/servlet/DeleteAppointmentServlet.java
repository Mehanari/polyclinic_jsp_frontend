package polyclinic.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import polyclinic.service.PolyclinicService;

import java.io.IOException;

@WebServlet("/DeleteAppointmentServlet")
public class DeleteAppointmentServlet extends HttpServlet {
    private PolyclinicService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        service = (PolyclinicService) ctx.getAttribute("PolyclinicService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
            int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
            service.deleteAppointment(appointmentId, cardNumber);
            response.sendRedirect("ViewMedicalCardServlet?cardNumber=" + cardNumber);
        } catch (Exception e) {
            response.getWriter().write("Error deleting appointment: " + e.getMessage());
        }
    }
}
