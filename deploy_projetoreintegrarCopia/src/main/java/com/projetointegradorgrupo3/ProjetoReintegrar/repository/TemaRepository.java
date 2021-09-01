package com.projetointegradorgrupo3.ProjetoReintegrar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetointegradorgrupo3.ProjetoReintegrar.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema,Long> {

	public List<Tema> findAllByDescricaoContainingIgnoreCase(String titulo);
}
