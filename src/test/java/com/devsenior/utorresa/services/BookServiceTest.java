package com.devsenior.utorresa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.utorresa.exceptions.NotFoundException;

public class BookServiceTest {

    private BookService service; 

    @BeforeEach
    void setup() {
        service = new BookService();
    }
    @Test
    void testAddBook() throws NotFoundException {
        //GIVEN -Preparacion de los datos para la prueba
        String isbn = "123456789";
        String tittle = "The Lord of the Rings";
        String author = "J. R. R. Tolkien";
        //WHEN -Ejecutar el metosd a probar
        service.addBook(isbn, tittle, author);
        //THEN -verificaciones de que el metodo funciona como se espera
        var book = service.getBookByIsbn(isbn);
        assertNotNull(book);
        assertEquals(tittle, book.getTittle());
        assertEquals(author, book.getAuthor());

    }

    @Test
    void testDeleteExistingBook() throws NotFoundException {
        // GIVEN
        String isbn = "123456789";
        String tittle = "The Lord of the Rings";
        String author = "J. R. R. Tolkien";
        service.addBook(isbn, tittle, author);
        // WHEN
        service.deleteBook(isbn);
        // THEN
        assertEquals(0, service.getAllBooks().size());  

    }
    
    @Test
    void testDeleteNonExistingBook() {
        //GIVEN
        String isbn = "123456789";
        // WHEN - THEN 
        assertThrows(NotFoundException.class,()->{service.deleteBook(isbn);});


    }

    @Test
    void testGetAllBooks() {
        // GIVEN
        String isbn = "123456789";
        String tittle = "The Lord of the Rings";
        String author = "J. R. R. Tolkien";
        service.addBook(isbn, tittle, author);
        // WHEN
        var books = service.getAllBooks();
        // THEN
        assertEquals(1, books.size());  


    }

    @Test
    void testGetBookByIsbn() throws NotFoundException {
        // GIVEN
        String isbn = "123456789";
        String tittle = "The Lord of the Rings";
        String author = "J. R. R. Tolkien";
        service.addBook(isbn, tittle, author);
        // WHEN
        var book = service.getBookByIsbn(isbn);
        // THEN
        assertNotNull(book); 
        assertEquals(tittle, book.getTittle());
        assertEquals(author, book.getAuthor());   

    }
}
