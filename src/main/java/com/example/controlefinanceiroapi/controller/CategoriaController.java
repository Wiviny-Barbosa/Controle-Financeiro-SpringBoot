package com.example.controlefinanceiroapi.controller;

import com.example.controlefinanceiroapi.dto.dtoRequest.CategoriaRequestDTO;
import com.example.controlefinanceiroapi.dto.dtoResponse.CategoriaResponseDTO;
import com.example.controlefinanceiroapi.exception.NotFoundException;
import com.example.controlefinanceiroapi.model.Categoria;
import com.example.controlefinanceiroapi.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDTO salvarCategoria(@RequestBody @Valid CategoriaRequestDTO dto){
        return categoriaService.salvarCategoria(dto);
    }

    @PutMapping("{id}")
    public CategoriaResponseDTO atualizarCategoria(@PathVariable("id") UUID id, @RequestBody @Valid CategoriaRequestDTO dto){
        return categoriaService.atualizarCategoria(id, dto);
    }

    @GetMapping("{id}")
    public CategoriaResponseDTO buscarCategoriaPorId(@PathVariable("id") UUID id) {
        return categoriaService.buscarCategoriaPorId(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCategoria(@PathVariable("id") UUID id) {
        categoriaService.deletarCategoria(id);
    }

    @GetMapping
    public List<CategoriaResponseDTO> listarCategorias(){
        return categoriaService.listarCategorias();
    }
}
