package dental_clinic.core.services;

import dental_clinic.core.domain.ToothStatus;
import dental_clinic.core.domain.Visit;
import dental_clinic.core.requests.AddVisitRequest;
import dental_clinic.core.responses.CoreError;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AddVisitValidatorTest {

    AddVisitValidator addVisitValidator = new AddVisitValidator();
    Optional<String> comment = Optional.empty();

    @Test
    public void testNotValidInputId(){
        List<CoreError> expectedErrors = new ArrayList<>();
        expectedErrors.add(new CoreError("id", "Not valid input of id"));

        Visit newVisit = new Visit( 11, comment, ToothStatus.FASETE, "Zlo");
        AddVisitRequest addVisitRequest = new AddVisitRequest(-1, newVisit);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        assertTrue(expectedErrors.equals(coreErrors));
    }

    @Test
    public void testNotValidInputToothNumber(){
        CoreError expectedError = new CoreError("tooth number", "Not valid input for tooth number");

        Visit newVisit = new Visit(2, comment, ToothStatus.FASETE, "Zlo");
        AddVisitRequest addVisitRequest = new AddVisitRequest(1, newVisit);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testNotValidInputDoctor(){
        CoreError expectedError = new CoreError("doctor", "Not valid input for doctor");

        Visit visit = new Visit(11, comment, ToothStatus.FASETE, "");
        AddVisitRequest addVisitRequest = new AddVisitRequest(1, visit);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        assertTrue(coreErrors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        Visit visit = new Visit(11, comment, ToothStatus.FASETE, "Doctor");
        AddVisitRequest addVisitRequest = new AddVisitRequest(1, visit);

        List <CoreError> coreErrors = addVisitValidator.validate(addVisitRequest);

        coreErrors.forEach(System.out::println);
        assertTrue(coreErrors.isEmpty());
    }

}