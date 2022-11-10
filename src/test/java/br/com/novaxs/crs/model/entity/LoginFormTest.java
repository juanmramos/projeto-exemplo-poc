package br.com.novaxs.crs.model.entity;

import br.com.novaxs.crs.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LoginFormTest extends BaseTest {

    @Test
    public void teste_loginForm_200() {
        LoginForm loginForm = new LoginForm();
        loginForm.setPassword("123456");
        loginForm.setEmail("teste@novaxs.com.br");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginForm.convert();
        Assert.assertNotNull(loginForm.getEmail());
        Assert.assertNotNull(loginForm.getPassword());
        Assert.assertNotNull(usernamePasswordAuthenticationToken);
    }
}
