package br.com.novaxs.crs.controller;

import br.com.novaxs.crs.BaseTest;
import br.com.novaxs.crs.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserControllerTest extends BaseTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void test_createClient_400() {
        //test 1
        when(userService.save(null)).thenReturn(null);
        ResponseEntity<?> ret = userController.createClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());

        //test 2
        when(userService.save(null)).thenReturn(null);
        ret = userController.createClient(mockUserDTO());
        assertTrue(ret.getStatusCode().is4xxClientError());
    }


    @Test
    public void test_createClient_200() {
        when(userService.save(Mockito.any())).thenReturn(mockUserDTO());
        ResponseEntity<?> ret = userController.createClient(mockUserDTO());
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_createClient_500() {
        when(userService.save(Mockito.any())).thenThrow(NullPointerException.class);
        ResponseEntity<?> ret = userController.createClient(null);
        assertTrue(ret.getStatusCode().is5xxServerError());
    }

    @Test
    public void test_findByIdClient_400() {
        //test 1
        ResponseEntity<?> ret = userController.findByIdClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());

        //test 2
        when(userService.findById(1L)).thenReturn(null);
        ret = userController.findByIdClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());
    }

    @Test
    public void test_findByIdClient_200() {
        when(userService.findById(Mockito.any())).thenReturn(mockUserDTO());
        ResponseEntity<?> ret = userController.findByIdClient(1L);
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_findByIdClient_500() {
        Long id = null;
        when(userService.findById(Mockito.any())).thenThrow(NullPointerException.class);
        ResponseEntity<?> ret = userController.findByIdClient(id);
        assertTrue(ret.getStatusCode().is5xxServerError());
    }

    @Test
    public void test_findByAllClient_400() {
        //test 1
        ResponseEntity<?> ret = userController.findByAllClient(0, 0);
        assertTrue(ret.getStatusCode().is4xxClientError());

        //test 2
        when(userService.findByAll(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);
        ret = userController.findByAllClient(0,0);
        assertTrue(ret.getStatusCode().is4xxClientError());
    }

    @Test
    public void test_findByAllClient_200() {
        when(userService.findByAll(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockPageUserDTO());
        ResponseEntity<?> ret = userController.findByAllClient(0,1);
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_findByAllClient_500() {
        when(userService.findByAll(Mockito.anyInt(), Mockito.anyInt())).thenThrow(NullPointerException.class);
        ResponseEntity<?> ret = userController.findByAllClient(0,0);
        assertTrue(ret.getStatusCode().is5xxServerError());
    }

    @Test
    public void test_deleteClient_400() {
        //test 1
        ResponseEntity<?> ret = userController.deleteClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());

        //test 2
        ret = userController.deleteClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());
    }

    @Test
    public void test_deleteClient_200() {
        ResponseEntity<?> ret = userController.deleteClient(1L);
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }

}
