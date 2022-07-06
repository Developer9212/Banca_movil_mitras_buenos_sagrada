package com.fenoreste.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fenoreste.entity.Pagos;

public interface PagosRepository extends JpaRepository<Pagos,Integer>{

	@Query(value = "SELECT * FROM sai_estado_de_cuenta_libretas(?1,?2,?3,?4,?5,0) WHERE replace(cargo,',','')::::numeric=0",nativeQuery = true)
	List<Object[]> findPagosByOpa(Integer idorigenp,Integer idproducto,Integer idauxiliar,Date fechaInicio,Date FechaFinal,Pageable page);
	
	
}
