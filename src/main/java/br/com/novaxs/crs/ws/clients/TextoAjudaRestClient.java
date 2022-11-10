package br.com.novaxs.crs.ws.clients;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.novaxs.crs.model.dto.TextoAjudaDTO;
import feign.Headers;

@FeignClient(name = "textoAjudaRestClient", url = "http://localhost:8081/")
public interface TextoAjudaRestClient {
    
    @GetMapping("/listar")
    List<TextoAjudaDTO> listar(TextoAjudaDTO dto);

    @GetMapping
    public Page<TextoAjudaDTO> findByFiltro(@RequestHeader("impedimentosDTO") TextoAjudaDTO impedimentosDTO, Pageable pageable) ;

    @PostMapping
    public TextoAjudaDTO adicionar(TextoAjudaDTO dto);

    @PutMapping("alterar/{codigo}")
    public TextoAjudaDTO alterar(@PathVariable(value = "codigo") UUID codigo, TextoAjudaDTO dto);

    @DeleteMapping("/{codigo}")
    public String remover(@PathVariable(value = "codigo") UUID codigo);
}
