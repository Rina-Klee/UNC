package ru.vsu.lab.service.impl;

import ru.vsu.lab.ReadCSVWithScanner;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.Person;
import ru.vsu.lab.factory.LabFactory;
import ru.vsu.lab.repository.IRepository;
import ru.vsu.lab.service.IService;

import javax.jws.WebService;
import java.io.IOException;

@WebService(endpointInterface = "ru.vsu.lab.service.IService")
public class ServiceImpl implements IService {

    private IRepository<IPerson> repository;

    public ServiceImpl() throws IOException {
        LabFactory factory = new LabFactory();
        repository = factory.createRepository(IPerson.class);
        ReadCSVWithScanner loader = new ReadCSVWithScanner(repository, "src/main/resources/persons.csv");
        this.repository = loader.getRepository();
    }

    @Override
    public String getUserNameById(int id) {
        return repository.get(id).getFirstName();
    }

    @Override
    public long getCountUsersByAge(int age) {
        repository.toList();
        return repository.toList().stream().filter(x -> x.getAge() == age).count();
    }
}
