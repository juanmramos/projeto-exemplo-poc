package br.com.novaxs.crs.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ComponenteDTO extends GenericDTO {

    private String label;

    private String placeholder;

    private Boolean required;

    private Long minlength;

    private Long maxlength;

    private String messageRequired;

}
