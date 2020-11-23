package dental_clinic.core.requests;


public class CheckPatientByIdRequest {

    private long id;

    public CheckPatientByIdRequest(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

}
