package com.example.MatriXpress.dto;

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

    public SolucaoResponseDTO(Solucao solucao) {
        this.tipo = solucao.getTipoSolucao();
        this.valores = solucao.getValores();
        this.mensagem = solucao.toString();
    }

}
