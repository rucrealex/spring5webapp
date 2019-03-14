package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PubisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap  {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PubisherRepository pubisherRepository;

    @Autowired
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PubisherRepository pubisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.pubisherRepository = pubisherRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Author robert = new Author("Robert", "Gelbraith");
        Publisher attics = new Publisher("Attics", "NY Five Avenue str, 5");
        Book book1 = new Book("The cuckoo's calling", "1-234234", attics);
        Book book2 = new Book("Career of Devil", "1-234-124", attics);
        robert.getBooks().add(book1);
        robert.getBooks().add(book2);
        book1.getAuthors().add(robert);
        book2.getAuthors().add(robert);

        pubisherRepository.save(attics);
        authorRepository.save(robert);
        bookRepository.save(book1);
        bookRepository.save(book2);

        Author rod = new Author("Rod", "Johnson");
        Publisher corona = new Publisher("Corona", "LA, Union square, 7-12");
        Book book3 = new Book("J2EE Development without EJB", "12334", corona);
        rod.getBooks().add(book3);
        book3.getAuthors().add(rod);

        pubisherRepository.save(corona);
        authorRepository.save(rod);
        bookRepository.save(book3);
    }
}



