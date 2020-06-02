package ru.vsu.lab.comparators;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

public class AgeComparator implements Comparator<IPerson> {
    public int compare(IPerson p1, IPerson p2) {
        if (p1.getAge() > p2.getAge())
            return 1;
        else if (p1.getAge() < p2.getAge())
            return -1;
        else
            return 0;
    }
}