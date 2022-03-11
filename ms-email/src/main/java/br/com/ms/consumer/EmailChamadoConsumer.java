package br.com.ms.consumer;

import java.time.format.DateTimeFormatter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.ms.dto.consumer.ChamadoConsumer;
import br.com.ms.enums.SituacaoChamado;
import br.com.ms.model.EmailModel;
import br.com.ms.service.EmailService;

@Component
public class EmailChamadoConsumer {
	
	@Autowired
	EmailService emailService;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String assunto;
	private String dataAberturaFormatada;
	private String dataFinalizacaoFormatada;
	
	
	@RabbitListener(queues = "CHAMADOS")
	public void consumer(@Payload ChamadoConsumer chamadoConsumer) {
		EmailModel emailModel = new EmailModel();
		if(chamadoConsumer.getSituacao().equals("Pendente")) {
			assunto = "Abertura do Chamado N° ";
			dataAberturaFormatada = chamadoConsumer.getDataAbertura().format(formatter);
			dataFinalizacaoFormatada = "-";
		}
		else {
			assunto = "Conclusão do Chamado N° ";
			dataFinalizacaoFormatada = chamadoConsumer.getDataFinalizacao().format(formatter);
		}
		emailModel.setAssunto(assunto + chamadoConsumer.getNumeroChamado());
		emailModel.setEmailDestinatario(chamadoConsumer.getCliente().getEmail());
		emailModel.setMensagem("Chamado N° " + chamadoConsumer.getNumeroChamado() + "\n" + "Cliente: " + chamadoConsumer.getCliente().getNome() + "\n"
				+ "CPF: " + chamadoConsumer.getCliente().getCpf() + "\n" + "Telefone: " + chamadoConsumer.getCliente().getTelefone() + "\n"
				+ "Data Abertura: " + dataAberturaFormatada + "\n" + "Funcionário Responsável: " + chamadoConsumer.getFuncionario().getNome() + "\n" + "Descrição: " + chamadoConsumer.getDescricao() + "\n"
				+ "Situação: " + chamadoConsumer.getSituacao() + "\n" + "Data Conclusão: " + dataFinalizacaoFormatada);
		
		emailService.enviarEmail(emailModel);
	}
}
