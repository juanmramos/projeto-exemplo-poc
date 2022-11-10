package br.com.novaxs.crs.model.convert;

import br.com.novaxs.crs.BaseTest;
import br.com.novaxs.crs.model.dto.UserDTO;
import br.com.novaxs.crs.model.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserConvertTest extends BaseTest {

    @Spy
    @InjectMocks
    private UserConvert userConvert;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void teste_toClientDTO_200() {
        when(userConvert.toClientDTO(mockUserEntity())).thenReturn(mockUserDTO());
        UserDTO userDTO = userConvert.toClientDTO(mockUserEntity());
        Assert.assertEquals(mockUserEntity().getId(), userDTO.getId());
        Assert.assertEquals(mockUserEntity().getName(), userDTO.getName());
        Assert.assertEquals(mockUserEntity().getEmail(), userDTO.getEmail());
        Assert.assertEquals(mockUserEntity().getPhone(), userDTO.getPhone());
        Assert.assertEquals(mockUserEntity().getPasswordUser(), userDTO.getPasswordUser());
    }

    @Test
    public void teste_toClientEntity_null() {
        when(userConvert.toClientEntity(Mockito.any())).thenReturn(new UserEntity());
        UserEntity userEntity = userConvert.toClientEntity(mockUserDTO());
        Assert.assertNull(userEntity);
    }

    @Test
    public void teste_toClientDTOList_null() {
        Page<UserDTO> userDTOS = userConvert.toClientDTOList(mockPageUserEntity());
        Assert.assertNotNull(userDTOS);
    }
}
