package com.devsenior.utorresa.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.devsenior.utorresa.exceptions.NotFoundException;

public class UserServiceTest {

    private UserService service; 

    @BeforeEach
    void setup() {
        service = new UserService();
    }
    @Test
    void testAddUser() throws NotFoundException {
        //GIVEN -Preparacion de los datos para la prueba
        var id = "123";
        var name = "Javier";
        var email = "javier@gmail";
        //WHEN -Ejecutar el metodos a probar 
        service.addUser(id, name, email, LocalDate.now());
        //THEN -verificaciones de que el metodo funciona como se espera
        var user = service.getUserById(id);
        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());  

    }

    @Test
    void testDeleteUser() {
        //GIVEN
        var id = "123";
        // WHEN - THEN 
        assertThrows(NotFoundException.class,()->{service.deleteUser(id);});
        

    }

    @Test
    void testGetAllUsers() {
        // GIVEN
        var id = "123";
        var name = "Javier";
        var email = "javier@gmail";
        service.addUser(id, name, email, LocalDate.now());
        // WHEN
        var users = service.getAllUsers();
        // THEN
        assertEquals(1, users.size());  

    }

    @Test
    void testGetUserById() throws NotFoundException {
        // GIVEN
        var id = "123";
        var name = "Javier";
        var email = "javier@gmail";
        service.addUser(id, name, email, LocalDate.now());
        // WHEN
        var user = service.getUserById(id);
        // THEN
        assertNotNull(user);

    }

    @Test
    void testUpdateUserEmail() throws NotFoundException {
        //GIVEN -Preparacion de los datos para la prueba
        var id = "123";
        var email = "javier@gmail";
        //WHEN -Ejecutar el metodos a probar 
        service.updateUserEmail(id, email);
        //THEN -verificaciones de que el metodo funciona como se espera
        var user = service.getUserById(id);
        assertNotNull(user);
        assertEquals(email, user.getEmail());     


    }

    @Test
    void testUpdateUserName() throws NotFoundException {
        //GIVEN -Preparacion de los datos para la prueba
        var id = "123";
        var name = "Javier";
        //WHEN -Ejecutar el metodos a probar 
        service.updateUserName(id, name);
        //THEN -verificaciones de que el metodo funciona como se espera
        var user = service.getUserById(id);
        assertNotNull(user);
        assertEquals(name, user.getName());  

    }
}
