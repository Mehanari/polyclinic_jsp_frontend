package polyclinic.service;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import polyclinic.entity.Appointment;
import polyclinic.entity.AppointmentResult;
import polyclinic.entity.MedicalCard;

import java.net.URI;
import java.util.List;
import java.util.function.Supplier;

public class PolyclinicService {
    public static final Logger log = LoggerFactory.getLogger(PolyclinicService.class);
    private final WebTarget target;

    public PolyclinicService(URI uri){
        Client client = ClientBuilder.newClient().register(JAXBContextProvider.class);
        WebTarget target = client.target(uri);
        this.target = target;
    }

    public List<MedicalCard> getMedicalCards(){
        log.info("Getting medical cards...");
        return handleConnection(() -> {
            Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
            Response response = request.get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(new GenericType<List<MedicalCard>>() {});
            }
            log.error("Failed to get medical cards. Status code: {}", response.getStatus());
            log.error("Response: {}", response.readEntity(String.class));
            return null;
        });
    }

    public MedicalCard getMedicalCard(int id){
        log.info("Getting medical card...");
        return handleConnection(() -> {
            Invocation.Builder request = target.path(String.valueOf(id)).request(MediaType.APPLICATION_JSON);
            Response response = request.get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(MedicalCard.class);
            }
            log.error("Failed to get medical card. Status code: {}", response.getStatus());
            log.error("Response: {}", response.readEntity(String.class));
            return null;
        });
    }

    public MedicalCard addMedicalCard(MedicalCard medicalCard){
        log.info("Adding medical card...");
        return handleConnection(() -> {
            Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
            Response response = request.post(Entity.entity(medicalCard, MediaType.APPLICATION_JSON));
            if (response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(MedicalCard.class);
            }
            log.error("Failed to add medical card. Status code: {}", response.getStatus());
            log.error("Response: {}", response.readEntity(String.class));
            return null;
        });
    }

    //Card number must be specified in the appointment object.
    public int addAppointment(Appointment appointment){
        log.info("Adding appointment...");
        return handleConnection(()-> {
            int cardNumber = appointment.getPatientCardNumber();
            Invocation.Builder request = target.path(String.valueOf(cardNumber)).path("appointment").request(MediaType.APPLICATION_JSON);
            Response response = request.post(Entity.entity(appointment, MediaType.APPLICATION_JSON));
            if (response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(Integer.class);
            }
            log.error("Failed to add appointment. Status code: {}", response.getStatus());
            return null;
        });
    }

    //Card number must be specified in the appointment object.
    public Appointment updateAppointment(Appointment appointment){
        log.info("Updating appointment...");
        return handleConnection(()-> {
            int cardNumber = appointment.getPatientCardNumber();
            Invocation.Builder request = target.path(String.valueOf(cardNumber)).path("appointment").request(MediaType.APPLICATION_JSON);
            Response response = request.put(Entity.entity(appointment, MediaType.APPLICATION_JSON));
            if (response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(Appointment.class);
            }
            log.error("Failed to update appointment. Status code: {}", response.getStatus());
            return null;
        });
    }

    public Appointment deleteAppointment(int appointmentId, int cardNumber){
        log.info("Deleting appointment...");
        return handleConnection(()-> {
            Invocation.Builder request = target.path(String.valueOf(cardNumber)).path("appointment").path(String.valueOf(appointmentId)).request(MediaType.APPLICATION_JSON);
            Response response = request.delete();
            if (response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(Appointment.class);
            }
            log.error("Failed to delete appointment. Status code: {}", response.getStatus());
            return null;
        });
    }

    //Card number must be specified in the appointment result object.
    public int addAppointmentResult(AppointmentResult appointmentResult){
        log.info("Adding appointment result...");
        return handleConnection(()-> {
            int cardNumber = appointmentResult.getPatientCardNumber();
            Invocation.Builder request = target.path(String.valueOf(cardNumber)).path("appointmentResult").request(MediaType.APPLICATION_JSON);
            Response response = request.post(Entity.entity(appointmentResult, MediaType.APPLICATION_JSON));
            if (response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(Integer.class);
            }
            log.error("Failed to add appointment result. Status code: {}", response.getStatus());
            return null;
        });
    }

    //Card number must be specified in the appointment result object.
    public AppointmentResult updateAppointmentResult(AppointmentResult appointmentResult){
        log.info("Updating appointment result...");
        return handleConnection(()-> {
            int cardNumber = appointmentResult.getPatientCardNumber();
            Invocation.Builder request = target.path(String.valueOf(cardNumber)).path("appointmentResult").request(MediaType.APPLICATION_JSON);
            Response response = request.put(Entity.entity(appointmentResult, MediaType.APPLICATION_JSON));
            if (response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(AppointmentResult.class);
            }
            log.error("Failed to update appointment result. Status code: {}", response.getStatus());
            return null;
        });
    }

    public List<Appointment> getAppointments(int cardNumber, int roomNumber){
        log.info("Getting appointments...");
        return handleConnection(() -> {
            Invocation.Builder request = target.path(String.valueOf(cardNumber)).path("appointments").queryParam("roomNumber", roomNumber).request(MediaType.APPLICATION_JSON);
            Response response = request.get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(new GenericType<List<Appointment>>() {});
            }
            log.error("Failed to get appointments. Status code: {}", response.getStatus());
            log.error("Response: {}", response.readEntity(String.class));
            return null;
        });
    }

    public static <T> T handleConnection(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (ProcessingException processingException) {
            log.error("Processing error was occurred.", processingException);
            throw processingException;
        }
    }
}
