package com.example.jdbcexample.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder(toBuilder = true)
@ApiObject(name = "Report Template DTO", description = "Report template specific information", visibility = ApiVisibility.PUBLIC, stage = ApiStage.GA, group = "Reports")
public class ReportTemplateDTO extends AbstractDTO {

    @ApiObjectField(description = "Report template description")
    private String description;

    @ApiObjectField(description = "Report template name")
    private String templateName;

    @ApiObjectField(description = "Report template filename")
    private String filename;

    @ApiObjectField(description = "Report template file")
    private byte[] template;

}
