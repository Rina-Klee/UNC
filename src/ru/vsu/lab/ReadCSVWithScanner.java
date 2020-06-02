package ru.vsu.lab;

import ru.vsu.lab.entities.Division;
import ru.vsu.lab.entities.Person;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.PersonRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

public class ReadCSVWithScanner {
    private IPersonRepository repository;
    private String path;
    private static final Logger log = Logger.getLogger(ReadCSVWithScanner.class.getName());

    public ReadCSVWithScanner(IPersonRepository repository, String path) throws IOException {
        this.repository = repository;
        this.path = path;
        load((PersonRepository) repository);
    }

    public IPersonRepository getRepository() {
        return repository;
    }

    HashMap<String, Division> divMap = new HashMap<>();

    private boolean hasDivision(String name) {
        return divMap.containsKey(name);
    }

    public void load(PersonRepository repository) throws IOException {

        // открываем файл
        BufferedReader reader = new BufferedReader(new FileReader(
                path));

        // считываем построчно
        String line = null;
        Scanner scanner = null;
        int index = 0;

        while ((line = reader.readLine()) != null) {

            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            int id = Integer.parseInt(scanner.next());
            String firstName = scanner.next();
            Gender gender = Gender.valueOf(scanner.next().toUpperCase());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String birth = scanner.next();
            Division division = null;
            String divisionName = scanner.next();
            if (!hasDivision(divisionName)) {
                divMap.put(divisionName, new Division(divisionName));
            }
            division = divMap.get(divisionName);

            BigDecimal salary = BigDecimal.valueOf(Double.parseDouble(scanner.next()));
            Person pers = new Person(id, firstName, gender, LocalDate.parse(birth, formatter), division, salary);
            repository.add(pers);
        }

        //закрываем наш ридер
        reader.close();
        log.info("Repository was successfully loaded from file: " + path);
    }

}