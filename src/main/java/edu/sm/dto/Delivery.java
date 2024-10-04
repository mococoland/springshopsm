package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Delivery{
    private int deliverId;
    private int orderId;
    private String name;
    private String tel;
    private String deliveryAdd;
}
