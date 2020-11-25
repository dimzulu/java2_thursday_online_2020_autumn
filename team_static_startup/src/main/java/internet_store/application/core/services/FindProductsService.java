package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.requests.Ordering;
import internet_store.application.core.requests.Paging;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.FindProductsResponse;
import internet_store.application.core.services.validators.FindProductsRequestValidator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindProductsService {

    private final Database database;
    private final FindProductsRequestValidator validator;

    public FindProductsService(Database database,
                               FindProductsRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindProductsResponse execute(FindProductsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindProductsResponse(null, errors);
        }

        List<Product> products = search(request);
        products = order(products, request.getOrdering());
        products = paging(products, request.getPaging());

        return new FindProductsResponse(products, null);
    }

    private List<Product> paging(List<Product> products, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return products.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }

    private List<Product> search(FindProductsRequest request) {
        List<Product> products = null;
        if (request.isNameProvided() && !request.isDescriptionProvided()) {
            products = database.findByProductName(request.getName());
        }
        if (!request.isNameProvided() && request.isDescriptionProvided()) {
            products = database.findByProductDescription(request.getDescription());
        }
        if (request.isNameProvided() && request.isDescriptionProvided()) {
            products = database.findByNameAndDescription(request.getName(), request.getDescription());
        }
        return products;
    }

    private List<Product> order(List<Product> productList, Ordering ordering){
        if (ordering != null) {
            Comparator<Product> comparator = ordering.getOrderBy().equals("Name")
                    ? Comparator.comparing(Product::getName)
                    : Comparator.comparing(Product::getDescription);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return productList.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return productList;
        }
    }

}
