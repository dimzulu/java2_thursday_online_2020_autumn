package internet_store.application.core.requests;

public class FindProductsRequest {

    private final String name;
    private final String description;
    private Ordering ordering;
    private Integer pageNumber;
    private Integer pageSize;

    public FindProductsRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public FindProductsRequest(String name, String description, Ordering ordering) {
        this.name = name;
        this.description = description;
        this.ordering = ordering;
    }

    public FindProductsRequest(String name, String description, Ordering ordering, Integer pageNumber, Integer pageSize) {
        this.name = name;
        this.description = description;
        this.ordering = ordering;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public FindProductsRequest(String name, String description, Integer pageNumber, Integer pageSize) {
        this.name = name;
        this.description = description;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public boolean isNameProvided() {
        return this.name != null && !this.name.isEmpty();
    }

    public boolean isDescriptionProvided() {
        return this.description != null && !this.description.isEmpty();
    }


}
