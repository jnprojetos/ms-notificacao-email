package br.com.ms.consumer;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ms.dto.consumer.FuncionarioConsumer;
import br.com.ms.model.EmailModel;
import br.com.ms.service.EmailService;

@Component
public class EmailFuncionaConsumer {
	
	@Autowired
	EmailService emailService;
	
	@RabbitListener(queues = "FUNCIONARIOS")
	public void consumer(FuncionarioConsumer funcionarioConsumer) {
		EmailModel emailModel = new EmailModel();
		emailModel.setAssunto("Registro Novo Funcionário");
		emailModel.setDataEnvio(LocalDateTime.now());
		emailModel.setEmailDestinatario(funcionarioConsumer.getEmail());
		emailModel.setMensagem("ID: " + funcionarioConsumer.getId() + 
				"\nNome: " + funcionarioConsumer.getNome() + 
				"\nE-mail: "+ funcionarioConsumer.getEmail() + 
				"\nTelefone: " + funcionarioConsumer.getTelefone());
		
		emailService.enviarEmail(emailModel);
		
		System.out.println("Enviado com sucesso!");
	}

}
