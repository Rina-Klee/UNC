package ru.vsu.lab.repository;

import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.Person;
import ru.vsu.lab.inject.LabInject;
import ru.vsu.lab.sorters.ISorter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;


public class PersonRepository implements IPersonRepository {

    @LabInject
    private ISorter sorter;

    private IPerson[] personDynamicArray;
    /**
     * .
     * number of items in the database.
     */
    private int personLenght = 0;
    private Predicate<IPerson> condition;

    private static final Logger log = Logger.getLogger(PersonRepository.class.getName());

    public PersonRepository() {
        personDynamicArray = new IPerson[10];
    }

    /**
     * a method that retrieves an item from a database by index.
     *
     * @param index to be found.
     * @return if the object is present, it returns it.
     */
    public final Person getPerson(int index) {
        if (index < personLenght) {
            return (Person) personDynamicArray[index];
        } else {
            return null;
        }
    }

    private void increase() {
        IPerson[] newPersonDynamicArray = new IPerson[personDynamicArray.length + (personDynamicArray.length / 2)];
        System.arraycopy(personDynamicArray,
                0,
                newPersonDynamicArray,
                0,
                personDynamicArray.length);
        personDynamicArray = newPersonDynamicArray;
    }

    @Override
    public void add(IPerson person) {
        if (personDynamicArray.length == personLenght) {
            increase();
        }
        personDynamicArray[personLenght] = person;
        personLenght++;
        log.info("Person was added to repository.");
    }

    private void move(int index) {
        IPerson[] newPersonDynamicArray = new IPerson[personDynamicArray.length];
        System.arraycopy(personDynamicArray, index + 1, newPersonDynamicArray, index + 1, personDynamicArray.length - (index + 1));
        System.arraycopy(newPersonDynamicArray, index + 1, personDynamicArray, index, newPersonDynamicArray.length - (index + 1));
        personDynamicArray[personDynamicArray.length - 1] = null;
    }

    @Override
    public IPerson get(int index) {
        try {
            log.info("Getting Person by index " + index);
            return personDynamicArray[index];
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            log.warning("There are no Person by this index!");
        }
        return null;
    }

    @Override
    public IPerson delete(int index) {
        IPerson person = null;
        for (int i = 0; i < personLenght; i++) {
            if (i == index) {
                person = personDynamicArray[i];
                personDynamicArray[i] = null;
                break;
            }
        }
        move(index);
        personLenght--;
        log.info("Person by index " + index + " was delete.");
        return person;
    }

    @Override
    public IPerson set(int index, IPerson person) {
        IPerson object = null;
        for (int i = 0; i < personLenght; i++) {
            if (i == index) {
                object = personDynamicArray[i];
                personDynamicArray[i] = person;
                break;
            }
        }
        log.info("Person was set to index" + index);
        return object;
    }

    @Override
    public void add(int index, IPerson person) {
        if (index >= 0 && index < personLenght) {
            for (int i = 0; i < personLenght; i++) {
                if (i == index) {
                }
                personDynamicArray[i] = null;
                personDynamicArray[i] = person;
                break;
            }
        }
    }

    @Override
    public List<IPerson> toList() {
        trimToSize();
        log.info("Parsing repository to List");
        return Arrays.asList(personDynamicArray);
    }

    private void trimToSize() {
        IPerson[] localBank = new Person[personLenght];
        System.arraycopy(personDynamicArray, 0, localBank, 0, personLenght);
        personDynamicArray = localBank;

    }

    public int getLength() {
        return personLenght;
    }

    public IPerson[] getRepository() {
        return personDynamicArray;
    }

    @Override
    public void sortBy(Comparator<IPerson> comparator) {
        this.sorter.setRepository(this);
        this.sorter.setComparator(comparator);
        this.sorter.sort();
        log.info("Repository was sorted.");
    }

    @Override
    public IPersonRepository searchBy(Predicate<IPerson> condition) {
        PersonRepository repository = new PersonRepository();
        for (int i = 0; i < personLenght; i++) {
            if (condition.test(personDynamicArray[i])) {
                repository.add(personDynamicArray[i]);
            }
        }
        return repository;
    }

    @Override
    public String toString() {
        StringBuilder p = new StringBuilder();

        for (int i = 0; i < personLenght; i++) {
            p.append(personDynamicArray[i].toString()).append("\n");
        }
        return p.toString();

    }
}
