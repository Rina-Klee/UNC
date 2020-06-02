package ru.vsu.lab.repository;

import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.Person;
import ru.vsu.lab.inject.LabInject;
import ru.vsu.lab.sorters.BubbleSorter;
import ru.vsu.lab.sorters.ISorter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


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
    }

    private void move(int index) {
        IPerson[] newPersonDynamicArray = new IPerson[personDynamicArray.length];
        System.arraycopy(personDynamicArray, index + 1, newPersonDynamicArray, index + 1, personDynamicArray.length - (index + 1));
        System.arraycopy(newPersonDynamicArray, index + 1, personDynamicArray, index, newPersonDynamicArray.length - (index + 1));
        personDynamicArray[personDynamicArray.length - 1] = null;
    }

    @Override
    public IPerson get(int index) {
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
    }

    @Override
    public IPersonRepository searchBy(Predicate<IPerson> condition) {
        PersonRepository repository = new PersonRepository();
        for (int i = 0; i < personLenght; i++) {
            if (condition.test(personDynamicArray[i])) {
                repository.add(personDynamicArray[i]);
            }
        }
        return (IPersonRepository) repository;
    }

    @Override
    public String toString() {
        String p = "";

        for (int i = 0; i < personLenght; i++) {
            p += personDynamicArray[i].toString() + "\n";
        }
        return p;

    }


}
