package com.example.ms_pacientes.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PACIENTES")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(unique = true, nullable = false, length = 12)
    private String rut;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    private Integer edad;

    @Column(nullable = false, length = 255)
    private String diagnostico;

    @Column(length = 20)
    private String estado = "Crítico";

    @Column(name = "fecha_ingreso", updatable = false)
    private LocalDateTime fechaIngreso;

    // Esto asigna la fecha y hora actual automáticamente antes de guardar por primera vez
    @PrePersist
    protected void onCreate() {
        fechaIngreso = LocalDateTime.now();
    }
}