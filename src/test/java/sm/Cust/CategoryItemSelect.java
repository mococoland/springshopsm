//Bum
package sm.Cust;

import edu.sm.dto.Item;
import edu.sm.service.ItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CategoryItemSelect {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");

        ItemService itemService = new ItemService();
        int categoryId = 6;

        try {
            List<Item> items = itemService.getItemsByCategory(categoryId); // 카테고리별 상품 조회

            // 상품 정보 출력
            for (Item item : items) {
                System.out.println("상품 번호: " + item.getItemKey() +
                        ", 상품 이름: " + item.getItem_Name() +
                        ", 가격: " + item.getItem_price() +
                        ", 재고 수량: " + item.getCnt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
