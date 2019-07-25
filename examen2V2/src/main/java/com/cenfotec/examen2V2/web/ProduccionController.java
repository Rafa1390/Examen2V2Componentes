package com.cenfotec.examen2V2.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cenfotec.examen2V2.domain.Produccion;
import com.cenfotec.examen2V2.repository.ProduccionRepository;

@Controller
public class ProduccionController {
	@Autowired
	ProduccionRepository repo;
	
	//Registro de produccion
	@PostMapping("/produccion_registro")
	public ModelAndView produccion_registro(@Valid Produccion produccion, BindingResult result) {
		repo.save(produccion);
		return new ModelAndView("redirect:/");
	}
}
