//주소 추가
package sm.Cust;

import edu.sm.dto.Address;
import edu.sm.service.AddressService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InsertAddress {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");


        AddressService addressService = new AddressService();
        Address address = Address.builder()
                .addressKey(12)
                .custKey(3)
                .zipnum("집")
                .addrD("가자")
                .build();

        try {
            addressService.add(address);
        } catch (Exception e) {
            System.out.println("시스템 장애");
            e.printStackTrace();
        }
    }
}
