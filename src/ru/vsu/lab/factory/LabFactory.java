package ru.vsu.lab.factory;

import ru.vsu.lab.entities.Division;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.Person;
import ru.vsu.lab.exception.InjectorException;
import ru.vsu.lab.inject.Injector;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;
import ru.vsu.lab.repository.PersonRepository;
import ru.vsu.lab.repository.Repository;

public class LabFactory implements ILabFactory {
    @Override
    public IPerson createPerson() {

        return new Person();
    }

    @Override
    public IDivision createDivision() {
        return new Division();
    }

    @Override
    public <T> IRepository<T> createRepository(Class<T> clazz) {
        try {
            return new Injector().inject(new Repository<>());
        } catch (InjectorException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public IPersonRepository createPersonRepository() throws Exception {
        return (IPersonRepository) new Injector().inject(new PersonRepository());
    }
}
