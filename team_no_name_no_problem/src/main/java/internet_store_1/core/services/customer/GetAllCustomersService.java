package internet_store_1.core.services.customer;

import internet_store_1.core.requests.customer.GetAllCustomersRequest;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.customer.GetAllCustomersResponse;
import internet_store_1.database.customer.CustomerDatabase;
import internet_store_1.core.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class GetAllCustomersService {

    private final CustomerDatabase customerDatabase;

    public GetAllCustomersService(CustomerDatabase customerDatabase) {
        this.customerDatabase = customerDatabase;
    }

    public GetAllCustomersResponse execute(GetAllCustomersRequest getAllCustomersRequest){
        List<CoreError> errors = new ArrayList<>();

        if (customerDatabase.getCustomers().isEmpty()){
            errors.add(new CoreError("database", "Customer database is empty"));
            return new GetAllCustomersResponse(errors, new ArrayList<>());
            }

        List<Customer> customers = customerDatabase.getCustomers();
        return new GetAllCustomersResponse(customers);
        }
}