package com.example.controlefinanceiroapi.service;

import com.example.controlefinanceiroapi.dto.dtoRequest.ContaRequestDTO;
import com.example.controlefinanceiroapi.dto.dtoResponse.ContaResponseDTO;
import com.example.controlefinanceiroapi.exception.NotFoundException;
import com.example.controlefinanceiroapi.model.Conta;
import com.example.controlefinanceiroapi.model.Usuario;
import com.example.controlefinanceiroapi.repository.ContasRepository;
import com.example.controlefinanceiroapi.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContaService {

    private final UsuariosRepository usuariosRepository;
    private final ContasRepository contasRepository;

    public ContaService(UsuariosRepository usuariosRepository, ContasRepository contasRepository) {
        this.usuariosRepository = usuariosRepository;
        this.contasRepository = contasRepository;
    }

    public ContaResponseDTO salvarConta(ContaRequestDTO dto){
        Usuario usuario = usuariosRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if(dto.saldo() < 0){
            throw new IllegalArgumentException("Saldo não pode ser negativo");
        }

        Conta conta = new Conta();
        conta.setNome(dto.nome());
        conta.setSaldo(dto.saldo());
        conta.setUsuario(usuario);

        Conta salvo = contasRepository.save(conta);

        return new ContaResponseDTO(salvo.getId(), salvo.getNome(), salvo.getSaldo(), salvo.getUsuario());
    }

    public ContaResponseDTO buscarContaPorId(UUID id){
        Conta salvo = contasRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta com ID "+id+" não encontrado."));
        return new ContaResponseDTO(salvo.getId(), salvo.getNome(), salvo.getSaldo(), salvo.getUsuario());
    }

    public ContaResponseDTO atualizarConta(UUID id, ContaRequestDTO dto){
        Conta conta = contasRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta não encontrado"));
        Usuario usuario = usuariosRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));

        if(dto.saldo() < 0){
            throw new NotFoundException("Saldo não pode ser negativo");
        }

        conta.setNome(dto.nome());
        conta.setSaldo(dto.saldo());
        conta.setUsuario(usuario);
        Conta salvo = contasRepository.save(conta);
        return new ContaResponseDTO(salvo.getId(), salvo.getNome(), salvo.getSaldo(), salvo.getUsuario());
    }

    public void deletarConta(UUID id){
        if(!contasRepository.existsById(id)){
            throw new NotFoundException("Conta não encontrado");
        }
        contasRepository.deleteById(id);
    }

    public List<Conta> listarContas(){
        return contasRepository.findAll();
    }
}
