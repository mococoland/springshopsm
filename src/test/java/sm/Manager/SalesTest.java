package sm.Manager;

import edu.sm.service.SalesService;

import java.util.List;

public class SalesTest {
    public static void main(String[] args) {
        SalesService salesService = new SalesService();

        try {
            // 월별 판매량 테스트
            List<Integer> monthlySales = salesService.getMonthlySales();
            System.out.println("월별 판매량:");
            for (Integer sales : monthlySales) {
                System.out.println(sales);
            }

            // 년별 판매량 테스트
            List<Integer> yearlySales = salesService.getYearlySales();
            System.out.println("년별 판매량:");
            for (Integer sales : yearlySales) {
                System.out.println(sales);
            }

            // 일별 판매량 테스트
            List<Integer> dailySales = salesService.getDailySales();
            System.out.println("일별 판매량:");
            for (Integer sales : dailySales) {
                System.out.println(sales);
            }

            // 성별 판매량 테스트
            List<Object[]> salesByGender = salesService.getSalesByGender();
            System.out.println("성별 판매량:");
            for (Object[] row : salesByGender) {
                System.out.println("판매량: " + row[0] + ", 성별: " + row[1]);
            }

            // 지역별 판매량 테스트
            List<Object[]> salesByLocation = salesService.getSalesByLocation();
            System.out.println("지역별 판매량:");
            for (Object[] row : salesByLocation) {
                System.out.println("판매량: " + row[0] + ", 지역코드: " + row[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
