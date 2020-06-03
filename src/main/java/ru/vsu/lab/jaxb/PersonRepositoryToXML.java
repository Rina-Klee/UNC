package ru.vsu.lab.jaxb;

import ru.vsu.lab.entities.Person;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;
import ru.vsu.lab.repository.PersonRepository;
import ru.vsu.lab.repository.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Класс, экспортирующий IPersonRepository в XML файл.
 */
public class PersonRepositoryToXML {

    /**
     * Основной метод для экспортирования.
     * @param repository репозиторий.
     * @param xml_path путь xml файла.
     */
    public static void toXML(IRepository repository, String xml_path) {
        try {
            JAXBContext context = JAXBContext.newInstance(Repository.class, Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            /* Вывод XML файла. */
            //marshaller.marshal(repository, System.out);

            File xml = new File(xml_path);
            marshaller.marshal(repository, xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
