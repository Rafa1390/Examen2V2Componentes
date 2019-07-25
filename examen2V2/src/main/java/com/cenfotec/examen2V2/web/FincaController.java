package com.cenfotec.examen2V2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.examen2V2.repository.FincaRepository;

@Controller
public class FincaController {
	@Autowired
	FincaRepository repo;
	
	@RequestMapping(value="", method = RequestMethod.GET)
    public String listaFincas(ModelMap mp){
        mp.put("fincas", repo.findAll());
        return "finca_lista";
    }
}
