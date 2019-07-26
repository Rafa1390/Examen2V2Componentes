package com.cenfotec.examen2V2.web;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cenfotec.examen2V2.domain.Empleado;
import com.cenfotec.examen2V2.repository.EmpleadoRepository;
import com.cenfotec.examen2V2.repository.FincaRepository;

@Controller
public class EmpleadoController {
	@Autowired
	EmpleadoRepository repo;
	@Autowired
	FincaRepository repFinca;
	
	//Registro de empleado
	@PostMapping("/empleado_registro")
	public ModelAndView empleado_registro(@Valid Empleado empleado, BindingResult result) {
		repo.save(empleado);
		return new ModelAndView("redirect:/");
	}
	
	//Modificar empleado
	//Contrucción del modelo Empleado junto con el id del empleado a modificar
	@RequestMapping(value="/empleado_modificar/{id}")
	public String empleado_id(@PathVariable Long id, ModelMap mp){
		Empleado empleado = repo.getOne(id);
		mp.addAttribute("empleado", empleado);
	    return "empleado_modificar";
	}
	
	@RequestMapping(value="/empleado_editar", method=RequestMethod.POST)
	public ModelAndView empleado_editar(@ModelAttribute("empleado") Empleado empleado){
	    Empleado empAct = repo.getOne(empleado.getId());
	    empAct.setId(empleado.getId());
	    empAct.setId_finca(empleado.getId_finca());
	    empAct.setCedula(empleado.getCedula());
	    empAct.setNombre(empleado.getNombre());
	    empAct.setApellido(empleado.getApellido());
	    empAct.setRol(empleado.getRol());
	    empAct.setEstado(empleado.getEstado());
	    repo.save(empAct);
	    return new ModelAndView("redirect:/empleado_lista/" + empleado.getId_finca());
	}
	//Fin modificar empleado
	
	//Busqueda de empleados por nombre o apellido
	@GetMapping("/empleados_busqueda")
	public String createEmpleadosView(Model model, ModelMap mp) {
		model.addAttribute("produccion", new Empleado());
		mp.put("fincas", repFinca.findAll());
        return "empleados_busqueda";
	}
	
	@RequestMapping(value="empleados_busqueda", method = RequestMethod.POST)
	public String listarEmpleados(@RequestParam("id_finca") Long id_finca, @RequestParam("tipo") String tipo, @RequestParam("dato") String dato, ModelMap mp) {
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		List<Empleado> empleadosBD = repo.findAll();
		
		if(!dato.equals("")) {
			for(Empleado bd : empleadosBD) {
				if(id_finca != 0) {
					if(tipo.equals("Nombre")) {
						if(bd.getId_finca() == id_finca && bd.getNombre().equals(dato)) {
							listaEmpleados.add(bd);
						}
					}else {
						if(bd.getId_finca() == id_finca && bd.getApellido().equals(dato)) {
							listaEmpleados.add(bd);
						}
					}
				} else {
					if(tipo.equals("Nombre")) {
						if(bd.getNombre().equals(dato)) {
							listaEmpleados.add(bd);
						}
					}else {
						if(bd.getApellido().equals(dato)) {
							listaEmpleados.add(bd);
						}
					}
				}
			}
		}else {
			listaEmpleados = empleadosBD;
		}
		
		mp.put("empleados", listaEmpleados);
		return "empleados_busqueda";
	}
	//Fin de busqueda
}
