package com.dh.clinica.repository;

import com.dh.clinica.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//le pasamos el tipo de dato que va manejar y luego el tipo de dato de la clave priincipal
public interface IDomicilioRepository extends JpaRepository<Domicilio, Integer> {

}
