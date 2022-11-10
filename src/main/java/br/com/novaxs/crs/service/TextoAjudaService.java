package br.com.novaxs.crs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.novaxs.crs.model.dto.TextoAjudaDTO;
import br.com.novaxs.crs.ws.clients.TextoAjudaRestClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TextoAjudaService {

    private final TextoAjudaRestClient textoAjudaRestClient;

    public List<TextoAjudaDTO> listar(TextoAjudaDTO dto) {
		return textoAjudaRestClient.listar(dto);
	}
    
}
