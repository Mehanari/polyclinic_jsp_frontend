package polyclinic.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import polyclinic.service.PolyclinicService;

import java.net.URI;
import java.net.URISyntaxException;

@WebListener
public class ServiceInitializationListener implements ServletContextListener {
    public static final Logger log = LoggerFactory.getLogger(ServiceInitializationListener.class);
    private final String SCHEME = "http";
    private final String HOST = "localhost";
    private final int PORT = 8080;
    private final String PATH = "polyclinic";
    private final String REST_PATH = "rest";
    private final String RESOURCE_PATH = "medicalCards";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        URI uri = getBaseURI(SCHEME, HOST, PORT, PATH, REST_PATH, RESOURCE_PATH);
        ServletContext context = sce.getServletContext();
        PolyclinicService polyclinicService = new PolyclinicService(uri);
        context.setAttribute("PolyclinicService", polyclinicService);
    }

    public URI getBaseURI(String scheme, String host, int port, String... pathSegments) {
        try {
            StringBuilder path = new StringBuilder();
            for (String segment : pathSegments) {
                if (!segment.startsWith("/")) {
                    path.append("/");
                }
                path.append(segment);
            }

            URI uri = new URI(scheme, null, host, port, path.toString(), null, null);
            log.info("Base URI: {}", uri);
            return uri;
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error creating URI", e);
        }
    }
}
