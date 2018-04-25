package br.com.franca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.franca.daos.ProdutoDAO;
import br.com.franca.models.Produto;

@Controller
public class HomeController {
	@Autowired
	ProdutoDAO produtoDao;

	@RequestMapping("/")
	@Cacheable(value = "produtosHome")
	public ModelAndView index() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
}
