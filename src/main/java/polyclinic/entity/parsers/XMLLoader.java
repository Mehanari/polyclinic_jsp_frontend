package polyclinic.entity.parsers;


import polyclinic.entity.Appointment;
import polyclinic.entity.AppointmentResult;
import polyclinic.entity.MedicalCard;

public interface XMLLoader {
    public Appointment loadAppointment(final String xmlFileName) throws Exception;

    public AppointmentResult loadAppointmentResult(final String xmlFileName) throws Exception;

    public MedicalCard loadMedicalCard(final String xmlFileName) throws Exception;
}
