package edu.sm.app;

import edu.sm.dto.Address;
import edu.sm.frame.MService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");

        MService<String, Address> service = service = null;
        service = (MService<String, Address>) factory.getBean("AddressService");
        service.remove("1");
    }
}
