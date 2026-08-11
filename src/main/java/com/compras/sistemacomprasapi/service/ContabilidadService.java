package com.compras.sistemacomprasapi.service;

import com.compras.sistemacomprasapi.dto.EntradaContableRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ContabilidadService {

    private static final Logger log = LoggerFactory.getLogger(ContabilidadService.class);

    // Valores fijos según la documentación del WS de Contabilidad:
    // auxiliarId 5 = "Otros Auxiliares" (Compras no tiene id propio todavía)
    // cuentaDebitoId 1 = "Caja General", cuentaCreditoId 2 = "Cuentas por Pagar"
    private static final int AUXILIAR_ID_COMPRAS = 5;
    private static final int CUENTA_DEBITO_ID = 1;
    private static final int CUENTA_CREDITO_ID = 2;

    private final RestTemplate restTemplate;

    @Value("${contabilidad.ws.url}")
    private String contabilidadUrl;

    public ContabilidadService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void registrarAsientoPorOrdenCompra(String numeroOrden, BigDecimal monto) {

        EntradaContableRequest request = new EntradaContableRequest(
                AUXILIAR_ID_COMPRAS,
                CUENTA_DEBITO_ID,
                CUENTA_CREDITO_ID,
                "Orden de Compra " + numeroOrden,
                monto
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EntradaContableRequest> entity = new HttpEntity<>(request, headers);

        try {
            restTemplate.postForEntity(contabilidadUrl, entity, String.class);
            log.info("Asiento contable enviado correctamente para la orden {}", numeroOrden);
        } catch (RestClientException e) {
            log.error("No se pudo registrar el asiento contable de la orden {}: {}",
                    numeroOrden, e.getMessage());
        }
    }
}