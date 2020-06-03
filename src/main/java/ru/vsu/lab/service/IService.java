package ru.vsu.lab.service;

import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IService {

    @WebMethod
    String getUserNameById(int id);

    @WebMethod
    long getCountUsersByAge(int age);
}
