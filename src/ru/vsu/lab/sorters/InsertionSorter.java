package ru.vsu.lab.sorters;

import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.PersonRepository;
import ru.vsu.lab.repository.Repository;

import java.util.Comparator;

public class InsertionSorter implements ISorter {
    PersonRepository repository;
    Comparator comparator;

    public void sort(PersonRepository repository, Comparator comparator) {
        this.repository = repository;
        this.comparator = comparator;
    }

    public InsertionSorter() {
    }

    @Override
    public void sort() {
        for (int i = 1; i < repository.getLength(); i++) {
            IPerson current = repository.getRepository()[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(current, repository.getRepository()[j]) < 0) {
                repository.getRepository()[j + 1] = repository.getRepository()[j];
                j--;
            }
            repository.getRepository()[j + 1] = current;
        }
    }

    @Override
    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }
}


