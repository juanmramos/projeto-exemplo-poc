package br.com.novaxs.crs.model.dto;

import br.com.novaxs.crs.model.entity.ProviderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String taxPayerId;
    private LocalDateTime birthDate;
    private String statementDescriptor;
    private String mcc;
    private AddressDTO addressDTO = new AddressDTO();

    public ProviderDTO(ProviderEntity providerEntity) {
        this.id = providerEntity.getId();
        this.firstName = providerEntity.getFirstName();
        this.lastName = providerEntity.getLastName();
        this.email = providerEntity.getEmail();
        this.phoneNumber = providerEntity.getPhoneNumber();
        this.taxPayerId = providerEntity.getTaxPayerId();
        this.birthDate = providerEntity.getBirthDate();
        this.statementDescriptor = providerEntity.getStatementDescriptor();
        this.mcc = providerEntity.getMcc();
        this.addressDTO.setId(providerEntity.getAddressEntity().getId());
        this.addressDTO.setCity(providerEntity.getAddressEntity().getCity());
        this.addressDTO.setState(providerEntity.getAddressEntity().getState());
        this.addressDTO.setCountry_code(providerEntity.getAddressEntity().getCountry_code());
        this.addressDTO.setLine1(providerEntity.getAddressEntity().getLine1());
        this.addressDTO.setLine2(providerEntity.getAddressEntity().getLine2());
        this.addressDTO.setNeighborhood(providerEntity.getAddressEntity().getNeighborhood());
        this.addressDTO.setPostalCode(providerEntity.getAddressEntity().getPostalCode());
    }

}
