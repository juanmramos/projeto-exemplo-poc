package br.com.novaxs.crs;

import br.com.novaxs.crs.model.dto.AddressDTO;
import br.com.novaxs.crs.model.dto.ProviderDTO;
import br.com.novaxs.crs.model.dto.UserDTO;
import br.com.novaxs.crs.model.entity.AddressEntity;
import br.com.novaxs.crs.model.entity.LoginForm;
import br.com.novaxs.crs.model.entity.ProviderEntity;
import br.com.novaxs.crs.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;

public abstract class BaseTest {

    protected UserDTO mockUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Novo Teste");
        userDTO.setEmail("teste@crs.com.br");
        userDTO.setPasswordUser("123456");
        userDTO.setPhone("2223333");
        return userDTO;
    }

    protected UserEntity mockUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Novo Teste");
        userEntity.setEmail("teste@crs.com.br");
        userEntity.setPasswordUser("123456");
        userEntity.setPhone("2223333");
        return userEntity;
    }

    protected Page<UserDTO> mockPageUserDTO() {
        Page<UserDTO> userDTOS = new PageImpl<>(Arrays.asList(mockUserDTO()), Pageable.unpaged(), 1);
        return userDTOS;
    }

    protected Page<UserEntity> mockPageUserEntity() {
        Page<UserEntity> userEntities = new PageImpl<>(Arrays.asList(mockUserEntity()), Pageable.unpaged(), 1);
        return userEntities;
    }

    protected LoginForm mockLoginForm() {
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("teste@crs.com.br");
        loginForm.setPassword("123456");
        return loginForm;
    }

    protected ProviderDTO mockProviderDTO() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(1L);
        addressDTO.setLine1("Line 1");
        addressDTO.setLine2("Line 2");
        addressDTO.setCity("City");
        addressDTO.setState("State");
        addressDTO.setNeighborhood("Neighborhood");
        addressDTO.setPostalCode("PostalCode");
        addressDTO.setCountry_code("Country Code");

        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setId(1L);
        providerDTO.setFirstName("FirstName");
        providerDTO.setLastName("Lastname");
        providerDTO.setEmail("email@email.com");
        providerDTO.setPhoneNumber("11988887777");
        providerDTO.setTaxPayerId("324");
        providerDTO.setBirthDate(LocalDateTime.now());
        providerDTO.setStatementDescriptor("StatementDescriptor");
        providerDTO.setMcc("324e3");
        providerDTO.setAddressDTO(addressDTO);
        return providerDTO;
    }

    protected ProviderEntity mockProviderEntity() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(1L);
        addressEntity.setLine1("Line 1");
        addressEntity.setLine2("Line 2");
        addressEntity.setCity("City");
        addressEntity.setState("State");
        addressEntity.setNeighborhood("Neighborhood");
        addressEntity.setPostalCode("PostalCode");
        addressEntity.setCountry_code("Country Code");

        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity.setId(1L);
        providerEntity.setFirstName("FirstName");
        providerEntity.setLastName("Lastname");
        providerEntity.setEmail("email@email.com");
        providerEntity.setPhoneNumber("11988887777");
        providerEntity.setTaxPayerId("324");
        providerEntity.setBirthDate(LocalDateTime.now());
        providerEntity.setStatementDescriptor("StatementDescriptor");
        providerEntity.setMcc("324e3");
        providerEntity.setAddressEntity(addressEntity);
        return providerEntity;
    }

    protected Page<ProviderDTO> mockPageProviderDTO() {
        Page<ProviderDTO> providerDTOS = new PageImpl<>(Arrays.asList(mockProviderDTO()), Pageable.unpaged(), 1);
        return providerDTOS;
    }

    protected Page<ProviderEntity> mockPageProviderEntity() {
        Page<ProviderEntity> providerEntities = new PageImpl<>(Arrays.asList(mockProviderEntity()), Pageable.unpaged(), 1);
        return providerEntities;
    }
}
