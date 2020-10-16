package br.unicamp.students.Retrofit.Objects;

public class Student {
    private String RA, name, email;


    public Student(String RA, String name, String email) {
        this.setRA(RA);
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

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }
}
