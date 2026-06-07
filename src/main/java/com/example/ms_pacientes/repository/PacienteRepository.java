package com.example.ms_pacientes.repository;

import com.example.ms_pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Si en el futuro quieres buscar por RUT, solo declaras esto y Spring hace el resto
    Optional<Paciente> findByRut(String rut);
}