package ru.vsu.lab.comparators;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

public class LastNameComparator implements Comparator<IPerson> {

    @Override
    public int compare(IPerson p1, IPerson p2) {
        return p1.getLastName().compareTo(p2.getLastName());
    }
}


