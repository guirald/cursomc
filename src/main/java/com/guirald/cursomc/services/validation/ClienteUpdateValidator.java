package com.guirald.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.guirald.cursomc.domain.Cliente;
import com.guirald.cursomc.dto.ClienteDTO;
import com.guirald.cursomc.repositories.ClienteRepository;
import com.guirald.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {};
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(ClienteDTO objDto, javax.validation.ConstraintValidatorContext context) {
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		
		Optional<Cliente> cliente = Optional.ofNullable(clienteRepository.findByEmail(objDto.getEmail()));
		
		if (cliente.isPresent() && cliente.get().getId().equals(uriId))
			list.add(new FieldMessage("email", "E-mail já existente"));
		
		for (FieldMessage e : list) {
			// transportar os erros personalizados para a lista de erros do framework
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());
		}
		return list.isEmpty();
	}

}
