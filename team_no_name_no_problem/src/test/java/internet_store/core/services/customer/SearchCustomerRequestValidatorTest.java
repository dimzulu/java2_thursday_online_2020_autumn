package internet_store.core.services.customer;

import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchCustomerRequestValidatorTest {

    SearchCustomerRequestValidator searchCustomerRequestValidator = new SearchCustomerRequestValidator();
    Ordering validOrdering = new Ordering("name", "ASC");
    Paging validPaging = new Paging(1, 1);

    @Test
    public void testEmptyFieldSearch(){
        CoreError expectedError = new CoreError("search", "Not valid input for search");

        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest
                (null, "",validOrdering, validPaging);
        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidFieldForName(){
        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest
                ("Jaroslav", "",validOrdering,validPaging);
        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        assertTrue(errors.size() == 0);

    }

    @Test
    public void testValidFieldForSurname(){
        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest
                (null, "Antonov",validOrdering,validPaging);
        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidNameAndSurnameFields(){
        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest
                ("Jaroslav", "Antonov",validOrdering,validPaging);
        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        assertTrue(errors.size() == 0);
    }

}