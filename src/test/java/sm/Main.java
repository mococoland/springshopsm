package sm;
import java.sql.SQLException;
import java.util.Scanner;

import edu.sm.dto.*;
import edu.sm.service.*;
import edu.sm.service.Prod_BoardService;
import edu.sm.dto.Review;
import edu.sm.service.ReviewService;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;


        while (choice != 0) {
            System.out.println("===== 메뉴 =====");
            System.out.println("1. 마이페이지");
            System.out.println("2. 모든 카테고리 조회");
            System.out.println("3. 문의 확인");
            System.out.println("4. 리뷰 확인");
            System.out.println("0. 종료");
            System.out.print("원하는 작업을 선택하세요: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("마이페이지로 이동합니다.");
                    System.out.println("개인정보를 수정하려면 1번, 배송지를 조회하려면 2번, 종료하려면 0번을 입력.");
                        int MyChoice = scanner.nextInt();
                        switch (MyChoice) {
                            case 1:
                                System.out.println("개인정보 수정");
                                CustomerService customerService = new CustomerService();
                                Customer customer = Customer.builder()
                                        .tel("010-2211-3322")
                                        .email("smsmu1@sunmoon.ac.kr")
                                        .pwd("pwd0022")
                                        .custKey(1)
                                        .build();
                                try {
                                    customerService.modify(customer);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                System.out.println("배송지 조회");
                                try {
                                    PayService payService = new PayService();  // SQLException 처리
                                    int custKey = 2;

                                    List<Address> address = payService.getselectPay(custKey);

                                    for (Address add : address) {
                                        System.out.println("Address Key: " + add.getAddressKey() +
                                                ", 집 주소: " + add.getZipnum() +
                                                ", 지번: " + add.getAddrD());
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();  // 예외 발생 시 처리
                                } catch (Exception e) {
                                    e.printStackTrace();  // 다른 예외 처리
                                }
                                System.out.println("배송지 수정은 1번, 배송지 입력은 2번, 메인페이지는 9번을 입력");
                                int Add_Choice = scanner.nextInt();
                                switch (Add_Choice) {
                                    case 1:
                                        System.out.println("배송지 수정");
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
                                        break;
                                    case 2:
                                        System.out.println("배송지 입력");
                                        AddressService addressServiceIn = new AddressService();
                                        Address addressIn = Address.builder()
                                                .addressKey(13)
                                                .custKey(2)
                                                .zipnum("1215")
                                                .addrD("네이처")
                                                .build();

                                        try {
                                            addressServiceIn.add(addressIn);
                                        } catch (Exception e) {
                                            System.out.println("시스템 장애");
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 9:
                                        System.out.println("메인 페이지로 돌아갑니다.");
                                        break;
                                    default:
                                        System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                                        break;
                                }
                                break;
                        }
                    break;
                case 2:
                    // 모든 카테고리 조회
                    CategoryService categoryService = new CategoryService();

                    try {
                        List<Category> categories = categoryService.get(); // 모든 카테고리 조회
                        System.out.println("모든 카테고리 목록:");
                        for (Category category : categories) {
                            System.out.println("카테고리 ID: " + category.getCategoryId() +
                                    ", 카테고리 이름: " + category.getCategoryName() +
                                    ", 상위 카테고리 ID: " + category.getSuperCategory());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("카테고리별 아이템 조회는 1번, 아이템을 조회하려면 2번, 아이템의 상세정보는 3번, 종료하려면 0번, 처음 페이지로 돌아가려면 9번을 입력.");
                    int CategoryChoice = scanner.nextInt();

                    switch (CategoryChoice) {
                        case 1:
                            System.out.println("카테고리별 아이템 조회");
                            ItemService itemServiceCate = new ItemService();
                            int categoryId = 5;

                            try {
                                List<Item> items = itemServiceCate.getItemsByCategory(categoryId); // 카테고리별 상품 조회

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
                            break;
                        case 2:
                            System.out.println("아이템 조회");
                            ItemService itemServices = new ItemService();  // ItemService 초기화 추가

                            System.out.print("\n조회할 아이템 이름을 입력하세요: ");
                            String itemName = scanner.next();  // Get the item name from user input

                            try {
                                List<Item> itemList = itemServices.selectItemListByName(itemName);  // 입력받은 아이템 이름을 전달
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
                            break;
                        case 3:
                            System.out.println("아이템 상세정보");
                            System.out.print("\n조회할 아이템의 itemKey를 입력하세요: ");
                            int itemKeyDetail = scanner.nextInt();
                            ItemService itemServiceDe = new ItemService();
                            try {
                                Item itemDetail = itemServiceDe.getItemDetail(itemKeyDetail);
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
                            continue;
                        case 9:
                            System.out.println("메인 페이지로 돌아갑니다.");
                            break;
                        default:
                            System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("문의 확인 페이지.");
                    System.out.println("문의 작성 1번");
                    int BoardChoice = scanner.nextInt();
                    switch (BoardChoice) {
                        case 1:
                            System.out.println("문의 작성");
                            Prod_BoardService prodBoardService = new Prod_BoardService();
                            // 사용자 입력 받기
                            System.out.print("고객 키(custKey)를 입력하세요: ");
                            int custKey = Integer.parseInt(scanner.nextLine());

                            System.out.print("아이템 키(itemKey)를 입력하세요: ");
                            int itemKey = Integer.parseInt(scanner.nextLine());

                            System.out.print("문의 유형(type)을 입력하세요: ");
                            String type = scanner.nextLine();

                            System.out.print("제목(title)을 입력하세요: ");
                            String title = scanner.nextLine();

                            System.out.print("내용(content)을 입력하세요: ");
                            String content = scanner.nextLine();

                            System.out.print("이미지(img) 경로를 입력하세요: ");
                            String img = scanner.nextLine();

                            // Prod_Board 객체 생성
                            Prod_Board prodBoard = Prod_Board.builder()
                                    .custKey(custKey)
                                    .itemKey(itemKey)
                                    .type(type)
                                    .title(title)
                                    .content(content)
                                    .img(img)
                                    .build();

                            // 문의 추가
                            try {
                                prodBoardService.add(prodBoard);
                                System.out.println("문의가 성공적으로 추가되었습니다.");
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("문의 추가 중 오류가 발생했습니다.");
                            } finally {
                                scanner.close();
                            }
                            break;
                    }
                    break;
                case 4:
                    System.out.println("리뷰 페이지");
                    System.out.println("리뷰 조회는 1번, 리뷰 상세 조회는 2번");
                    int reviewChoice = scanner.nextInt();
                    switch (reviewChoice) {
                        case 1:
                            System.out.println("리뷰 조회");
                            ReviewService reviewService = new ReviewService();
                            List<Review> reviews = null;

                            try {
                                // 전체 리뷰 가져오기
                                reviews = reviewService.get(); // get() 메서드 호출
                                System.out.println(reviews); // 리뷰 출력
                            } catch (Exception e) {
                                e.printStackTrace(); // 예외 처리
                            }
                            break;
                        case 2:
                            System.out.println("리뷰 상세 조회");
                            ReviewService service = new ReviewService();

                            try {
                                System.out.print("리뷰 키를 입력하세요: ");
                                String reviewKey = scanner.nextLine();

                                // 리뷰 상세 정보 조회
                                Review review = service.getDetail(reviewKey);

                                if (review != null) {
                                    System.out.println("리뷰 제목: " + review.getTitle());
                                    System.out.println("리뷰 내용: " + review.getContent());
                                    System.out.println("리뷰 평점: " + review.getRate());
                                    System.out.println("작성일: " + review.getRdate());
                                } else {
                                    System.out.println("해당 키의 리뷰를 찾을 수 없습니다.");
                                }
                            } catch (Exception e) {
                                System.out.println("리뷰 조회 중 오류 발생: " + e.getMessage());
                            }
                    }
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                    break;
            }
        }

        scanner.close();
    }
}
