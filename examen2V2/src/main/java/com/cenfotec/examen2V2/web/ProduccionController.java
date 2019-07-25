package com.cenfotec.examen2V2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cenfotec.examen2V2.repository.ProduccionRepository;

@Controller
public class ProduccionController {
	@Autowired
	ProduccionRepository repo;
}
