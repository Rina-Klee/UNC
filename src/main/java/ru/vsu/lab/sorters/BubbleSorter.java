package ru.vsu.lab.sorters;

import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.PersonRepository;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Comparator;

@XmlRootElement
public class BubbleSorter implements ISorter {
    PersonRepository repository;
    Comparator comparator;

    public BubbleSorter(PersonRepository repository, Comparator comparator) {
        this.repository = repository;
        this.comparator = comparator;
    }

    public BubbleSorter() {
    }

    public static class Adapter extends XmlAdapter<BubbleSorter,ISorter> {
        public ISorter unmarshal(BubbleSorter s) { return s; }
        public BubbleSorter marshal(ISorter s) { return (BubbleSorter) s; }
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

