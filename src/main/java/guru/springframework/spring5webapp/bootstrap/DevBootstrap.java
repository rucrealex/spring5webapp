package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap  {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Author robert = new Author("Robert", "Gelbraith");
        Book book1 = new Book("The cuckoo's calling", "1-234234", "Attics");
        Book book2 = new Book("Career of Devil", "1-234-124", "Attics");
        robert.getBooks().add(book1);
        robert.getBooks().add(book2);
        book1.getAuthors().add(robert);
        book2.getAuthors().add(robert);

        authorRepository.save(robert);
        bookRepository.save(book1);
        bookRepository.save(book2);

        Author rod = new Author("Rod", "Johnson");
        Book book3 = new Book("J2EE Development without EJB", "12334", "Corona");
        rod.getBooks().add(book3);
        book3.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(book3);
    }
}



