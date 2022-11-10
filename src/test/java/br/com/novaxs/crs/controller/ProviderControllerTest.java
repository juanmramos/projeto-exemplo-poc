package br.com.novaxs.crs.controller;

import br.com.novaxs.crs.BaseTest;
import br.com.novaxs.crs.service.ProviderService;
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
public class ProviderControllerTest extends BaseTest {

    @InjectMocks
    private ProviderController providerController;

    @Mock
    private ProviderService providerService;

    @Test
    public void test_createClient_400() {
        //test 1
        when(providerService.save(null)).thenReturn(null);
        ResponseEntity<?> ret = providerController.createClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());

        //test 2
        when(providerService.save(null)).thenReturn(null);
        ret = providerController.createClient(mockProviderDTO());
        assertTrue(ret.getStatusCode().is4xxClientError());
    }


    @Test
    public void test_createClient_200() {
        when(providerService.save(Mockito.any())).thenReturn(mockProviderDTO());
        ResponseEntity<?> ret = providerController.createClient(mockProviderDTO());
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_createClient_500() {
        when(providerService.save(Mockito.any())).thenThrow(NullPointerException.class);
        ResponseEntity<?> ret = providerController.createClient(null);
        assertTrue(ret.getStatusCode().is5xxServerError());
    }

    @Test
    public void test_findByIdClient_400() {
        //test 1
        ResponseEntity<?> ret = providerController.findByIdClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());

        //test 2
        when(providerService.findById(1L)).thenReturn(null);
        ret = providerController.findByIdClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());
    }

    @Test
    public void test_findByIdClient_200() {
        when(providerService.findById(Mockito.any())).thenReturn(mockProviderDTO());
        ResponseEntity<?> ret = providerController.findByIdClient(1L);
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_findByIdClient_500() {
        Long id = null;
        when(providerService.findById(Mockito.any())).thenThrow(NullPointerException.class);
        ResponseEntity<?> ret = providerController.findByIdClient(id);
        assertTrue(ret.getStatusCode().is5xxServerError());
    }

    @Test
    public void test_findByAllClient_400() {
        //test 1
        ResponseEntity<?> ret = providerController.findByAllClient(0, 0);
        assertTrue(ret.getStatusCode().is4xxClientError());

        //test 2
        when(providerService.findByAll(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);
        ret = providerController.findByAllClient(0,0);
        assertTrue(ret.getStatusCode().is4xxClientError());
    }

    @Test
    public void test_findByAllClient_200() {
        when(providerService.findByAll(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockPageProviderDTO());
        ResponseEntity<?> ret = providerController.findByAllClient(0,1);
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_findByAllClient_500() {
        when(providerService.findByAll(Mockito.anyInt(), Mockito.anyInt())).thenThrow(NullPointerException.class);
        ResponseEntity<?> ret = providerController.findByAllClient(0,0);
        assertTrue(ret.getStatusCode().is5xxServerError());
    }

    @Test
    public void test_deleteClient_400() {
        //test 1
        ResponseEntity<?> ret = providerController.deleteClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());

        //test 2
        ret = providerController.deleteClient(null);
        assertTrue(ret.getStatusCode().is4xxClientError());
    }

    @Test
    public void test_deleteClient_200() {
        ResponseEntity<?> ret = providerController.deleteClient(1L);
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }

}
