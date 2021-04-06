package com.example.projeto;

public class Utilizador {

    int id;
    String username, password;

    public Utilizador() {
        id = 0;
        username = "";
        password = "";
    }

    public Utilizador(int id) {
        this.id = id;
        username = "";
        password = "";
    }

    public Utilizador(String username, String password) {
        id = 0;
        this.username = username;
        this.password = password;
    }

    public Utilizador(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Utilizador(Utilizador u) {
        id = u.id;
        username = u.username;
        password = u.password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
