package br.com.ms.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ms.enums.StatusEmail;
import lombok.Data;

@Entity
@Table(name = "tbl_email")
@Data
public class EmailModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long emailId;
	private String emailRemetente;
	private String emailDestinatario;
	private String assunto;
	@Column(columnDefinition = "TEXT")
	private String mensagem;
	private LocalDateTime dataEnvio;
	private StatusEmail statusEmail;

}
