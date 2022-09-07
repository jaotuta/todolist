package br.com.jlcom.models;

import java.util.Date;

public class Task {
    private int idTask;
    private int idProject;
    private String name;
    private String description;
    private Boolean status;
    private String notes;
    private Date deadLine;
    private Date createdDate;
    private Date updatedDate;

    public Task(int idTask, int idProject, String name, String description, Boolean status, Date deadLine, Date createdDate, Date updatedDate, String notes) {
        this.idTask = idTask;
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.status = status;
        this.deadLine = deadLine;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.notes = notes;

    }

    public Task () {

        this.createdDate = new Date();
        this.updatedDate = new Date();
    }


    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", idProject=" + idProject +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", deadLine=" + deadLine +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
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


}
