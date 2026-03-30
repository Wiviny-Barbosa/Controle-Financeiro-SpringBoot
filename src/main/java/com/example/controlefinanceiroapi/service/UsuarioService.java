package com.example.controlefinanceiroapi.service;

import com.example.controlefinanceiroapi.dto.dtoRequest.UsuarioRequestDTO;
import com.example.controlefinanceiroapi.dto.dtoResponse.UsuarioResponseDTO;
import com.example.controlefinanceiroapi.exception.NotFoundException;
import com.example.controlefinanceiroapi.model.Usuario;
import com.example.controlefinanceiroapi.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuariosRepository usuariosRepository;

    public UsuarioService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        if(usuariosRepository.existsByEmail(usuario.getEmail())){
            throw new NotFoundException("Email não pode ser duplicado.");
        }
        Usuario salvo = usuariosRepository.save(usuario);

        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public UsuarioResponseDTO buscarUsuarioPorId(UUID id){
        Usuario salvo = usuariosRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario com ID "+id+" não encontrado."));
        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO dto){
        Usuario usuario = usuariosRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        Usuario salvo = usuariosRepository.save(usuario);
        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public void deletarUsuario(UUID id){
        if(!usuariosRepository.existsById(id)){
            throw new NotFoundException("Não existe esse usuario");
        }
        usuariosRepository.deleteById(id);
    }

    public List<UsuarioResponseDTO> listarUsuario(){
        List<Usuario> usuarios = usuariosRepository.findAll();
        List<UsuarioResponseDTO> usuarioResponse = new ArrayList<>();

        for(Usuario usuario : usuarios){
            UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
            usuarioResponse.add(usuarioResponseDTO);
        }
        
        return usuarioResponse;
    }
}
