package br.com.ms.dto.consumer;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Getter;

@Getter
public class ChamadoConsumer {
	
	private Long numeroChamado;

    private ClienteConsumer cliente;

    private FuncionarioConsumer funcionario;

    private String descricao;

    private String observacao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataAbertura;

    private String situacao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataFinalizacao;

}
