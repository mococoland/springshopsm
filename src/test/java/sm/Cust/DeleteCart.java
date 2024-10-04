//장바구니 삭제
package sm.Cust;

import edu.sm.service.CartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeleteCart {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        CartService cartService = new CartService();

        int cartId = 1;

        try{
            boolean isDeleted = cartService.remove(cartId);
            if(isDeleted){
                System.out.println("Cart has been deleted.");
            } else {
                System.out.println("Cart has not been deleted.");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
