package guru.springframework.spring5webapp.repositories;

import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
