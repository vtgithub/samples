package com.jfms.authentication.authentication.dal.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_entity")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String mobileNumber;
    @Column
    private String firstName;
    @Column
    private String lastName;

//    protected UserEntity(){}

    public UserEntity(String mobileNumber, String firstName, String lastName) {
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
}
