package br.com.novaxs.crs.controller;

import br.com.novaxs.crs.model.dto.ProviderDTO;
import br.com.novaxs.crs.model.dto.UserDTO;
import br.com.novaxs.crs.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping(path = "/new-provider", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createClient(@Valid @RequestBody ProviderDTO providerDTO) {
        try {
            ProviderDTO result = providerService.save(providerDTO);
            if (Objects.nonNull(result)) {
                return new ResponseEntity(result, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @GetMapping(path = "/find-provider/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByIdClient(@PathVariable(value = "id") Long id) {
        try {
            ProviderDTO providerDTO = providerService.findById(id);
            if (Objects.nonNull(providerDTO)) {
                return new ResponseEntity(providerDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/find-all-provider", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByAllClient(@RequestParam int pagina, @RequestParam int qtd) {
        try {
            Page<ProviderDTO> providerDTOS = providerService.findByAll(pagina,qtd);
            if (providerDTOS != null) {
                return new ResponseEntity(providerDTOS, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/find-all-provider-filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByListImoveis(@RequestParam(value = "id", required = false) Long id,
                                               @RequestParam(value = "firstName", required = false) String firstName,
                                               @RequestParam(value = "lastName", required = false) String lastName,
                                               @RequestParam(value = "email", required = false) String email,
                                               @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                               @RequestParam(value = "taxPayerId", required = false) String taxPayerId,
                                               @RequestParam(value = "birthDate", required = false) LocalDateTime birthDate,
                                               @RequestParam(value = "statementDescriptor", required = false) String statementDescriptor,
                                               @RequestParam(value = "mcc", required = false) String mcc,
                                               @RequestParam(value = "Address.id", required = false) Long idAddress,
                                               @RequestParam(value = "Address.postalCode", required = false) String postalCode,
                                               @RequestParam(value = "line1", required = false) String line1,
                                               @RequestParam(value = "line2", required = false) String line2,
                                               @RequestParam(value = "city", required = false) String city,
                                               @RequestParam(value = "neighborhood", required = false) String neighborhood,
                                               @RequestParam(value = "state", required = false) String state,
                                               @RequestParam(value = "country_code", required = false) String country_code) {

        Collection<ProviderDTO> providerDTOS = providerService.findByFilter(id,
                firstName, lastName, email, phoneNumber, taxPayerId, birthDate,
                statementDescriptor, mcc, idAddress, postalCode, line1, line2, city,
                neighborhood, state, country_code);

        if (!CollectionUtils.isEmpty(providerDTOS)) {

            return new ResponseEntity<Collection<ProviderDTO>>(providerDTOS, HttpStatus.OK);
        }

        return new ResponseEntity<String>("Nome não encontrado!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/delete-provider/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id) {
        if (id != null) {
            providerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
