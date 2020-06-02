package ru.vsu.lab.entities;

public class Division implements IDivision {

    private String name;

    public Division(String name) {
        this.name = name;
    }

    public Division() {
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
    }
}
