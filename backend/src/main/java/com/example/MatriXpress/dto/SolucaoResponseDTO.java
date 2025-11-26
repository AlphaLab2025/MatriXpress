package com.example.MatriXpress.dto;

import java.util.List;

import com.example.MatriXpress.enumeration.TipoSolucao;
import lombok.Getter;
import lombok.Setter;
import com.example.MatriXpress.models.Solucao;

@Getter
@Setter
public class SolucaoResponseDTO {

    private TipoSolucao tipo;
    private double[] valores;
    private String mensagem;
    private List<String> passos;

    public SolucaoResponseDTO(Solucao solucao) {
        this.tipo = solucao.getTipoSolucao();
        this.valores = solucao.getValores();
        this.mensagem = solucao.toString();
        this.passos = solucao.getPassos();
    }

}
