package com.example.jdbcexample.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@ApiObject(name = "Person DTO", description = "Information about person (teacher, pupil, parent)", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Person")
public class PersonDTO extends AbstractDTO {

    @NotBlank
    @ApiObjectField(description = "Person's firstname")
    private String firstname;

    @NotBlank
    @ApiObjectField(description = "Person's lastname")
    private String lastname;

    @NotBlank
    @Email
    @ApiObjectField(description = "Persons email")
    private String email;

    @NotBlank
    @ApiObjectField(description = "Person's gender")
    private String gender;

    @NotBlank
    @Past
    @ApiObjectField(description = "Person's birthdate")
    private LocalDate birthdate;
}

