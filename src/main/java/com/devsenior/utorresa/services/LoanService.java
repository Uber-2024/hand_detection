package com.devsenior.utorresa.services;

import java.util.ArrayList;
import java.util.List;
import com.devsenior.utorresa.exceptions.NotFoundException;
import com.devsenior.utorresa.model.Loan;
import com.devsenior.utorresa.model.LoanState;

public class LoanService {

    private BookService bookService = new BookService();
    private UserService userService = new UserService();
    private List<Loan> loans;

    public LoanService(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loans = new ArrayList<>();
    }    

    public void addLoan(String id,String isbn) throws NotFoundException{
        var user = userService.getUserById(id);
        var book = bookService.getBookByIsbn( isbn);
        loans.add(new Loan(user, book));
        
    }

    public void returnBook(String id,String isbn) throws NotFoundException{
        for (var loan : loans) {
            if(loan.getUser().getId().equals(id) && loan.getBook().getIsbn().equals(isbn)&& loan.getState().equals(LoanState.STARTED)){ 
                loan.setState(LoanState.FINISHED);
                return;
            }   
        } throw new NotFoundException("No se encontro el prestamo del libro con isbn: " + isbn + " para el usuario con id : " + id);  
    }   

    public List<Loan> getLoans() {
        return loans;
    }   
    

}
