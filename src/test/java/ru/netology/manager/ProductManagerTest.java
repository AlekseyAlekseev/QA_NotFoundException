package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.SmartPhone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class ProductManagerTest {


    private ProductRepository repository = Mockito.mock(ProductRepository.class);

    private ProductManager manager = new ProductManager(repository);

    private Book book1 = new Book(1, "Три дня индиго", 1000, "Сергей Лукьяненко");
    private Book book2 = new Book(2, "Война и мир. Том 1", 800, "Лев Толстой");
    private Book book3 = new Book(3, "Война и мир. Том 2", 780, "Лев Толстой");
    private Book book4 = new Book(4, "Анна Каренина", 1000, "Лев Толстой");
    private Book book5 = new Book(5, "Исповедь", 687, "Лев Толстой");

    private SmartPhone samsungGalaxy = new SmartPhone(6, "Samsung Galaxy", 21000, "Samsung");
    private SmartPhone samsungGalaxyNote = new SmartPhone(7, "Samsung Galaxy Note", 31000, "Samsung");
    private SmartPhone iphone11 = new SmartPhone(8, "Iphone 11", 33000, "Apple");
    private SmartPhone iphone12Pro = new SmartPhone(9, "Iphone 12 Pro", 45000, "Apple");
    private SmartPhone iphoneXR = new SmartPhone(10, "Iphone XR", 37000, "Apple");



    @Test
    void shouldPhoneSearchProducer() {
        Product[] returned = {iphone12Pro, iphone11};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {iphone12Pro, iphone11};
        Product[] actual = manager.searchBy("Apple");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void shouldPhoneSearchName() {
        Product[] returned = {iphoneXR, iphone11, iphone12Pro};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {iphoneXR, iphone11, iphone12Pro};
        Product[] actual = manager.searchBy("Iphone");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();

    }

    @Test
    void shouldBookSearchProducer() {
        Product[] returned = {book2, book3, book4, book5};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {book2, book3, book4, book5};
        Product[] actual = manager.searchBy("Лев Толстой");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void shouldBookSearchName() {
        Product[] returned = {book2, book3};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {book2, book3};
        Product[] actual = manager.searchBy("Война и мир");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();
    }

    @Test
    void shouldSearchEmpty() {
        Product[] returned = {};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {};
        Product[] actual = manager.searchBy("Привет");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();

    }

    @Test
    void shouldSearchBookFalse() {
        Product[] returned = {book1, book2, book3};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {};
        Product[] actual = manager.searchBy("Книга");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();

    }

    @Test
    void shouldSearchPhoneFalse() {
        Product[] returned = {iphone11, iphone12Pro, iphoneXR};
        doReturn(returned).when(repository).findAll();

        Product[] expected = {};
        Product[] actual = manager.searchBy("Телефон");

        assertArrayEquals(expected, actual);
        verify(repository).findAll();

    }
}