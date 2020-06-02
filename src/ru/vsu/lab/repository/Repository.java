package ru.vsu.lab.repository;

import ru.vsu.lab.inject.LabInject;
import ru.vsu.lab.sorters.ISorter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class Repository<T> implements IRepository<T> {
    @LabInject
    private ISorter sorter;

    private static final Logger log = Logger.getLogger(Repository.class.getName());

    private Object[] dynamicArray;
    /**
     * .
     * number of items in the database.
     */
    private int personLenght = 0;

    public Repository() {
        dynamicArray = new Object[10];
    }

    private void increase() {
        Object[] newPersonDynamicArray = new Object[dynamicArray.length + (dynamicArray.length / 2)];
        System.arraycopy(dynamicArray,
                0,
                newPersonDynamicArray,
                0,
                dynamicArray.length);
        for (int i = dynamicArray.length; i < newPersonDynamicArray.length; i++) {
            newPersonDynamicArray[i] = null;
        }

        dynamicArray = newPersonDynamicArray;
    }

    private void move(int index, boolean isShiftToRight) {
        Object[] newPersonDynamicArray = new Object[dynamicArray.length];
        if (!isShiftToRight) {
            System.arraycopy(dynamicArray, index + 1, newPersonDynamicArray, index + 1, dynamicArray.length - (index + 1));
            System.arraycopy(newPersonDynamicArray, index + 1, dynamicArray, index, newPersonDynamicArray.length - (index + 1));
            dynamicArray[dynamicArray.length - 1] = null;
        } else {
            System.arraycopy(dynamicArray, index, newPersonDynamicArray, index, dynamicArray.length - (index + 1));
            System.arraycopy(newPersonDynamicArray, index, dynamicArray, index + 1, newPersonDynamicArray.length - (index + 1));
        }

    }

    /**
     * removing an element from an array by index.
     *
     * @param index
     */
    public void remove(int index) {
        for (int i = 0; i < personLenght; i++) {
            if (i == index) {
                dynamicArray[i] = null;
                break;
            }
        }
        move(index, false);
        personLenght--;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder("");
        for (int i = 0; i < personLenght; i++) {
            buffer.append(dynamicArray[i].toString());
        }
        return buffer.toString();
    }

    @Override
    public void add(Object person) {
        if (dynamicArray.length == personLenght) {
            increase();
        }
        dynamicArray[personLenght] = person;
        personLenght++;
    }

    @Override
    public T get(int index) {
        for (int i = 0; i < personLenght; i++) {
            if (i == index) {
                log.info("Getting element with index " + index);
                return (T) dynamicArray[i];
            }
        }
        return null;
    }

    @Override
    public T delete(int index) {
        Object o = null;
        for (int i = 0; i < personLenght; i++) {
            if (i == index) {
                o = dynamicArray[i];
                dynamicArray[i] = null;
                break;
            }
        }
        move(index, false);
        personLenght--;
        log.info("Element by index " + index + " was delete.");
        return (T) o;
    }

    @Override
    public T set(int index, Object person) {
        Object o = null;
        for (int i = 0; i < personLenght; i++) {
            if (i == index) {
                o = dynamicArray[i];
                dynamicArray[i] = person;
                break;
            }
        }
        log.info("Element was set to index" + index);
        return (T) o;
    }

    @Override
    public void add(int index, Object person) {
        if (dynamicArray.length == personLenght) {
            increase();
        }
        move(index, true);
        dynamicArray[personLenght] = person;
        personLenght++;
    }

    @Override
    public List<T> toList() {
        this.trimToSize();
        log.info("Parsing repository to List");
        return (List<T>) Arrays.asList(dynamicArray);
    }

    private void trimToSize() {
        Object[] localBank = new Object[personLenght];
        System.arraycopy(dynamicArray, 0, localBank, 0, personLenght);
        dynamicArray = localBank;

    }

    public int getLength() {
        return personLenght;
    }

    public Object[] getRepository() {
        return this.dynamicArray;
    }

    @Override
    public void sortBy(Comparator comparator) {
        // If sort methods are generics uncomment
        /*
        this.sorter.setRepository(this);
        this.sorter.setComparator(comparator);
        this.sorter.sort();
         */
    }

    @Override
    public IRepository searchBy(Predicate condition) {
        return null;
    }
}




