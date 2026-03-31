package com.example.controlefinanceiroapi.controller;

import com.example.controlefinanceiroapi.dto.dtoRequest.ContaRequestDTO;
import com.example.controlefinanceiroapi.dto.dtoResponse.ContaResponseDTO;
import com.example.controlefinanceiroapi.exception.NotFoundException;
import com.example.controlefinanceiroapi.model.Conta;
import com.example.controlefinanceiroapi.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponseDTO salvarConta(@RequestBody ContaRequestDTO dto){
        return contaService.salvarConta(dto);
    }

    @PutMapping("{id}")
    public ContaResponseDTO atualizarConta(@PathVariable UUID id, @RequestBody ContaRequestDTO dto){
        return contaService.atualizarConta(id, dto);
    }

    @GetMapping("{id}")
    public ContaResponseDTO buscarContaPorId(@PathVariable UUID id){
        return contaService.buscarContaPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConta(@PathVariable UUID id){
        contaService.deletarConta(id);
    }

    @GetMapping
    public List<ContaResponseDTO> listarContas(){
        return contaService.listarContas();
    }
}
