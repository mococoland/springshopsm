package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class OrderDetail {
    private int detailId;
    private int orderId;
    private int itemKey;
    private int price;
    private int cnt;

}