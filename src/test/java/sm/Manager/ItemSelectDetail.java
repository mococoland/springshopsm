package sm.Manager;

import edu.sm.dto.Item;
import edu.sm.service.ItemService;

import java.util.Scanner;

public class ItemSelectDetail {
    public static void main(String[] args) {
        ItemService itemService = new ItemService();  // ItemService 객체 생성
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n조회할 아이템의 itemKey를 입력하세요: ");
        int itemKeyDetail = scanner.nextInt();
        try {
            Item itemDetail = itemService.getItemDetail(itemKeyDetail);
            if (itemDetail != null) {
                System.out.println("아이템 상세 정보:");
                System.out.println("이름: " + itemDetail.getItem_Name());
                System.out.println("가격: " + itemDetail.getItem_price());
                System.out.println("수량: " + itemDetail.getCnt());
                System.out.println("설명: " + itemDetail.getContent());
            } else {
                System.out.println("해당 아이템을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
