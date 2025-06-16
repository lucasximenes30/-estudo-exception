package br.com.dio.model;

import java.time.OffsetDateTime;
import java.util.Objects;

public class UserModel {
    private Long id;
    private String name;
    private String email;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(name, userModel.name) && Objects.equals(email, userModel.email) && Objects.equals(birthDate, userModel.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birthDate);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OffsetDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(OffsetDateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public UserModel() {

    }

    public UserModel(Long id, String name, String email, OffsetDateTime birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    private OffsetDateTime birthDate;

}
