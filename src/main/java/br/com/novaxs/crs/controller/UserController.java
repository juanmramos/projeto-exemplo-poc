package br.com.novaxs.crs.controller;

import br.com.novaxs.crs.model.dto.UserDTO;
import br.com.novaxs.crs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/client")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/new-client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClient(@Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO result = userService.save(userDTO);
            if (Objects.nonNull(result)) {
                return new ResponseEntity(result, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @GetMapping(path = "/find-client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByIdClient(@PathVariable(value = "id") Long id) {
        try {
            UserDTO userDTO = userService.findById(id);
            if (Objects.nonNull(userDTO)) {
                return new ResponseEntity(userDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/find-all-client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByAllClient(@RequestParam int pagina, @RequestParam int qtd) {
        try {
            Page<UserDTO> userDTOS = userService.findByAll(pagina,qtd);
            if (userDTOS != null) {
                return new ResponseEntity(userDTOS, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/find-all-client-filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByListImoveis(@RequestParam(value = "id", required = false) Long id,
                                               @RequestParam(value = "name", required = false) String name,
                                               @RequestParam(value = "email", required = false) String email,
                                               @RequestParam(value = "passwordUser", required = false) String passwordUser,
                                               @RequestParam(value = "phone", required = false) String phone) {

        Collection<UserDTO> userServiceByFilter = userService.findByFilter(id, name, email, passwordUser, phone);

        if (!CollectionUtils.isEmpty(userServiceByFilter)) {

            return new ResponseEntity<Collection<UserDTO>>(userServiceByFilter, HttpStatus.OK);
        }

        return new ResponseEntity<String>("Nome não encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/delete-client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id) {
        if (id != null) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
