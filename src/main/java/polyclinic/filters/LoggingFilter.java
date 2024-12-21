package polyclinic.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(urlPatterns = "/*")  // Apply this filter to all URLs
public class LoggingFilter implements Filter {

    private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing Logging Filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Pre-processing logic
        logger.info("Request received: " + request.getRemoteAddr());

        // Continue to the next filter or target resource
        chain.doFilter(request, response);

        // Post-processing logic
        logger.info("Response completed for: " + request.getRemoteAddr());
    }

    @Override
    public void destroy() {
        logger.info("Destroying Logging Filter");
    }
}
