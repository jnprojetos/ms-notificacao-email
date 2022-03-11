package br.com.ms.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.ms.dto.EmailDTO;
import br.com.ms.model.EmailModel;
import br.com.ms.service.EmailService;

@Component
public class EmailConsumer {

	@Autowired
	EmailService emailService;
	
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void listen(@Payload EmailDTO emailDTO) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDTO, emailModel);
		emailService.enviarEmail(emailModel);
		System.out.println("Email Status: "+ emailModel.getStatusEmail().toString());
	}
}
