package br.com.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ms.model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {

}
