package ru.vsu.lab.entities;

import ru.vsu.lab.entities.enums.Gender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * a class that stores parameters about a user.
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements IPerson {

    /**
     * this variable stores the user id.
     */

    private int id;
    /**
     * this variable stores the first name  of the user.
     */
    private String firstName;

    /**
     * this variable stores the last name  of the user.
     */
    private String lastName;

    /**
     * this variable stores the date of birth.
     */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate birthdate;

    /**
     * this variable stores the gender.
     */
    private Gender gender;

    /**
     * this variable stores the division.
     */
    @XmlElement
    @XmlJavaTypeAdapter(Division.Adapter.class)
    private IDivision division;

    /**
     * this variable stores the salary.
     */
    private BigDecimal salary;

    /**
     * constructor for the Person class
     */
    public Person(int id,
                  String firstName,
                  Gender gender,
                  LocalDate birthdate,
                  IDivision division,
                  BigDecimal salary) {

        this.id = id;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.division = division;
        this.salary = salary;
    }

    public Person() {
    }

    public static class Adapter extends XmlAdapter<Person,IPerson> {
        public IPerson unmarshal(Person v) { return v; }
        public Person marshal(IPerson v) { return (Person) v; }
    }

    public static class DateAdapter extends XmlAdapter<String,LocalDate> {
        public LocalDate unmarshal(String v) { return LocalDate.parse(v); }
        public String marshal(LocalDate v) { return v.toString(); }
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return birthdate == person.birthdate
                && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(gender, person.gender);
    }

    /**
     * @return id.
     */
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return first name.
     */
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return last name.
     */
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public void setBirthdate(java.time.LocalDate Birthdate) {
    }

    @Override
    public Integer getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    /**
     * @return gender.
     */
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * @return division
     */
    public IDivision getDivision() {
        return division;
    }

    @Override
    public void setDivision(IDivision division) {
        this.division = division;
    }

    /**
     * @return salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /*public void setDivision(IDivision division);
    public void setSalary(BigDecimal salary);*/

    @Override
    public String toString() {
        return firstName + ", " +
                gender.toString().toLowerCase() + ", " +
                this.getAge() + ", " +
                birthdate + ";";


    }
}
