package com.guirald.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;

import org.springframework.beans.factory.annotation.Autowired;

import com.guirald.cursomc.domain.enums.TipoCliente;
import com.guirald.cursomc.dto.ClienteNewDTO;
import com.guirald.cursomc.repositories.ClienteRepository;
import com.guirald.cursomc.resources.exception.FieldMessage;
import com.guirald.cursomc.services.validation.util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert constraintAnnotation) {};
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, javax.validation.ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		Optional.ofNullable(clienteRepository.findByEmail(objDto.getEmail())).ifPresent(c -> list.add(new FieldMessage("email", "E-mail já existente")));
		
		for (FieldMessage e : list) {
			// transportar os erros personalizados para a lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());
		}
		return list.isEmpty();
	}

}
