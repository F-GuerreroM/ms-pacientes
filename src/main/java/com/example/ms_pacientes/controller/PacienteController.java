package com.example.ms_pacientes.controller;

import com.example.ms_pacientes.model.Paciente;
import com.example.ms_pacientes.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor // Aplicamos la misma anotación de Lombok
public class PacienteController {

    // Cambiamos a 'final' y quitamos @Autowired
    private final PacienteService service;

    @GetMapping
    public List<Paciente> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> crear(@RequestBody Paciente paciente) {
        Paciente nuevoPaciente = service.guardar(paciente);
        return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        Paciente pacienteActualizado = service.actualizar(id, paciente);
        if (pacienteActualizado != null) {
            return ResponseEntity.ok(pacienteActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}