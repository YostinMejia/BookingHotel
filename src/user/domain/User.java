package user.domain;

import java.util.Date;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private Date createdAt;

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = new Date(); // Establecer la fecha de creación como la fecha actual
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public String getPassword() {
        return password;
    }
    public String getId() {
        return id;
    }
}
