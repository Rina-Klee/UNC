package ru.vsu.lab.jaxb;

import ru.vsu.lab.entities.Person;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;
import ru.vsu.lab.repository.PersonRepository;
import ru.vsu.lab.repository.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Класс, экспортирующий xml в PersonRepository.
 */
public class XMLToPersonRepository {

    /**
     * Основной метод для экспортирования.
     * @param xml_path путь до xml файла.
     */
    public static IRepository toPersonRepository (String xml_path) {
        try {
            JAXBContext context = JAXBContext.newInstance(Repository.class, Person.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (IRepository) unmarshaller.unmarshal(new FileReader(xml_path));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
