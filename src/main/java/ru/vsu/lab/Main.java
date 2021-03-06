package ru.vsu.lab;

import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.factory.LabFactory;
import ru.vsu.lab.jaxb.PersonRepositoryToXML;
import ru.vsu.lab.jaxb.XMLToPersonRepository;
import ru.vsu.lab.repository.PersonRepository;
import ru.vsu.lab.soap.WebClient;
import ru.vsu.lab.soap.WebPublisher;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) throws IOException {

        /* Раскомментируйте нужную строку. */

        // Веб-сервер с двумя запросами.
        //soap();

        // Экспорт репозитория в XML и обратно.
        //jaxbTask();

        // Задание на потоки.
        //streamsTask();
    }

    private static void soap() throws IOException {
        WebPublisher.start("http://localhost:8080/wss/persons");
        WebClient.start("http://localhost:8080/wss/persons?wsdl",
                "http://impl.service.lab.vsu.ru/",
                "ServiceImplService");

        System.out.println("Количество людей заданного возраста (21): " + WebClient.getCountUsersByAge(21));
        System.out.println("Имя человека с заданным номером (4): " + WebClient.getUserById(4));
    }

    private static void jaxbTask() {
        try {
            LabFactory factory = new LabFactory();
            ReadCSVWithScanner loader = new ReadCSVWithScanner(factory.createRepository(IPerson.class), "src/main/resources/persons.csv");

            PersonRepositoryToXML.toXML(loader.getRepository(), "src/main/resources/repository.xml");

            // Вывод в консоль всех людей из XML файла
            for (Object person : Objects.requireNonNull(XMLToPersonRepository.toPersonRepository("src/main/resources/repository.xml")).toList()) {
                System.out.println(person.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void streamsTask() {
        try {
            LabFactory factory = new LabFactory();
            ReadCSVWithScanner read = new ReadCSVWithScanner(factory.createPersonRepository(), "src/main/resources/persons.csv");
            PersonRepository repository = (PersonRepository) read.getRepository();
            System.out.println("1) Список людей, сгруппированный по отделам и зарплатам.");
            System.out.println(repository.toList().stream().collect(Collectors.groupingBy(p -> p.getDivision().getName(), Collectors.summingDouble(p -> p.getSalary().doubleValue()))));
            System.out.println("\n2) Список людей с буквой 'а' в имени, старше 30 и заработной платой меньше 5000.");
            repository.toList().stream().filter(p -> p.getFirstName().toLowerCase().contains("a")).filter(p -> p.getAge() > 30).filter(p -> p.getSalary().intValue() < 5000).forEach(System.out::println);
            System.out.println("\n3) Список людей с двумя буквами 'а' в имени подряд.");
            repository.toList().stream().filter(p -> p.getFirstName().toLowerCase().contains("aa")).forEach(System.out::println);
            System.out.println("\n4) Количество людей по датам рождения.");
            System.out.println(repository.toList().stream().collect(Collectors.groupingBy(p -> p.getBirthdate().getYear(), Collectors.counting())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




