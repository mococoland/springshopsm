package edu.sm.frame;

public class Sql {
    public static String selectCategory = "SELECT categoryId, categoryName, superCategory FROM category"; // 카테고리 나열
    public static String updateCategory = "UPDATE category SET categoryName = ?, superCategory = ? WHERE categoryId = ?"; // 카테고리 수정
    public static String deleteCategory = "DELETE FROM category WHERE categoryId = ?";
    public static String insertCategory = "INSERT INTO category (categoryId, superCategory,categoryName) VALUES (?, ?, ?)";

    public static String selectItem = "SELECT itemKey, item_name, item_price FROM item WHERE categoryId = ?"; // 상품 이름, 가격, 브랜드 불러오기
    public static String selectItemDetail = "SELECT item_name, item_price, item_date, cnt, content, img1, img2, img3 FROM item WHERE itemKey = ?"; // 상품 상세 페이지
    public static String insertItem = "INSERT INTO item (categoryId, item_name, item_price, item_date, cnt, content, img1, img2, img3) VALUES (?, ?, ?, SYSDATE(), ?, ?, ?, ?, ?)"; // 상품등록
    public static String selectItemList = "SELECT itemKey, item_name, item_price FROM item WHERE item_name LIKE ?"; // 상품검색
    public static String selectItemReview = "SELECT reviewKey, custKey, title, content, rate FROM review WHERE itemKey = ?"; // 상품 후기(아이템 키)

    public static String selectOrderList = "SELECT orderId, name, payId, itemCnt, totalPrice, rdate FROM orderList WHERE custKey = ?"; // 주문목록
    public static String insertReview = "INSERT INTO review VALUES(?,?,?,?,?,?,?,?,?,?)";; // 후기작성
    public static String updateReview = "UPDATE review SET title = ?, content = ?, rate = ? WHERE reviewKey = ?"; // 후기수정
    public static String deleteReview = "DELETE FROM review WHERE reviewKey = ?";; // 후기삭제


    public static String insertCart = "INSERT INTO cart(custKey, itemKey, itemCnt, price) Values (?,?,?,?)"; // 장바구니에 추가하기
    public static String deleteCart = "DELETE FROM cart WHERE cartKey = ?"; // 장바구니 상품 삭제

    public static String selectPay = "SELECT addressKey, zipnum, addrD FROM address WHERE custKey = ?"; // 결제 페이지 불러오기
    public static String selectSum = "SELECT SUM(price * itemCnt) AS totalPrice FROM cart WHERE custKey = ?"; // 총 구매금액
    public static String updatePay = "UPDATE pay SET payCom = ?, payNum = ? WHERE payId = ?";  // 결제사 입력
    public static String updatePayNum = "UPDATE pay SET payNum = ? WHERE payId = ?"; // 통장/카드번호 입력

    public static String insertAdd = "INSERT INTO address (addressKey, custKey, zipnum, addrD) VALUES (?,?,?,?)"; // 배송지 등록
    public static String updateAdd = "UPDATE address SET zipnum = ?, addrD = ? WHERE addressKey = ?"; // 우편번호 수정
    public static String deleteAdd = "DELETE FROM address WHERE addressKey = ?"; // 우편번호 삭제

    public static String selectBoard = "SELECT title, content, rdate FROM prod_board WHERE custKey = ?"; // 문의나열
    public static String selectFineBoard = "SELECT title, content FROM prod_board WHERE title LIKE ? OR content LIKE ?"; // 문의검색
    public static String insertBoard = "INSERT INTO prod_board (custKey, itemKey, type, title, content, img) VALUES (?,?,?,?,?,?)"; // 문의 작성하기
    public static String updateBoard = "UPDATE prod_board SET title = ?, content = ?, img = ? WHERE boardKey = ?"; // 문의수정
    public static String deleteBoard = "DELETE FROM prod_board WHERE boardKey = ?"; // 문의삭제
    public static String updateBoardAnswer = "UPDATE prod_board SET answer = ? WHERE boardKey = ?"; // 문의 답변 입력

    public static String reviewDetail = "SELECT title, content, rate, rdate FROM review WHERE reviewKey = ?"; // 리뷰 상세 페이지
    public static String reviewList = "SELECT reviewKey, title, rate, rdate FROM review"; // 리뷰 목록 나열

    public static String selectCustomer = "SELECT custKey, custName, gender, tel, email FROM customer\n"; // 모든 회원 조회
    public static String updateCustState = "UPDATE customer SET custState = ? WHERE custKey = ?";
    public static String updateRating = "UPDATE customer SET rating = ? WHERE custKey = ?";
    public static String updateMemberOut = "UPDATE customer SET memberOut = ? WHERE custKey = ?";

    public static String updateCustomer = "UPDATE customer SET tel=?, email=?, pwd=? where custKey=?;";


    public static String selectMonth = "SELECT SUM(orderdetail.cnt) AS sales, MONTH(orderList.rdate) AS month\n" + // 월별 판매량
            "FROM orderList\n" +
            "JOIN orderdetail ON orderList.orderId = orderdetail.orderId\n" +
            "GROUP BY MONTH(orderList.rdate)";

    public static String selectYear = "SELECT SUM(orderdetail.cnt) AS sales, YEAR(orderList.rdate) AS year\n" + // 년별 판매량
            "FROM orderList\n" +
            "JOIN orderdetail ON orderList.orderId = orderdetail.orderId\n" +
            "GROUP BY YEAR(orderList.rdate)";

    public static String selectDay = "SELECT SUM(orderdetail.cnt) AS sales, DAY(orderList.rdate) AS day\n" + // 일별 판매량
            "FROM orderList\n" +
            "JOIN orderdetail ON orderList.orderId = orderdetail.orderId\n" +
            "GROUP BY DAY(orderList.rdate)";

    public static String selectGender = "SELECT SUM(orderdetail.cnt) AS sales, customer.gender\n" + // 성별 판매량
            "FROM orderList\n" +
            "JOIN orderdetail ON orderList.orderId = orderdetail.orderId\n" +
            "JOIN customer ON orderList.custKey = customer.custKey\n" +
            "GROUP BY customer.gender;";

    public static String selectLoc = "SELECT SUM(orderdetail.cnt) AS sales, address.zipnum\n" + // 지역별 판매량
            "FROM orderList\n" +
            "JOIN orderdetail ON orderList.orderId = orderdetail.orderId\n" +
            "JOIN customer ON orderList.custKey = customer.custKey\n" +
            "JOIN address ON customer.custKey = address.custKey\n" +
            "GROUP BY address.zipnum;\n";
}