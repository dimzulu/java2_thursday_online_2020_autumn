package team_static_startup.lesson_1;

public interface ProductDatabase {

    Long save(Product product);

    boolean delete(Long productId);

    boolean delete(Product product);

    void deleteByProductName(String product);

//    Optional<Book> findById(Long bookId);

//    List<Book> findByAuthor(String author);

//    List<Book> findByTitle(String title);

//    int countAllBooks();

//    void deleteByTitle(String title);

//    List<Book> find(SearchCriteria searchCriteria);

//    Set<String> findUniqueAuthors();

//    Set<String> findUniqueTitles();

//    Set<Book> findUniqueBooks();

//    boolean contains(Book book);

//    Map<String, List<Book>> getAuthorToBooksMap();

//    Map<String, Integer> getEachAuthorBookCount();

}
