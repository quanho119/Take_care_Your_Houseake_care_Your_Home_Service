package com.example.webisite.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    @NotEmpty
    private Long id;

    @Column(name = "service_name")
    @NotEmpty
    private String name;

    public @NotEmpty Long getId() {
        return id;
    }

    public void setId(@NotEmpty Long id) {
        this.id = id;
    }

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public @NotEmpty String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty String description) {
        this.description = description;
    }

    @Column(name = "description")
    @NotEmpty
    private String description;
}
