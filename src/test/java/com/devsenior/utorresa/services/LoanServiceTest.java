package com.devsenior.utorresa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.devsenior.utorresa.exceptions.NotFoundException;
import com.devsenior.utorresa.model.Book;
import com.devsenior.utorresa.model.LoanState;
import com.devsenior.utorresa.model.User;

public class LoanServiceTest {
    private BookService bookService;
    private UserService userService;
    private LoanService service;
    
    @BeforeEach
    void setup() {
        bookService= Mockito.mock(BookService.class); 
        userService= Mockito.mock(UserService.class); 
        service= new LoanService(bookService, userService);
       
        
    }
    @DisplayName("Agregar un préstamo con un usuario existente y un libro existente")
    @Test
    void testAddLoanWithExistingUserAndExistingBook() throws NotFoundException {
                
        //GIVEN
        var id="123";
        var isbn="1234567890";
        var mockUser = new User(id, "Javier", "javier@gmail"); 
        var mockBook = new Book(isbn, "The Lord of the Rings", "J. R. R. Tolkien");
        Mockito.when(userService.getUserById(id)).thenReturn(mockUser);
        Mockito.when(bookService.getBookByIsbn(isbn)).thenReturn(mockBook);
        //WHEN
        service.addLoan(id, isbn);
        //THEN 
        var loans = service.getLoans(); 
        assertEquals(1, loans.size());
        var loan = loans.get(0);
        assertNotNull(loan.getUser());
        assertNotNull(loan.getBook());
        assertEquals(LoanState.STARTED, loan.getState());
        assertEquals(id, loan.getUser().getId());
        assertEquals(isbn, loan.getBook().getIsbn());

    }
    
    @Test
    void testReturnBook() throws NotFoundException {
        //GIVEN
        var id="123";
        var isbn="1234567890";
        var mockUser = new User(id, "Javier", "javier@gmail"); 
        var mockBook = new Book(isbn, "The Lord of the Rings", "J. R. R. Tolkien");
        Mockito.when(userService.getUserById(id)).thenReturn(mockUser);
        Mockito.when(bookService.getBookByIsbn(isbn)).thenReturn(mockBook);
        service.addLoan(id, isbn);
        //WHEN
        service.returnBook(id, isbn);
        //THEN 
        var loans = service.getLoans(); 
        assertEquals(1, loans.size());
        var loan = loans.get(0);
        assertNotNull(loan.getUser());
        assertNotNull(loan.getBook());
        assertEquals(LoanState.FINISHED, loan.getState());
        assertEquals(id, loan.getUser().getId());
        assertEquals(isbn, loan.getBook().getIsbn());

    }

    @DisplayName("Agregar un préstamo con un usuario inexistente y un libro inexistente")
    @Test
    void testAddLoanWithNonExistingUserAndNotExistingBook() throws NotFoundException  {

        //GIVEN
        var id="123";
        var isbn="1234567890";
        Mockito.when(userService.getUserById(id)).thenThrow(new NotFoundException("No se encontro el usuario con id: " + id));
        Mockito.when(bookService.getBookByIsbn(isbn)).thenThrow(new NotFoundException("No se encontro el libro con isbn: " + isbn));
        //THEN-WHEN
        assertThrows(NotFoundException.class, () -> service.addLoan(id, isbn)); 
       
                    
          

    }
}
