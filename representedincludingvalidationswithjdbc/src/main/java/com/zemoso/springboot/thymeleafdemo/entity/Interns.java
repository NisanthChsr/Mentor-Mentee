package com.zemoso.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="interns")
public class Interns {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="intern_id")
    private int id;

    @NotNull(message="is required")
    @Column(name="first_name")
    private String firstName;

    @NotNull(message="is required")
    @Column(name="last_name")
    private String lastName;

    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=100, message="must be less than or equal to 100")
    @Column(name="marks")
    private int marks;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "mentor_id")
    private Mentors mentor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Mentors getMentor() {
        return mentor;
    }

    public void setMentor(Mentors mentor) {
        this.mentor = mentor;
    }

    public Interns()
    {

    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Interns(int id,String firstName, String lastName, int marks) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
    }
}
