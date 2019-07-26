package com.cenfotec.examen2V2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cenfotec.examen2V2.domain.Produccion;

public interface ProduccionRepository extends JpaRepository<Produccion, Long>{
	
	//@Query("select p from tproduccion p where p.id_finca like %?1%")
	//List<Produccion> findProduccionById_finca(int id_finca);
}
