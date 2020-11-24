package internet_store.application.core.services.validators;

import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.requests.Ordering;
import internet_store.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindProductsRequestValidator {

    public static final String ORDERING_NAME_1 = "Name";
    public static final String ORDERING_NAME_2 = "Description";
    public static final String ORDERING_DIRECTION_1 = "Ascending";
    public static final String ORDERING_DIRECTION_2 = "Descending";

    public List<CoreError> validate(FindProductsRequest request) {
        return new ArrayList<>(validateSearchFields(request));
    }

    private List<CoreError> validateSearchFields(FindProductsRequest request) {
        Ordering ordering = request.getOrdering();
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getDescription())) {
            errors.add(new CoreError("Name", "Must not be empty!"));
            errors.add(new CoreError("Description", "Must not be empty!"));
        } else if (ordering != null) {
            orderingValidation(ordering, errors);
        } else if (request.getPageNumber() == null || request.getPageSize() == null) {
            if (request.getPageNumber() != null || request.getPageSize() != null) {
                errors.add(new CoreError("Paging Fields", "Both must be empty or filled!"));
            }
        } else if (request.getPageNumber() <= 0 || request.getPageSize() <= 0) {
            errors.add(new CoreError("Paging Fields", "Both must be grater than 0."));
        }
        return errors;
    }

    private List<CoreError> orderingValidation(Ordering ordering, List<CoreError> errors) {
        if (isEmpty(ordering.getOrderBy()) || isEmpty(ordering.getOrderDirection())) {
            errors.add(new CoreError("Ordering Fields", "Both must be empty or filled!"));
        } else if (inCorrectOrderingNames(ordering)) {
            errors.add(new CoreError("Ordering by", "Must be Name or Description."));
        } else if (inCorrectOrderingDirection(ordering)) {
            errors.add(new CoreError("Direction", "Must be Ascending or Descending."));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean inCorrectOrderingNames(Ordering ordering) {
        return !ORDERING_NAME_1.equalsIgnoreCase(ordering.getOrderBy())
                && !ORDERING_NAME_2.equalsIgnoreCase(ordering.getOrderBy());
    }

    private boolean inCorrectOrderingDirection(Ordering ordering) {
        return !ORDERING_DIRECTION_1.equalsIgnoreCase(ordering.getOrderDirection())
                && !ORDERING_DIRECTION_2.equalsIgnoreCase(ordering.getOrderDirection());
    }


}
