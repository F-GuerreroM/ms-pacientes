package com.example.ms_pacientes.service;

import com.example.ms_pacientes.model.Paciente;
import com.example.ms_pacientes.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor 
public class PacienteService {

    // Ya no usamos @Autowired, usamos 'final'
    private final PacienteRepository repository;

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }

   public Paciente actualizar(Long id, Paciente pacienteRecibido) {
    return repository.findById(id).map(pacienteExistente -> {
        // Ahora sí estamos actualizando todos los campos, incluyendo el RUT
        pacienteExistente.setRut(pacienteRecibido.getRut());
        pacienteExistente.setNombreCompleto(pacienteRecibido.getNombreCompleto());
        pacienteExistente.setEdad(pacienteRecibido.getEdad());
        pacienteExistente.setDiagnostico(pacienteRecibido.getDiagnostico());
        pacienteExistente.setEstado(pacienteRecibido.getEstado());
        
        return repository.save(pacienteExistente);
    }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}