package com.example.controlefinanceiroapi.service;

import com.example.controlefinanceiroapi.dto.dtoRequest.TransacaoRequestDTO;
import com.example.controlefinanceiroapi.dto.dtoResponse.TransacaoResponseDTO;
import com.example.controlefinanceiroapi.enumeracao.TipoEnum;
import com.example.controlefinanceiroapi.exception.NotFoundException;
import com.example.controlefinanceiroapi.model.Categoria;
import com.example.controlefinanceiroapi.model.Conta;
import com.example.controlefinanceiroapi.model.Transacao;
import com.example.controlefinanceiroapi.repository.CategoriasRepository;
import com.example.controlefinanceiroapi.repository.ContasRepository;
import com.example.controlefinanceiroapi.repository.TransacoesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransacaoService {

    private final TransacoesRepository transacoesRepository;
    private final ContasRepository contasRepository;
    private final CategoriasRepository categoriasRepository;

    public TransacaoService(TransacoesRepository transacoesRepository, ContasRepository contasRepository, CategoriasRepository categoriasRepository) {
        this.transacoesRepository = transacoesRepository;
        this.contasRepository = contasRepository;
        this.categoriasRepository = categoriasRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public TransacaoResponseDTO salvarTransacao(TransacaoRequestDTO dto){
        Conta conta = contasRepository.findById(dto.contaId())
                        .orElseThrow(() -> new NotFoundException("Conta não encontrada"));
        Categoria categoria = categoriasRepository.findById(dto.categoriaId())
                        .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        Transacao transacao = new Transacao();
        transacao.setDescricao(dto.descricao());
        transacao.setValor(dto.valor());
        transacao.setTipo(dto.tipo());
        transacao.setConta(conta);
        transacao.setCategoria(categoria);

        conta.setSaldo(transacao.aplicarEfeitoNoSaldo(conta.getSaldo()));

        Transacao salvo = transacoesRepository.save(transacao);
        return new TransacaoResponseDTO(salvo.getId(), salvo.getDescricao(), salvo.getValor(), salvo.getTipo(), salvo.getData(), salvo.getConta(), salvo.getCategoria());
    }

    @Transactional(rollbackFor = Exception.class)
    public TransacaoResponseDTO atualizarTransacao(UUID id, TransacaoRequestDTO dto){

        Transacao transacao = transacoesRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Transação não encontrada"));

        Conta conta = contasRepository.findById(dto.contaId())
                        .orElseThrow(() -> new NotFoundException("Conta não encontrada"));

        Categoria categoria = categoriasRepository.findById(dto.categoriaId())
                        .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        transacao.getConta().setSaldo(transacao.reverterEfeitoNoSaldo(transacao.getConta().getSaldo()));

        transacao.setDescricao(dto.descricao());
        transacao.setValor(dto.valor());
        transacao.setTipo(dto.tipo());
        transacao.setCategoria(categoria);
        transacao.setConta(conta);

        conta.setSaldo(transacao.aplicarEfeitoNoSaldo(conta.getSaldo()));

        Transacao salvo = transacoesRepository.save(transacao);
        return new TransacaoResponseDTO(salvo.getId(), salvo.getDescricao(), salvo.getValor(), salvo.getTipo(), salvo.getData(), salvo.getConta(), salvo.getCategoria());
    }

    public TransacaoResponseDTO buscarTransacaoPorId(UUID id){
        Transacao salvo = transacoesRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Transação com ID "+id+" não encontrado."));
        return new TransacaoResponseDTO(salvo.getId(), salvo.getDescricao(), salvo.getValor(), salvo.getTipo(), salvo.getData(), salvo.getConta(), salvo.getCategoria());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarTransacao(UUID id){
        Transacao transacao = transacoesRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Transação com ID "+id+" não encontrado."));

        transacao.getConta().setSaldo(transacao.reverterEfeitoNoSaldo(transacao.getConta().getSaldo()));

        transacoesRepository.deleteById(id);
    }

    public List<TransacaoResponseDTO> listarTransacao(){
        List<Transacao> transacaos = transacoesRepository.findAll();
        List<TransacaoResponseDTO> transacaoResponse = new ArrayList<>();

        for(Transacao transacao : transacaos){
            TransacaoResponseDTO transacaoResponseDTO = new TransacaoResponseDTO(transacao.getId(), transacao.getDescricao(),
                    transacao.getValor(), transacao.getTipo(), transacao.getData(), transacao.getConta(), transacao.getCategoria());
            transacaoResponse.add(transacaoResponseDTO);
        }
        return transacaoResponse;
    }
}
