package com.devsenior.utorresa;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.devsenior.utorresa.exceptions.NotFoundException;
import com.devsenior.utorresa.services.BookService;

public class Main {
    public static void main(String[] args) throws NotFoundException {
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookService(); // Inicializar BookService
        int option = -1;

        do {
            try {
                System.out.println("\n=== Menú de la Aplicación ===");
                System.out.println("1. Agregar un libro");
                System.out.println("2. Listar todos los libros");
                System.out.println("3. Eliminar un libro");
                System.out.println("4. Prestar un libro");
                System.out.println("5. Entregar un libro");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                option = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (option) {
                    case 1:
                        String isbn;
                        String title;
                        String author;                                              
                        
                            // Validar ISBN
                            System.out.println("Has seleccionado la opción de agregar un libro.");
                            do {
                                System.out.print("Ingrese el ISBN (no puede estar vacío): ");
                                isbn = scanner.nextLine().trim();
                                if (isbn.isEmpty()) {
                                    System.out.println("Error: El ISBN no puede estar vacío. Intente de nuevo.");
                                }
                            } while (isbn.isEmpty());
                        
                            // Validar título
                            do {
                                System.out.print("Ingrese el título (no puede estar vacío): ");
                                title = scanner.nextLine().trim();
                                if (title.isEmpty()) {
                                    System.out.println("Error: El título no puede estar vacío. Intente de nuevo.");
                                }
                            } while (title.isEmpty());
                        
                            // Validar autor
                            do {
                                System.out.print("Ingrese el autor (no puede estar vacío): ");
                                author = scanner.nextLine().trim();
                                if (author.isEmpty()) {
                                    System.out.println("Error: El autor no puede estar vacío. Intente de nuevo.");
                                }
                            } while (author.isEmpty());
                        
                            // Agregar el libro si todos los datos son válidos
                            bookService.addBook(isbn, title, author);
                            System.out.println("Libro agregado exitosamente.");
                            break;                        

                    case 2:
                        System.out.println("Has seleccionado la opción de listar todos los libros.");
                       try {
                                               
                        bookService.getAllBooks().forEach(book -> 
                            System.out.println("ISBN: " + book.getIsbn() + ", Título: " + book.getTittle() + ", Autor: " + book.getAuthor())
                        );
                        } catch (Exception e) {
                            System.out.println("Error No hay ninguna lista de libros: " + e.getMessage());
                        }
                        
                        break;

                    case 3:
                        System.out.println("Has seleccionado la opción de eliminar un libro.");
                        System.out.print("Ingrese el ISBN del libro a eliminar: ");
                        String deleteIsbn = scanner.nextLine();
                        try {
                            bookService.deleteBook(deleteIsbn);
                            System.out.println("Libro eliminado exitosamente.");
                        } catch (NotFoundException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("Has seleccionado la opción de prestar un libro.");
                        System.out.print("Ingrese el ISBN del libro a prestar: ");
                        String loanIsbn = scanner.nextLine();
                        bookService.loanBook(bookService, loanIsbn); // Método para prestar un libro
                        System.out.println("Libro prestado exitosamente.");
                        break;

                    case 5:
                        System.out.println("Has seleccionado la opción de entregar un libro.");
                        System.out.print("Ingrese el ISBN del libro a entregar: ");
                        String returnIsbn = scanner.nextLine();
                        bookService.returnBook(returnIsbn); // Método para entregar un libro
                        System.out.println("Libro entregado exitosamente.");
                        break;

                    case 0:
                        System.out.println("Saliendo de la aplicación...Gracias por usar nuestro sistema.");
                        break;

                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
            }
        } while (option != 0);

        scanner.close();
    }


}
