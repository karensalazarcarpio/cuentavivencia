/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.gob.senasir.seguridad.repositories;

import bo.gob.senasir.seguridad.model.Archivosadjuntos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KSalazar
 */
@Repository
public interface ArchivosadjuntosRepository extends JpaRepository<Archivosadjuntos, Long> {
    
}
