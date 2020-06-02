package test.java.java;

import org.junit.Test;
import ru.vsu.lab.ReadCSVWithScanner;
import ru.vsu.lab.comparators.AgeComparator;
import ru.vsu.lab.entities.Division;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.Person;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.factory.LabFactory;
import ru.vsu.lab.repository.IPersonRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PersonTest {
    IDivision division = new Division("Маркетинг");
    Person pers1 = new Person(1, "Ирина", Gender.FEMALE, LocalDate.of(1998, 4, 15), division, new BigDecimal(26000)),
            pers2 = new Person(2, "Лина", Gender.FEMALE, LocalDate.of(1996, 12, 24), division, new BigDecimal(37000)),
            pers3 = new Person(3, "Тимур", Gender.MALE, LocalDate.of(1999, 7, 1), division, new BigDecimal(23000)),
            pers4 = new Person(4, "Никита", Gender.MALE, LocalDate.of(1993, 2, 17), division, new BigDecimal(49000)),
            pers5 = new Person(5, "Арина", Gender.FEMALE, LocalDate.of(2000, 12, 24), division, new BigDecimal(34000));

    private LabFactory factory = new LabFactory();
    private IPersonRepository repository = factory.createPersonRepository();

    public PersonTest() throws Exception {
    }


    @Test
    public void csvTest() throws Exception {
        ReadCSVWithScanner read = new ReadCSVWithScanner(repository, "src/main/resources/persons.csv");
        System.out.println(read.getRepository().toString());
    }

    @Test
    public void sortTest() {
        repository.add(pers1);
        repository.add(pers2);
        repository.add(pers3);
        repository.add(pers4);
        repository.add(pers5);
        System.out.println(repository.toString() + "\n");
        repository.sortBy(new AgeComparator());
        System.out.println(repository.toString());
    }
}

