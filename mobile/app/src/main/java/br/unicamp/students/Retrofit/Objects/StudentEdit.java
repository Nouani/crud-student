package br.unicamp.students.Retrofit.Objects;

public class StudentEdit {
    private String name, email;


    public StudentEdit(String name, String email) {
        this.setName(name);
        this.setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
