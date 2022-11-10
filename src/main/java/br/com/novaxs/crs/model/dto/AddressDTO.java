package br.com.novaxs.crs.model.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private String postalCode;
    private String line1;
    private String line2;
    private String neighborhood;
    private String city;
    private String state;
    private String country_code;

}
