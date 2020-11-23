package dental_clinic.core.responses;

import dental_clinic.core.domain.Patient;

import java.util.List;

public class CheckPatientByIdResponse extends CoreResponse {

    private long id;

    public CheckPatientByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public CheckPatientByIdResponse(long id){
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

}
