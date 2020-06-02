package ru.vsu.lab.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name = "division")
@XmlAccessorType(XmlAccessType.FIELD)
public class Division implements IDivision {

    private String name;

    public Division(String name) {
        this.name = name;
    }

    public Division() {
    }

    public static class Adapter extends XmlAdapter<Division, IDivision> {
        public IDivision unmarshal(Division v) { return v; }
        public Division marshal(IDivision v) { return (Division) v; }
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
