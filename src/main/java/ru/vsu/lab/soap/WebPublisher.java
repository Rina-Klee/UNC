package ru.vsu.lab.soap;

import ru.vsu.lab.service.impl.ServiceImpl;

import javax.xml.ws.Endpoint;
import java.io.IOException;

public class WebPublisher {
    public static void start(String address) throws IOException {
        Endpoint.publish(address, new ServiceImpl());
    }
}
