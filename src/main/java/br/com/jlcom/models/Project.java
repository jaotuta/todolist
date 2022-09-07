package br.com.jlcom.models;

import java.util.Date;

public class Project {
    private int idProject;
    private String name;
    private String description;
    private Date createdDate;
    private Date updatedDate;

    public Project(int idProject, String name, String description, Date createdDate, Date updatedDate) {
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public Project() {
        this.createdDate = new Date();
        this.updatedDate = new Date();

    }
    public int getId() {
        return idProject;
    }

    public void setId(int id) {
        this.idProject = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
