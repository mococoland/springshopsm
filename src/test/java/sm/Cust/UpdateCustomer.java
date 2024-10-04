package sm.Cust;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UpdateCustomer {
    public static void main(String[] args) {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");


        CustomerService customerService = new CustomerService();
        Customer customer = Customer.builder()
                .tel("010-1111-1111")
                .email("이메일변경")
                .pwd("윤창정")
                .custKey(1)
                .build();

        try {
            customerService.modify(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
