package polyclinic.service;

import jakarta.ws.rs.ext.ContextResolver;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import polyclinic.entity.ObjectFactory;

public class JAXBContextProvider implements ContextResolver<JAXBContext> {

    private JAXBContext context = null;

    public JAXBContext getContext(Class<?> type) {
        if (context == null) {
            try {
                context = JAXBContext.newInstance (ObjectFactory.class);
            } catch (JAXBException exception) {
                System.err.println(getClass().getName() + ": " + exception.getMessage());
            }
        }
        return context;
    }
}
