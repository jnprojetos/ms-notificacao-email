package br.com.ms.dto.consumer;

import lombok.Getter;

@Getter
public class FuncionarioConsumer {
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private Boolean ativo;
}
