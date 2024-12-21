package polyclinic.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"medicalCard"})
@XmlRootElement(name = "medicalCards")
public class MedicalCards {
    protected List<MedicalCard> medicalCard;

    public List<MedicalCard> getMedicalCard() {
        if (medicalCard == null) {
            medicalCard = new ArrayList<MedicalCard>();
        }
        return this.medicalCard;
    }

    public void setMedicalCard(List<MedicalCard> medicalCard) {
        this.medicalCard = medicalCard;
    }

    @Override
    public String toString() {
        return "MedicalCards{" +
                "medicalCard=" + medicalCard +
                '}';
    }
}
