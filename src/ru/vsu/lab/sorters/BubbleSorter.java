package ru.vsu.lab.sorters;

import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.PersonRepository;

import java.util.Comparator;

public class BubbleSorter implements ISorter {
    PersonRepository repository;
    Comparator comparator;

    public BubbleSorter(PersonRepository repository, Comparator comparator) {
        this.repository = repository;
        this.comparator = comparator;
    }

    public BubbleSorter() {
    }

    @Override
    public void sort() {
        boolean isSorted = false;
        IPerson buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < repository.getLength() - 1; i++) {
                if (comparator.compare(repository.getRepository()[i], repository.getRepository()[i + 1]) >= 1) {
                    isSorted = false;
                    buf = repository.getRepository()[i];
                    repository.getRepository()[i] = repository.getRepository()[i + 1];
                    repository.getRepository()[i + 1] = buf;

                }
            }
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

