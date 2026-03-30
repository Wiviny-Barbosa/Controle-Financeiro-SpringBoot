package com.example.controlefinanceiroapi.controller;

import com.example.controlefinanceiroapi.dto.dtoRequest.TransacaoRequestDTO;

import com.example.controlefinanceiroapi.dto.dtoResponse.TransacaoResponseDTO;
import com.example.controlefinanceiroapi.model.Transacao;
import com.example.controlefinanceiroapi.service.TransacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public TransacaoResponseDTO salvarTransacao(@RequestBody TransacaoRequestDTO dto){
            return transacaoService.salvarTransacao(dto);
    }

    @PutMapping("{id}")
    public TransacaoResponseDTO atualizarTransacao(@PathVariable UUID id, @RequestBody TransacaoRequestDTO dto){
        return transacaoService.atualizarTransacao(id, dto);
    }

    @GetMapping("{id}")
    public TransacaoResponseDTO buscarTransacaoPorId(@PathVariable UUID id){
        return transacaoService.buscarTransacaoPorId(id);
    }

    @DeleteMapping("{id}")
    public void deletarTransacao(@PathVariable UUID id){
        transacaoService.deletarTransacao(id);
    }

    @GetMapping
    public List<TransacaoResponseDTO> listarTransacao(){
        return transacaoService.listarTransacao();
    }
}
