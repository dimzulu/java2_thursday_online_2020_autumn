package internet_store.core.service.find_by_order_number;

import internet_store.core.domain.Order;
import internet_store.core.request.find_by_order_number.FindByOrderNumberRequest;
import internet_store.database.order_database.InnerOrderDatabase;

public class FindByOrderNumberService {
    private final InnerOrderDatabase orderDatabase;

    public FindByOrderNumberService(InnerOrderDatabase orderDatabase) {
        this.orderDatabase = orderDatabase;
    }

    public Order execute(FindByOrderNumberRequest findByOrderNumberRequest) {
        return orderDatabase.findByOrderNumber(findByOrderNumberRequest.getOrderNumber());
    }
}
