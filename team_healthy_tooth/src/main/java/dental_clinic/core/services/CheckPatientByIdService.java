package dental_clinic.core.services;


import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.CheckPatientByIdRequest;
import dental_clinic.core.responses.CheckPatientByIdResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.database.PatientDatabase;

import java.util.List;


public class CheckPatientByIdService {

    private final PatientDatabase patientDatabase;
    private CheckPatientByIdValidator checkPatientByIdValidator;

    public CheckPatientByIdService(PatientDatabase patientDatabase, CheckPatientByIdValidator checkPatientByIdValidator) {
        this.patientDatabase = patientDatabase;
        this.checkPatientByIdValidator = checkPatientByIdValidator;
    }

    public CheckPatientByIdResponse execute(CheckPatientByIdRequest checkPatientByIdRequest){

        List<CoreError> errors = checkPatientByIdValidator.validate(checkPatientByIdRequest);

        if (!errors.isEmpty()){
            return new CheckPatientByIdResponse(errors);
        }

        for (int i = 0; i < patientDatabase.getPatients().size(); i++){
            if (getCurrentPatientPersonalData(i).getId() == checkPatientByIdRequest.getId()){
                patientDatabase.checkPatientById(checkPatientByIdRequest.getId());
                return new CheckPatientByIdResponse(checkPatientByIdRequest.getId());
            }
        }

        errors.add(new CoreError("id = ", "Database doesn't contain patient with id"));
        return new CheckPatientByIdResponse(errors);
    }

    private PersonalData getCurrentPatientPersonalData(int index){
        return patientDatabase.getPatients().get(index).getPersonalData();
    }
}
