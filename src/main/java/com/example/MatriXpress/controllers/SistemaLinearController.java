package com.example.MatriXpress.controllers;


import com.example.MatriXpress.dto.SistemaRequestDTO;
import com.example.MatriXpress.dto.SolucaoResponseDTO;
import com.example.MatriXpress.models.ResolvedorGaussiano;
import com.example.MatriXpress.models.SistemaLinear;
import com.example.MatriXpress.models.Solucao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SistemaLinearController {

    private final ResolvedorGaussiano resolvedor = new ResolvedorGaussiano();

    @PostMapping("/resolver")
    public SolucaoResponseDTO resolverSistema(@RequestBody SistemaRequestDTO request) {
        SistemaLinear sistema = new SistemaLinear(request.getCoeficientes(), request.getConstantes());
        Solucao solucao = resolvedor.resolver(sistema);
        return new SolucaoResponseDTO(solucao);
    }
}
