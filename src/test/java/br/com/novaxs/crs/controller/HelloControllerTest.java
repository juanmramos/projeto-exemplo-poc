package br.com.novaxs.crs.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class HelloControllerTest {

    @InjectMocks
    private HelloController helloController;

    @Test
    public void test_getCliente_200() {
        ResponseEntity<?> ret = helloController.hello();
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }
}
