package br.com.novaxs.crs.controller;

import br.com.novaxs.crs.configuration.security.TokenService;
import br.com.novaxs.crs.model.dto.TokenDTO;
import br.com.novaxs.crs.model.entity.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autentication(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dadosLogin = form.convert();

        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerartoken(authenticate);
            if (token != null) {
                return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
            }
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
