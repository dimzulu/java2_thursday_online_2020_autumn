package dental_clinic.domain;

import java.util.*;

public class Patient {

    private PersonalData personalData;
    private Jowl jowl = new Jowl();
    private List<String> attendingDoctors;

    public Patient(PersonalData personalData, String...attendingDoctors){
        this.personalData = personalData;
        this.attendingDoctors = new ArrayList<>(Arrays.asList(attendingDoctors));
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public Jowl getJowl (){
        return jowl;
    }

    public List<String> getAttendingDoctors() {
        return attendingDoctors;
    }

    public void addAttendingDoctor(String attendingDoctor) {
        this.attendingDoctors.add(attendingDoctor);
    }


    @Override
    public String toString() {
        return "Patient{" +
                "personalData=" + personalData +
                ", jowl=" + jowl +
                ", attendingDoctors=" + attendingDoctors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(personalData, patient.personalData) &&
                Objects.equals(jowl, patient.jowl) &&
                Objects.equals(attendingDoctors, patient.attendingDoctors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalData, jowl, attendingDoctors);
    }
}
