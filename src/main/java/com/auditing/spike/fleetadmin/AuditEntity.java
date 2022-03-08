package com.auditing.spike.fleetadmin;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class AuditEntity {

    private Long id;
    private String tech;
    private String previousTech;
    private String name;
    private String previousName;
    private int rev;
    private int revType;
}
