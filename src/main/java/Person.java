import org.joda.time.LocalDate;
import org.joda.time.Years;

/**
 * a class that stores parameters about a user.
 */
public class Person {

    /**
     * this variable stores the user id.
     */
    private  int id;

    /**
     * this variable stores the name surname and patronymic of the user.
     */
    private String fullName;

    /**
     * this variable stores the gender.
     */
    private String gender;

    /**
     * this variable stores the date of birth.
     */
    private LocalDate dateOfBirth;

    /**
     * constructor for the Person class
     */
    public Person( int id,String fullName,LocalDate dateOfBirth, String gender) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @return name surname and patronymic.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return gender.
     */
    public String getGender() {
        return gender;
    }
    /**
     *
     * @return dateOfBith
     */

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * the method calculates the ages of a person by the given field fields.
     */
    public int  getAge ()
    {
        LocalDate now = new LocalDate();
        LocalDate dateOfBirth = new LocalDate();
        dateOfBirth = getDateOfBirth();
        Years age = Years.yearsBetween(dateOfBirth, now);
        return age.getYears();
    }

}
