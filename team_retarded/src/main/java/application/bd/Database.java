package application.bd;

import application.items.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Database {

    long add(String productName, String specification, double price);

    boolean delete(long id);

    void delete(Predicate<Product> predicate);

    void clear();

    Optional<Product> getById(long id);

    List<Product> filter(Predicate<Product> predicate);

    List<Product> getList();

}
