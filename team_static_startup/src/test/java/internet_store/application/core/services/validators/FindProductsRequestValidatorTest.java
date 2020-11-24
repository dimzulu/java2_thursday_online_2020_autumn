package internet_store.application.core.services.validators;

import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.requests.Ordering;
import internet_store.application.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindProductsRequestValidatorTest {

    private FindProductsRequestValidator validator;
    private Ordering ordering;

    @Before
    public void setUp() {
        validator = new FindProductsRequestValidator();
    }

    @Test
    public void shouldNotReturnErrorsWhenNameIsProvided() {
        FindProductsRequest request = new FindProductsRequest("Name", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenDescriptionIsProvided() {
        FindProductsRequest request = new FindProductsRequest(null, "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenTitleAndAuthorIsProvided() {
        FindProductsRequest request = new FindProductsRequest("Name", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenSearchFieldsAreEmpty() {
        FindProductsRequest request = new FindProductsRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "Description");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldNotReturnErrorsOrderingFieldIsFilled() {
        ordering = new Ordering("Name", "Descending");
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfOnlyOneOrderingFieldIsFilled() {
        ordering = new Ordering("Name", "");
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Ordering Fields", errors.get(0).getField());
        assertEquals("Both must be empty or filled!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfOrderingNameIsWrong() {
        ordering = new Ordering("price", "alphabet");
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Ordering by", errors.get(0).getField());
        assertEquals("Must be Name or Description.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfOrderingDirectionIsWrong() {
        ordering = new Ordering("Name", "alphabet");
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Direction", errors.get(0).getField());
        assertEquals("Must be Ascending or Descending.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnNoErrorsPagingFieldsFilled() {
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", 10, 10);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorIfOneFieldInPagingIsNull() {
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", 10, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Paging Fields", errors.get(0).getField());
        assertEquals("Both must be empty or filled!", errors.get(0).getMessage());

    }

    @Test
    public void shouldReturnErrorIfOneFieldInPagingIsZero() {
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", 10, 0);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Paging Fields", errors.get(0).getField());
        assertEquals("Both must be grater than 0.", errors.get(0).getMessage());

    }





}