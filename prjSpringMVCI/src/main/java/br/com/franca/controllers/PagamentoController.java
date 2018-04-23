package br.com.franca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.franca.models.CarrinhoCompras;
import br.com.franca.models.DadosPagamento;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {

	@Autowired
	CarrinhoCompras carrinho;
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public ModelAndView finalizar(RedirectAttributes model) {
		try {
			String uri = "http://book-payment.herokuapp.com/payment";
			String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
			model.addFlashAttribute("sucesso", response);
			System.out.println(response);
			return new ModelAndView("redirect:/produtos");
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			model.addFlashAttribute("falha", "Valor maior que o permitido");
			return new ModelAndView("redirect:/produtos");
		}
	}

}
