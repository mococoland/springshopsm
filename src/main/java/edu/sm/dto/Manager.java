package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Manager {
    private int managerKey;
    private String managerId;
    private String managerPwd;
    private String name;
    private int op;
}