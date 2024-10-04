package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder



public class Customer {
    private int custKey;
    private String custName;
    private String gender;
    private String tel;
    private String email;
    private String pwd;
    private String junum;
    private String memberOut;
    private String rating;
    private String custState;
}