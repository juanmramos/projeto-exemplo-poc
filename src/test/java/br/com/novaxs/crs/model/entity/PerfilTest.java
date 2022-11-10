package br.com.novaxs.crs.model.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PerfilTest {

    @Test
    public void teste_perfil_200() {
        Perfil perfil = new Perfil();
        perfil.setId(1L);
        perfil.setName("ADM");
        Assert.assertNotNull(perfil.getId());
        Assert.assertNotNull(perfil.getName());
        Assert.assertNotNull(perfil.getAuthority());
    }
}
