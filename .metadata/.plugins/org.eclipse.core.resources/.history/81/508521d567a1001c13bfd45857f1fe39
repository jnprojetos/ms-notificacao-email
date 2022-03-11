package br.com.ms.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.ms.enums.StatusEmail;
import br.com.ms.model.EmailModel;
import br.com.ms.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;

	public EmailModel enviarEmail(EmailModel emailModel) {
		emailModel.setDataEnvio(LocalDateTime.now());
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailRemetente());
			message.setTo(emailModel.getEmailDestinatario());
			message.setSubject(emailModel.getAssunto());
			message.setText(emailModel.getMensagem());
			emailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		}finally {
			return emailRepository.save(emailModel);
		}
	}
	

}
