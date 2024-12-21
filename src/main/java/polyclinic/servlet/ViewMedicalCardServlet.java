package polyclinic.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import polyclinic.entity.MedicalCard;
import polyclinic.service.PolyclinicService;

import java.io.IOException;

@WebServlet("/ViewMedicalCardServlet")
public class ViewMedicalCardServlet extends HttpServlet {
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
            // Get the card number from the request
            int cardNumber = Integer.parseInt(request.getParameter("cardNumber"));

            // Retrieve the medical card
            MedicalCard medicalCard = service.getMedicalCard(cardNumber);

            // Set the medical card as a request attribute
            request.setAttribute("medicalCard", medicalCard);

            // Forward to the JSP page
            request.getRequestDispatcher("viewMedicalCard.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().write("Error retrieving medical card: " + e.getMessage());
        }
    }
}
