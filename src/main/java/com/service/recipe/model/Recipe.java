package com.service.recipe.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Recipe implements Serializable {

    private static final long serialVersionUID = 14444345654L;

    @Column(unique = true)
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;
    private String content;
    private String category;
    private String img;
    private Date createdTimeDate;
    private String createdBy;

    public Recipe() {
    }

    public Recipe(String name, Integer id, String content, String category, String img, Date createdTimeDate, String createdBy) {
        this.name = name;
        this.id = id;
        this.content = content;
        this.category = category;
        this.img = img;
        this.createdTimeDate = createdTimeDate;
        this.createdBy = createdBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreatedTimeDate() {
        return createdTimeDate;
    }

    public void setCreatedTimeDate(Date createdTimeDate) {
        this.createdTimeDate = createdTimeDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
