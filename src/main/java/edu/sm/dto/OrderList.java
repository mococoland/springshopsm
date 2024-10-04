package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder



public class OrderList {
    private int orderId;
    private int custKey;
    private int payId;
    private int totalPrice;
    private int itemCnt;
    private Timestamp rdate;
    private String img;
    private String name;

}