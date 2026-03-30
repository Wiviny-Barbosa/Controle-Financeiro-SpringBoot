package com.example.controlefinanceiroapi.service;

import com.example.controlefinanceiroapi.dto.dtoRequest.CategoriaRequestDTO;
import com.example.controlefinanceiroapi.dto.dtoResponse.CategoriaResponseDTO;
import com.example.controlefinanceiroapi.exception.NotFoundException;
import com.example.controlefinanceiroapi.model.Categoria;
import com.example.controlefinanceiroapi.repository.CategoriasRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    private final CategoriasRepository categoriasRepository;

    public CategoriaService(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

   public CategoriaResponseDTO salvarCategoria(CategoriaRequestDTO dto){
            Categoria categoria = new Categoria();
            categoria.setNome(dto.nome());
            Categoria salvo =  categoriasRepository.save(categoria);
            return new CategoriaResponseDTO(salvo.getId(), salvo.getNome());
   }

   public CategoriaResponseDTO atualizarCategoria(UUID id, CategoriaRequestDTO dto){
        Categoria categoria = categoriasRepository.findById(id).orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
        categoria.setNome(dto.nome());
        Categoria salvo = categoriasRepository.save(categoria);
        return new CategoriaResponseDTO(salvo.getId(), salvo.getNome());
   }

   public CategoriaResponseDTO buscarCategoriaPorId(UUID id){
        Categoria salvo = categoriasRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
        return new CategoriaResponseDTO(salvo.getId(), salvo.getNome());
   }

   public void deletarCategoria(UUID id){
        if(!categoriasRepository.existsById(id)){
            throw new NotFoundException("Categoria não encontrada");
        }
        categoriasRepository.deleteById(id);
   }

   public List<CategoriaResponseDTO> listarCategorias(){
        List<Categoria> categorias = categoriasRepository.findAll();
        List<CategoriaResponseDTO> categoriasResponse = new ArrayList<>();

        for(Categoria categoria : categorias){
            CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
            categoriasResponse.add(categoriaResponseDTO);
        }
       return categoriasResponse;
   }
}
