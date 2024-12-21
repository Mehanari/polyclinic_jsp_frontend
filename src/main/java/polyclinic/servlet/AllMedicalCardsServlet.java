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
import java.util.List;

@WebServlet("/AllMedicalCardsServlet")
public class AllMedicalCardsServlet extends HttpServlet {
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
            List<MedicalCard> medicalCards = service.getMedicalCards();

            request.setAttribute("medicalCards", medicalCards);

            request.getRequestDispatcher("allMedicalCards.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().write("Error retrieving medical cards: " + e.getMessage());
        }
    }
}
