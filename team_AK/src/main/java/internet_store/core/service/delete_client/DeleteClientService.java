package internet_store.core.service.delete_client;

import internet_store.ProductListApplication;
import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;
import internet_store.core.request.delete_client.DeleteClientRequest;
import internet_store.core.response.delete_client.DeleteClientResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.client_database.InnerClientDatabase;

import java.util.List;

public class DeleteClientService {
    private final InnerClientDatabase clientDatabase;

    public DeleteClientService(InnerClientDatabase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }

    public DeleteClientResponse execute(DeleteClientRequest deleteClientRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(deleteClientRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (isIdExist(deleteClientRequest.getId())) {
            Client deletedClient = findProductById(deleteClientRequest.getId());
            ProductListApplication.clientDatabase.deleteClient(deletedClient);
        } else {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new DeleteClientResponse(deleteClientRequest.getId());
        }
        return new DeleteClientResponse(errors);
    }

    private boolean isIdExist(long id) {
        return clientDatabase.isIdExist(id);
    }

    private Client findProductById(long id) {
        return clientDatabase.findById(id);
    }
}