package br.com.apidecurso.cursos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apidecurso.cursos.model.Curso;
import br.com.apidecurso.cursos.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public Curso salvar(Curso curso) {
        return repository.save(curso);
    }

    public List<Curso> listar(String name, String category) {
        if (name != null) return repository.findByNameContainingIgnoreCase(name);
        if (category != null) return repository.findByCategoryContainingIgnoreCase(category);
        return repository.findAll();
    }

    public Curso atualizar(Long id, Curso dados) {
        Curso curso = repository.findById(id).orElseThrow();
        if (dados.getName() != null) curso.setName(dados.getName());
        if (dados.getCategory() != null) curso.setCategory(dados.getCategory());
        return repository.save(curso);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Curso toggleActive(Long id) {
        Curso curso = repository.findById(id).orElseThrow();
        curso.setActive(!curso.isActive());
        return repository.save(curso);
    }
}


