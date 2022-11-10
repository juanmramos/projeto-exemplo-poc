package br.com.novaxs.crs.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.novaxs.crs.model.dto.TextoAjudaDTO;
import br.com.novaxs.crs.service.TextoAjudaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/texto-ajuda")
@Api(tags = "TextoAjudaController"/* , authorizations = @Authorization(SwaggerAutoConfiguration.AUTH_PROVIDER)*/)
public class TextoAjudaController {

    private final TextoAjudaService textoAjudaService;

    @ApiOperation(value = "Listar texto de ajuda por filtro")
	@GetMapping(value = "listar")
	public ResponseEntity<List<TextoAjudaDTO>> recuperarImpedimentosCliente(TextoAjudaDTO dto) {
		List<TextoAjudaDTO> impedimentosPessoa = textoAjudaService.listar(dto);

		return ResponseEntity.ok(impedimentosPessoa);
	}
    
}
