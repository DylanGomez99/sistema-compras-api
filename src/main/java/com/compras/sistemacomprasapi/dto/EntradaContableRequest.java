package com.compras.sistemacomprasapi.dto;

import java.math.BigDecimal;

public class EntradaContableRequest {

    private Integer auxiliarId;
    private Integer cuentaDebitoId;
    private Integer cuentaCreditoId;
    private String descripcion;
    private BigDecimal monto;

    public EntradaContableRequest() {
    }

    public EntradaContableRequest(Integer auxiliarId, Integer cuentaDebitoId, Integer cuentaCreditoId,
                                   String descripcion, BigDecimal monto) {
        this.auxiliarId = auxiliarId;
        this.cuentaDebitoId = cuentaDebitoId;
        this.cuentaCreditoId = cuentaCreditoId;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public Integer getAuxiliarId() {
        return auxiliarId;
    }

    public void setAuxiliarId(Integer auxiliarId) {
        this.auxiliarId = auxiliarId;
    }

    public Integer getCuentaDebitoId() {
        return cuentaDebitoId;
    }

    public void setCuentaDebitoId(Integer cuentaDebitoId) {
        this.cuentaDebitoId = cuentaDebitoId;
    }

    public Integer getCuentaCreditoId() {
        return cuentaCreditoId;
    }

    public void setCuentaCreditoId(Integer cuentaCreditoId) {
        this.cuentaCreditoId = cuentaCreditoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}