package com.community101.core;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jiaoming on 7/16/15.
 */
@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Goods> goodses;

    public Category(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(Set<Goods> goodses) {
        this.goodses = goodses;
    }
}
