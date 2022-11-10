package br.com.novaxs.crs.model.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TokenDTOTest {

    @Test
    public void teste_tokenDTO_constructor_200() {
        TokenDTO tokenDTO = new TokenDTO("fsedfdsfdsdrgfdrgfdgdg", "Bearer");
        Assert.assertNotNull(tokenDTO);
    }

    @Test
    public void teste_tokenDTO_200() {
        TokenDTO tokenDTO = new TokenDTO("fsedfdsfdsdrgfdrgfdgdg", "Bearer");
        Assert.assertNotNull(tokenDTO.getToken());
        Assert.assertNotNull(tokenDTO.getTipo());
    }
}
