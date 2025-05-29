package com.devsenior.utorresa.services;

import com.devsenior.utorresa.exceptions.NotFoundException;
import com.devsenior.utorresa.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookService {
    private static final Logger logger = Logger.getLogger(BookService.class.getName()); // Logger para BookService
    private List<Book> books;

    public BookService() {
        this.books = new ArrayList<>();
        logger.info("BookService inicializado.");
    }

    public void addBook(String isbn, String title, String author) {
        boolean exists = books.stream().anyMatch(book -> book.getIsbn().equals(isbn));
        if (exists) {
            logger.warning("Intento de agregar un libro duplicado: ISBN=" + isbn);
            throw new IllegalArgumentException("El libro con ISBN " + isbn + " ya existe.");
        }
        books.add(new Book(isbn, title, author));
        logger.info("Libro agregado: ISBN=" + isbn + ", Título=" + title + ", Autor=" + author);
    }

    public List<Book> getAllBooks() {
        logger.info("Obteniendo todos los libros.");
        return books;
    }

    public void deleteBook(String isbn) throws NotFoundException {
        Book book = getBookByIsbn(isbn);
        books.remove(book);
        logger.info("Libro eliminado: ISBN=" + isbn);
    }

    public void loanBook(BookService bookService, String isbn) throws NotFoundException {
        Book book = getBookByIsbn(isbn);
        if (book.isLoaned()) {
            logger.warning("Intento de prestar un libro ya prestado: ISBN=" + isbn);
            throw new UnsupportedOperationException("El libro ya está prestado.");
        }
        book.setLoaned(true);
        logger.info("Libro prestado: ISBN=" + isbn);
    }

    public void returnBook(String isbn) throws NotFoundException {
        Book book = getBookByIsbn(isbn);
        if (!book.isLoaned()) {
            logger.warning("Intento de devolver un libro que no está prestado: ISBN=" + isbn);
            throw new UnsupportedOperationException("El libro no está prestado.");
        }
        book.setLoaned(false);
        logger.info("Libro devuelto: ISBN=" + isbn);
    }

    Book getBookByIsbn(String isbn) throws NotFoundException {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> {
                    logger.warning("Libro no encontrado: ISBN=" + isbn);
                    return new NotFoundException("El libro con ISBN " + isbn + " no fue encontrado.");
                });
    }
}


