//Bum
package sm.Cust;

import edu.sm.service.AddressService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeleteAddress {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        AddressService addressService = new AddressService();

        int ine = 13;

        try {
            addressService.remove(String.valueOf(ine));
            System.out.println("주소가 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}