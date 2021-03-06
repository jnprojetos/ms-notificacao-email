package br.com.ms.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ms.dto.EmailDTO;
import br.com.ms.model.EmailModel;
import br.com.ms.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping(value = "/enviar-email")
	public ResponseEntity<EmailModel> enviar(@RequestBody @Valid EmailDTO emailDTO) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDTO, emailModel);
		emailService.enviarEmail(emailModel);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
