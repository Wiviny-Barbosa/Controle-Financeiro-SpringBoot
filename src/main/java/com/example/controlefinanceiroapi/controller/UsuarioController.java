package com.example.controlefinanceiroapi.controller;

import com.example.controlefinanceiroapi.dto.dtoRequest.UsuarioRequestDTO;
import com.example.controlefinanceiroapi.dto.dtoResponse.UsuarioResponseDTO;
import com.example.controlefinanceiroapi.model.Usuario;
import com.example.controlefinanceiroapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioResponseDTO criarUsuario(@RequestBody @Valid UsuarioRequestDTO dto){
        return usuarioService.salvarUsuario(dto);
    }

    @PutMapping("{id}")
    public UsuarioResponseDTO atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioRequestDTO dto){
        return usuarioService.atualizarUsuario(id, dto);
    }

    @GetMapping("{id}")
    public UsuarioResponseDTO buscarUsuarioPorId(@PathVariable UUID id){
        return usuarioService.buscarUsuarioPorId(id);
    }

    @DeleteMapping("{id}")
    public void deletarUsuario(@PathVariable UUID id){
        usuarioService.deletarUsuario(id);
    }

    @GetMapping
    public List<Usuario> listarUsuario(){
        return usuarioService.listarUsuario();
    }

}
