package polyclinic.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import polyclinic.entity.Identification;
import polyclinic.entity.MedicalCard;
import polyclinic.entity.PersonalInfo;
import polyclinic.listener.ServiceInitializationListener;
import polyclinic.service.PolyclinicService;


import java.io.IOException;

@WebServlet("/AddMedicalCardServlet")
public class AddMedicalCardServlet extends HttpServlet {
    public static final Logger log = LoggerFactory.getLogger(AddMedicalCardServlet.class);
    private PolyclinicService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        service = (PolyclinicService) ctx.getAttribute("PolyclinicService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            log.info("AddMedicalCardServlet#doPost");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String patronymic = request.getParameter("patronymic");
            String birthDate = request.getParameter("birthDate");
            String gender = request.getParameter("gender");
            String workplace = request.getParameter("workplace");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String identification = request.getParameter("identification");

            if (firstName.isEmpty() || lastName.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || workplace.isEmpty() ||
                    address.isEmpty() || email.isEmpty() || phone.isEmpty() || identification.isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            PersonalInfo personalInfo = new PersonalInfo();
            personalInfo.setFirstName(firstName);
            personalInfo.setLastName(lastName);
            personalInfo.setPatronymic(patronymic);
            personalInfo.setBirthDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(birthDate));
            personalInfo.setGender(gender);

            Identification id = new Identification();
            id.setPassportNumber(identification);

            MedicalCard card = new MedicalCard();
            card.setPersonalInfo(personalInfo);
            card.setWorkplace(workplace);
            card.setAddress(address);
            card.setEmail(email);
            card.setPhone(phone);
            card.setIdentification(id);

            MedicalCard responseCard = service.addMedicalCard(card);
            log.info("Medical card added: " + responseCard);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
