package internet_store.core.service.delete_product;

import internet_store.ProductListApplication;
import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.delete_product.DeleteProductRequest;
import internet_store.core.response.delete_product.DeleteProductResponse;
import internet_store.core.validate.NegativeNumberValidator;
import internet_store.database.product_database.InnerProductDatabase;

import java.util.List;

public class DeleteProductService {
    private final InnerProductDatabase productDatabase;

    public DeleteProductService(InnerProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public DeleteProductResponse execute(DeleteProductRequest deleteProductRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(deleteProductRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        if (isIdExist(deleteProductRequest.getId())) {
            Product deletedProduct = findProductById(deleteProductRequest.getId());
            ProductListApplication.productDatabase.deleteProduct(deletedProduct);
        } else {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new DeleteProductResponse(deleteProductRequest.getId());
        }
        return new DeleteProductResponse(errors);
    }

    private boolean isIdExist(long id) {
        return productDatabase.isIdExist(id);
    }

    private Product findProductById(long id) {
        return productDatabase.findById(id);
    }
}