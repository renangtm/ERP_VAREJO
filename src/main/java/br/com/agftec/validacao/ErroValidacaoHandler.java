package br.com.agftec.validacao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ErroValidacaoHandler {

	@Autowired
	private MessageSource msg;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WebExchangeBindException.class)
	public Mono<List<ErroValidacao>> erroValidacao(WebExchangeBindException ex) {

		List<FieldError> erros = ex.getBindingResult().getFieldErrors();

		return Mono.just(erros.stream().map(e -> {

			ErroValidacao ev = new ErroValidacao();
			ev.setCampo(e.getField());
			ev.setValor(msg.getMessage(e, LocaleContextHolder.getLocale()));

			return ev;

		}).collect(Collectors.toList()));

	}

}
