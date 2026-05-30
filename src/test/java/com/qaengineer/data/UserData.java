package com.qaengineer.data;

public class UserData {

    // Estos atributos mapean exactamente los campos del JSON
    private String username;
    private String password;
    private String expectedUrl;
    private String expectedError;

    // Getters — permiten leer los valores desde los tests
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getExpectedUrl() { return expectedUrl; }
    public String getExpectedError() { return expectedError; }

    // toString — útil para ver los datos en los logs
    @Override
    public String toString() {
        return "UserData{username='" + username + "', password='" + password + "'}";
    }
}