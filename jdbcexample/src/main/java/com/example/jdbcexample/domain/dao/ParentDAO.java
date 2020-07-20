package com.example.jdbcexample.domain.dao;

import com.example.jdbcexample.domain.enums.ParentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;
import java.util.EnumMap;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ParentDAO extends PersonDAO {

    private ParentType parentType;
    private String parentInfo;
    private String address;
    private String phonenumber;
    private String familyId;
}
