package com.devsenior.utorresa.model;

public class Book {
    private String isbn;
    private String tittle;
    private String author;
    
    public Book(String isbn, String tittle, String author) {
        this.isbn = isbn;
        this.tittle = tittle;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTittle() {
        return tittle;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isLoaned() {
        return loaned;
    }    

    public void setLoaned(boolean loaned) {
        this.loaned = loaned;
    }    

    private boolean loaned; // Indica si el libro está prestado o no
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }    

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }    

    public void setAuthor(String author) {
        this.author = author;
    }       
    

}
