package sm.Cust;

import edu.sm.dto.Address;
import edu.sm.service.AddressService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UpdateAddress {
    public static void main(String[] args) {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");


        AddressService addressService = new AddressService();

        // 업데이트할 주소 정보를 가진 Address 객체 생성
        Address address = Address.builder()
                .addressKey(11) // 기존 주소 키
                .custKey(101)  // 고객 키
                .zipnum("12345") // 수정할 우편번호
                .addrD("선문대") // 수정할 주소
                .build();

        try {
            addressService.modify(address); // 주소 업데이트
            System.out.println("주소가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
    }
}
