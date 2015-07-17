package com.community101.core;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jiaoming on 7/16/15.
 */
@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @Column(name = "tel_phone")
    private String telPhone;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Orders> orderses;

    public User() {
        }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public Set<Orders> getOrderses() {
        return orderses;
    }

    public void setOrderses(Set<Orders> orderses) {
        this.orderses = orderses;
    }
}
