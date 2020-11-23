package dental_clinic.core.services;

import dental_clinic.core.requests.CheckPatientByIdRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class CheckPatientByIdValidator {

    public List<CoreError> validate (CheckPatientByIdRequest request) {
        List<CoreError> errorsResult = new ArrayList<>();

        Long id = request.getId();

        if ( id<0 ) {
            errorsResult.add(new CoreError("id = ",
                    "Invalid input patient ID!"));
        }
        return errorsResult;
    }


}
