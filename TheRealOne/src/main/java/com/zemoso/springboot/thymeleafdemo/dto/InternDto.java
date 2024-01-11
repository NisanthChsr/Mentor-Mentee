package com.zemoso.springboot.thymeleafdemo.dto;

import com.zemoso.springboot.thymeleafdemo.entity.Mentors;

import javax.validation.constraints.*;

public class InternDto {

    private int id;

    @NotNull(message="is required")
    private String firstName;

    @NotNull(message="is required")
    private String lastName;

    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=100, message="must be less than or equal to 100")
    private int marks;

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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Mentors getMentor() {
        return mentor;
    }

    public void setMentor(Mentors mentor) {
        this.mentor = mentor;
    }

    public InternDto()
    {

    }

    public InternDto(int id,String firstName, String lastName, int marks) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
    }

}
