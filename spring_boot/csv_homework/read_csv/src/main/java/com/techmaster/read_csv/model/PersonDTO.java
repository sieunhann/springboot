package com.techmaster.read_csv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private int id;
    private String fullName;
    private String job;
    private String city;
}
