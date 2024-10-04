package edu.sm.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Pay {
    private int payId;
    private int totalPrice;
    private String payCom;
    private int payNum;

}
