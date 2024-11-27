package com.escass.testpersonals.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"ssnBirth", "ssnKey"})
public class PersonalEntity {
    private String name;
    private String gender;
    private String ssnBirth;
    private String ssnKey;
    private String region;
}
