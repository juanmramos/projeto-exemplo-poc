package br.com.novaxs.crs.controller;

import br.com.novaxs.crs.BaseTest;
import br.com.novaxs.crs.configuration.security.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AuthenticationControllerTest extends BaseTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Test
    public void test_autentication_400() {
        //test 1
        when(authenticationManager.authenticate(Mockito.any())).thenReturn(new UsernamePasswordAuthenticationToken(
                mockLoginForm().getEmail(), mockLoginForm().getPassword()));
        ResponseEntity<?> ret = authenticationController.autentication(mockLoginForm());
        assertTrue(ret.getStatusCode().is4xxClientError());
    }


    @Test
    public void test_autentication_200() {
        when(authenticationManager.authenticate(Mockito.any())).thenReturn(new UsernamePasswordAuthenticationToken(
                mockLoginForm().getEmail(), mockLoginForm().getPassword()));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQb3N0bWFuIiwic3ViIjoiMSIsImlhdCI6MTY2NzE0NDc4NCwiZXhwIjoxNjY" +
                "3MjMxMTg0fQ.1s8SotzsCfAQLgbav-pCWuWpwTEK7QX0MNm1ulEbRkc";
        when(tokenService.gerartoken(Mockito.any())).thenReturn(token);
        ResponseEntity<?> ret = authenticationController.autentication(mockLoginForm());
        assertTrue(ret.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void test_autentication_500() {
        when(authenticationManager.authenticate(Mockito.any())).thenReturn(null);
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQb3N0bWFuIiwic3ViIjoiMSIsImlhdCI6MTY2NzE0NDc4NCwiZXhwIjoxNjY" +
                "3MjMxMTg0fQ.1s8SotzsCfAQLgbav-pCWuWpwTEK7QX0MNm1ulEbRkc";
        when(tokenService.gerartoken(Mockito.any())).thenThrow(new AuthenticationException("teste") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        }).thenCallRealMethod();
        ResponseEntity<?> ret = authenticationController.autentication(mockLoginForm());
        assertTrue(ret.getStatusCode().is5xxServerError());
    }

}
