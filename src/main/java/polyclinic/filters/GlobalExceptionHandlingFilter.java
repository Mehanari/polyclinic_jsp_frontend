package polyclinic.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter(urlPatterns = "/*") // Applies to all endpoints
public class GlobalExceptionHandlingFilter implements Filter {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandlingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            // Proceed with the request
            chain.doFilter(request, response);
        } catch (Exception e) {
            // Log the exception
            logger.log(Level.SEVERE, "Unhandled exception occurred", e);

            // Generate a user-friendly error response
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(createErrorResponse(e));
        }
    }

    private String createErrorResponse(Exception e) {
        // Generate a JSON error response (replace with your desired format)
        return String.format(
                "{\"error\": \"Internal Server Error\", \"message\": \"%s\"}",
                e.getMessage()
        );
    }
}
