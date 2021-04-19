package com.zemoso.springboot.thymeleafdemo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mentors")
public class Mentors {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="mentor_id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy = "mentor",
            cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Interns> theInterns;

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

    public List<Interns> getTheInterns() {
        return theInterns;
    }

    public void setTheInterns(List<Interns> theInterns) {
        this.theInterns = theInterns;
    }

    public void add(Interns theIntern)
    {
        if(theInterns==null)
        {
            theInterns=new ArrayList<>();
        }
        theInterns.add(theIntern);
        theIntern.setMentor(this);
    }
    public Mentors(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Mentors()
    {

    }
}
