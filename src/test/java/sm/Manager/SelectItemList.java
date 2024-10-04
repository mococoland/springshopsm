package sm.Manager;

import edu.sm.dto.Item;
import edu.sm.service.ItemService;

import java.util.List;
import java.util.Scanner;

public class SelectItemList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ItemService itemService = new ItemService();  // ItemService 초기화 추가

        System.out.print("\n조회할 아이템 이름을 입력하세요: ");
        String itemName = scanner.next();  // Get the item name from user input

        try {
            List<Item> itemList = itemService.selectItemListByName(itemName);  // 입력받은 아이템 이름을 전달
            if (itemList.isEmpty()) {
                System.out.println("아이템을 찾을 수 없습니다.");
            } else {
                System.out.println("아이템 목록:");
                for (Item item : itemList) {
                    System.out.println("아이템 이름: " + item.getItem_Name() + ", 가격: " + item.getItem_price());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();  // Scanner 자원 해제
        }
    }
}
