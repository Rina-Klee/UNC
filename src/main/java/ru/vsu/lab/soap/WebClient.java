package ru.vsu.lab.soap;

import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.Person;
import ru.vsu.lab.service.IService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class WebClient {

    private static IService service;

    public static void start(String spec, String namespaceURL, String localPart) throws MalformedURLException {
        URL url = new URL(spec);

        QName qname = new QName(namespaceURL,localPart);

        service = Service.create(url, qname).getPort(IService.class);
    }

    public static long getCountUsersByAge(int age) {
        return service.getCountUsersByAge(age);
    }

    public static String getUserById(int id) {
        return service.getUserNameById(id);
    }
}
