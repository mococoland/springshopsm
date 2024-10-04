package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cart {
    private int cartKey; // cart 기본키
    private int custKey; //Customer 에서 참조되는 외래키
    private int itemKey;// Item 에서 참조되는 왜래키
    private int itemCnt; // 상품 수량
    private int price; // 상품 가격

}