package com.cenfotec.examen2V2.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cenfotec.examen2V2.domain.Produccion;
import com.cenfotec.examen2V2.repository.FincaRepository;
import com.cenfotec.examen2V2.repository.ProduccionRepository;

@Controller
public class ProduccionController {
	@Autowired
	ProduccionRepository repo;
	@Autowired
	FincaRepository repFinca;
	
	//Registro de produccion
	@PostMapping("/produccion_registro")
	public ModelAndView produccion_registro(@Valid Produccion produccion, BindingResult result) {
		repo.save(produccion);
		return new ModelAndView("redirect:/");
	}	
	
	//Busqueda de produccion en el mes actual
	@GetMapping("/produccion_buqueda")
	public String createProduccionView(Model model, ModelMap mp) {
		model.addAttribute("produccion", new Produccion());
		mp.put("fincas", repFinca.findAll());
        return "produccion_buqueda";
	}
	
	@RequestMapping(value="produccion_buqueda", method = RequestMethod.POST)
	public String listarProduccion(@RequestParam("id_finca") Long id_finca, ModelMap mp) {
		List<Produccion> listaProduccion = new ArrayList<Produccion>();
		List<Produccion> produccionBD = repo.findAll();
		
		for(Produccion bd : produccionBD) {
			if(id_finca != 0) {
				if(bd.getId_finca() == id_finca && bd.getFecha().getMonth() == LocalDate.now().getMonth()) {
					listaProduccion.add(bd);
				}
			} else {
				if(bd.getFecha().getMonth() == LocalDate.now().getMonth()) {
					listaProduccion.add(bd);
				}
			}
		}
		
		mp.put("producciones", listaProduccion);
		return "produccion_buqueda";
	}
	//---Fin de busqueda de produccion---
}
