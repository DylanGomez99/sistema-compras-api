package com.compras.sistemacomprasapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "articulo")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "La descripción del artículo es obligatoria")
    @Size(max = 100, message = "La descripción no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String descripcion;

    @NotBlank(message = "La marca del artículo es obligatoria")
    @Size(max = 100, message = "La marca no puede exceder los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String marca;

    @ManyToOne
    @JoinColumn(name = "unidad_medida_id")
    private UnidadMedida unidadMedida;
    
    @NotNull(message = "La existencia es obligatoria")
    @PositiveOrZero(message = "La existencia no puede ser un número negativo")
    @Column(nullable = false)
    private Integer existencia;

    @Column(nullable = false)
    private Boolean estado;

    public Articulo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
