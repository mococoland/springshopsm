package sm.Manager;

import edu.sm.dto.Item;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.ItemService;

import java.sql.Timestamp;

public class ItemInsert {
    public static void main(String[] args) {
        ItemService productService = new ItemService();
        Item product = Item.builder()
                .categoryId(5)
                .item_Name("Tshrts")
                .item_price(23400)
                .item_date(new Timestamp(System.currentTimeMillis()))
                .cnt(4)
                .content("test")
                .img1("img")
                .build();

        try {
            productService.add(product);
        }catch (DuplicatedIdException e){
            System.out.println("이름이 중복 되어 입력이 안됩니다.");
        } catch (Exception e) {
            System.out.println("시스템 장애");
            e.printStackTrace();
        }
    }
}