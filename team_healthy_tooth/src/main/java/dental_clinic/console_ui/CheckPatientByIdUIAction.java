package dental_clinic.console_ui;

import dental_clinic.core.requests.CheckPatientByIdRequest;
import dental_clinic.core.responses.CheckPatientByIdResponse;
import dental_clinic.core.services.CheckPatientByIdService;

import java.util.Scanner;

class CheckPatientByIdUIAction implements UIAction {

    private CheckPatientByIdService checkPatientByIdService;
    InputCheckUtility inputCheckUtility = new InputCheckUtility();

    public CheckPatientByIdUIAction(CheckPatientByIdService checkPatientById) {
        this.checkPatientByIdService = checkPatientById;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter patients id");
        long id = in.nextLong();

        CheckPatientByIdRequest checkPatientByIdRequest = new CheckPatientByIdRequest(id);
        CheckPatientByIdResponse checkPatientByIdResponse = checkPatientByIdService.execute(checkPatientByIdRequest);

        if (checkPatientByIdResponse.hasErrors()){
            checkPatientByIdResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Patient with id " + id + " is not found");
        }
    }

}

