package br.com.ms.dto.consumer;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Getter;

@Getter
public class ClienteConsumer {
	private long id;
    private String nome;
    private String cpf;
    private String telefone;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascimento;
    private String email;

}
