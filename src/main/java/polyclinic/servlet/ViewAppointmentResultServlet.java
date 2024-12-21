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

@WebServlet("/ViewAppointmentResultServlet")
public class ViewAppointmentResultServlet extends HttpServlet {
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

            MedicalCard medicalCard = service.getMedicalCard(cardNumber);
            AppointmentResult result = medicalCard.getResults().getAppointmentResult().stream().filter(r -> r.getId() == resultId).findFirst().orElse(null);

            if (result == null) {
                response.getWriter().write("Appointment result not found.");
                return;
            }

            request.setAttribute("appointmentResult", result);
            request.setAttribute("cardNumber", cardNumber);
            request.getRequestDispatcher("viewAppointmentResult.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().write("Error retrieving appointment: " + e.getMessage());
        }
    }
}
