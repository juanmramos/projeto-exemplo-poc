package br.com.novaxs.crs.model.entity;

import br.com.novaxs.crs.BaseTest;
import br.com.novaxs.crs.model.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserEntityTest extends BaseTest {

    @Test
    public void teste_UserEntity_200() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(mockUserDTO().getId());
        userEntity.setName(mockUserDTO().getName());
        userEntity.setEmail(mockUserDTO().getEmail());
        userEntity.setPhone(mockUserDTO().getPhone());
        userEntity.setPasswordUser(mockUserDTO().getPasswordUser());
        int hash = userEntity.hashCode();
        Assert.assertNotNull(userEntity.getName());
        Assert.assertNotNull(userEntity.getPasswordUser());
        Assert.assertNotNull(userEntity.getPassword());
        Assert.assertNotNull(userEntity.getEmail());
        Assert.assertNotNull(userEntity.getPhone());
        Assert.assertNotNull(userEntity.getId());
        Assert.assertNotNull(hash);
        Assert.assertNotNull(userEntity.equals(mockUserEntity()));
    }

    @Test
    public void teste_UserEntity_constructor_200() {
        UserDTO userDTO = new UserDTO(mockUserEntity());
        Assert.assertNotNull(userDTO);
    }
}
