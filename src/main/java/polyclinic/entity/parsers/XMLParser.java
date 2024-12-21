package polyclinic.entity.parsers;

import polyclinic.entity.Appointment;
import polyclinic.entity.AppointmentResult;
import polyclinic.entity.MedicalCard;

public interface XMLParser extends XMLLoader {
    public void saveAppointment(Appointment appointment, final String xmlFileName) throws Exception;

    public void saveAppointmentResult(AppointmentResult appointmentResult, final String xmlFileName) throws Exception;

    public void saveMedicalCard(MedicalCard medicalCard, final String xmlFileName) throws Exception;
}
