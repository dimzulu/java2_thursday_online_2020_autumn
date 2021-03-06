package internet_store.core.service.delete_client;

import internet_store.ProductListApplication;
import internet_store.core.domain.Client;
import internet_store.core.request.delete_client.DeleteClientRequest;
import internet_store.core.response.delete_client.DeleteClientResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteClientServiceTest {
    DeleteClientService deleteService = new DeleteClientService(ProductListApplication.clientDatabase);

    @Test
    public void shouldReturnNoErrors() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Name");
        client.setSurname("Surname");
        client.setPhoneNumber("29336699");
        client.setEmail("a@a.lv");
        ProductListApplication.clientDatabase.addClient(client);

        DeleteClientResponse response = deleteService.execute(new DeleteClientRequest(1L));
        assertEquals(1, response.getId());
    }

    @Test
    public void shouldReturnError_1() {
        DeleteClientResponse response = deleteService.execute(new DeleteClientRequest(-1L));
        assertEquals("Long input error ", response.getErrors().get(0).getField());
        assertEquals("only positive number allowed", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldReturnError_2() {
        DeleteClientResponse response = deleteService.execute(new DeleteClientRequest(25L));
        assertEquals("Id error ", response.getErrors().get(0).getField());
        assertEquals("wrong ID", response.getErrors().get(0).getMessage());
    }
}