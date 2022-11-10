package br.com.novaxs.crs.model.dto;

import br.com.novaxs.crs.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserDTOTest extends BaseTest {

    @Test
    public void teste_UserDTO_200() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(mockUserDTO().getId());
        userDTO.setName(mockUserDTO().getName());
        userDTO.setEmail(mockUserDTO().getEmail());
        userDTO.setPhone(mockUserDTO().getPhone());
        userDTO.setPasswordUser(mockUserDTO().getPasswordUser());
        Assert.assertNotNull(userDTO);
    }

    @Test
    public void teste_UserDTO_constructor_200() {
        UserDTO userDTO = new UserDTO(mockUserEntity());
        Assert.assertNotNull(userDTO);
    }
}
