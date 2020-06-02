package ru.vsu.lab.sorters;

import ru.vsu.lab.repository.PersonRepository;

import java.util.Comparator;

public interface ISorter {

    void sort();

    void setRepository(PersonRepository repository);

    void setComparator(Comparator comparator);
}


