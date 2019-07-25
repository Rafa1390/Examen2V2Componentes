package com.cenfotec.examen2V2.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cenfotec.examen2V2.domain.Finca;
import com.cenfotec.examen2V2.repository.FincaRepository;

@Controller
public class FincaController {
	@Autowired
	FincaRepository repo;
	
	//Listar fincas
	@RequestMapping(value="", method = RequestMethod.GET)
    public String listaFincas(ModelMap mp){
        mp.put("fincas", repo.findAll());
        return "finca_lista";
    }
	
	//Registrar finca
	@GetMapping("/finca")
	public String createFincaView(Model model) {
		model.addAttribute("finca", new Finca());
        return "finca";
	}
	
	@PostMapping("/finca")
	public ModelAndView createFinca(@Valid Finca finca, BindingResult result) {
		repo.save(finca);
		return new ModelAndView("redirect:/");
	}
	//---Fin registrar finca---
	
	//Modificar finca
	@RequestMapping(value="/finca_actualizar/{id}")
	public String finca_actualizar(@PathVariable Long id, ModelMap mp){
		Finca finca = repo.getOne(id);
		mp.addAttribute("finca", finca);
	    return "finca_actualizar";
	}
	
	@RequestMapping(value="/finca_editar", method=RequestMethod.POST)
	public ModelAndView finca_editar(@ModelAttribute("finca") Finca finca){
	    Finca fincaAct = repo.getOne(finca.getId());
	    fincaAct.setId(finca.getId());
	    fincaAct.setNombre(finca.getNombre());
	    fincaAct.setDireccion(finca.getDireccion());
	    fincaAct.setEspacio(finca.getEspacio());
	    repo.save(fincaAct);
	    return new ModelAndView("redirect:/");
	}
	//---Fin modificar finca---
}
