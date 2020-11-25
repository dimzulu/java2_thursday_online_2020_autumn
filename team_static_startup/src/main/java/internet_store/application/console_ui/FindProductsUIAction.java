package internet_store.application.console_ui;

import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.requests.Ordering;
import internet_store.application.core.requests.Paging;
import internet_store.application.core.responses.FindProductsResponse;
import internet_store.application.core.services.FindProductsService;

import java.util.Scanner;

public class FindProductsUIAction implements UIAction {

    private final FindProductsService findProductsService;

    public FindProductsUIAction(FindProductsService findProductsService) {
        this.findProductsService = findProductsService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter ordering. By 'Name' or by 'Description' ");
        String orderingType = scanner.nextLine();
        System.out.print("Enter ordering 'Ascending' or by 'Descending' ");
        String orderingDirection = scanner.nextLine();

        System.out.print("Enter ordering. By 'Name' or by 'Description' ");
        Integer pageNumber = scanner.nextInt();
        System.out.print("Enter ordering 'Ascending' or by 'Descending' ");
        Integer pageSize = scanner.nextInt();

        Ordering ordering = new Ordering(orderingType, orderingDirection);
        Paging paging = new Paging(pageNumber, pageSize);
        FindProductsRequest request = new FindProductsRequest(name, description, ordering, paging);
        FindProductsResponse response = findProductsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {

            if (!response.getProducts().isEmpty()) {
                System.out.println("\nFound " + response.getProducts().size() + " product(s) in the database : ");
                response.getProducts().forEach(System.out::println);
            } else {
                System.out.println("\nNo product with name = " + name + " found in the database");

            }
        }
    }

}
