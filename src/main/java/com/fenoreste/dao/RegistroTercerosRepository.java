package com.fenoreste.dao;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fenoreste.entity.productos_tercero_activacion;

public interface RegistroTercerosRepository extends CrudRepository<productos_tercero_activacion, Long>{

	@Query(value = " SELECT * FROM productos_terceros_activacion WHERE " + "idorigenpt= ?1 " + " AND   idproductot= ?2"
			+ " AND   idauxiliart= ?3 AND usuariobanca = ?4", nativeQuery = true)
	productos_tercero_activacion findTerceroByOpa(Integer idorigenp, Integer idproducto, Integer idauxiliar,String usuario);

	@Transactional
	@Modifying
	@Query(value = " INSERT INTO productos_terceros_activacion VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
	void save(Integer idorigenp, Integer idproducto, Integer idauxiliar,Timestamp date,String usuario);
     
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE productos_terceros_activacion SET fecharegistro =?1 WHERE idorigenpt=?2 AND idproductot=?3 AND idauxiliart=?4 AND usuariobanca=?5" , nativeQuery=true)
	void modificar(Timestamp fecha,Integer idorigenp,Integer idproducto,Integer idauxiliar,String usuario);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM productos_terceros_activacion WHERE idorigenpt=?1 AND idproductot=?2 AND idauxiliart=?3 AND usuariobanca = ?4 " , nativeQuery=true)
	void eliminar(Integer idorigenp,Integer idproducto,Integer idauxiliar,String usuario);
	
}
