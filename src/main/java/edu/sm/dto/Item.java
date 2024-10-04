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


public class Item {
    private int itemKey;
    private int categoryId;
    private String item_Name;
    private int item_price;
    private Timestamp item_date;
    private int cnt;
    private String content;
    private String img1;
    private String img2;
    private String img3;
}