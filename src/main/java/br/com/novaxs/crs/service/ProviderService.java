package br.com.novaxs.crs.service;

import br.com.novaxs.crs.model.dto.ProviderDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Collection;

public interface ProviderService {

    ProviderDTO save(ProviderDTO providerDTO);
    ProviderDTO findById(Long id);
    Page<ProviderDTO> findByAll(int paginacao, int qtd);
    void delete(Long id);

    Collection<ProviderDTO> findByFilter(Long id,
                                     String firstName,
                                     String lastName,
                                     String email,
                                     String phoneNumber,
                                         String taxPayerId, LocalDateTime birthDate, String statementDescriptor, String mcc, Long idAddress, String postalCode, String line1, String line2,
                                         String neighborhood, String city, String state, String country_code);
}
