package br.com.apidecurso.cursos.repository;

import java.util.List;

import br.com.apidecurso.cursos.model.Curso;

public interface CursoRepository {
  
  List<Curso> findByNameContainingIgnoreCase(String name);
  List<Curso> findByCategoryContainingIgnoreCase(String category);  
}
