/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.cuentavivencia.repositories;

import bo.gob.senasir.cuentavivencia.model.CuentaVivencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KSalazar
 */
@Repository
public interface CuentaVivenciaRepository extends JpaRepository<CuentaVivencia, Long> {

}
