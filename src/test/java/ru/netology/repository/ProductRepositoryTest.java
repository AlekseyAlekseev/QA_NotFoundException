package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book coreJava = new Book();

    private Book book1 = new Book(1, "Три дня индиго", 1000, "Сергей Лукьяненко");
    private Book book2 = new Book(2, "Война и мир. Том 1", 800, "Лев Толстой");
    private Book book3 = new Book(3, "Война и мир. Том 2", 780, "Лев Толстой");

    @Test
    void shouldSaveOneItem() {
        repository.save(coreJava);

        Product[] expected = new Product[]{coreJava};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);

        repository.removeById(2);
        Product[] expected = {book1, book3};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByInvalidId() {
        repository.save(book1);
        repository.save(book2);
        repository.save(book3);


        assertThrows(NotFoundException.class, () -> {
           repository.removeById(5);
        });
    }
}