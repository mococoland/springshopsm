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
public class Review {
    private int reviewKey;
    private int custKey;
    private int itemKey;
    private int managerKey;
    private String title;
    private Timestamp rdate;
    private String content;
    private String img;
    private int rate;
    private String answer;
}
