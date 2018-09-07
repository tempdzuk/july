package com.test.julyOld.entity;

import javax.persistence.*;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "story")
    private String story;

    @Column(name = "description")
    private String description;

    private boolean completed = false;

    @OneToOne(fetch = FetchType.EAGER)
    private Project project;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    public Task() {
    }

    public Task(String story, String description, Project project, User user) {
        this.story = story;
        this.description = description;
        this.project = project;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
