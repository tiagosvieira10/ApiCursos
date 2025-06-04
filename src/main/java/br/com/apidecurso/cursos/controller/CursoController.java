package br.com.apidecurso.cursos.controller;

import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apidecurso.cursos.model.Curso;
import br.com.apidecurso.cursos.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService service;

    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(curso));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listar(@RequestParam(required = false) String name,
                                              @RequestParam(required = false) String category) {
        return ResponseEntity.ok(service.listar(name, category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Curso dados = new Curso();
        dados.setName(body.get("name"));
        dados.setCategory(body.get("category"));
        return ResponseEntity.ok(service.atualizar(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Curso> toggleActive(@PathVariable Long id) {
        return ResponseEntity.ok(service.toggleActive(id));
    }
}

