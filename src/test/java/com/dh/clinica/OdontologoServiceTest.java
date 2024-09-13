package com.dh.clinica;

import com.dh.clinica.dao.impl.OdontologoDaoH2;
import com.dh.clinica.db.H2Connection;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    //inicializar
    public static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    static final OdontologoService odontologoService= new OdontologoService(new OdontologoDaoH2());
    @BeforeAll
    static void tablas(){
        H2Connection.cargarTablas();
    }

    @Test
    @DisplayName("Testear que un odontologo se guarde en la DB")
    void caso1(){
        Odontologo odontologo1 = new Odontologo(202003, "PEPITO", "GONZALES");
        Odontologo odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo1);

        assertNotNull(odontologoDesdeDB.getId());
    }


    @Test
    @DisplayName("Testear la salida de la lista de odontologos")
    void caso2(){
        List<Odontologo> odontologos = new ArrayList<>();

        odontologos = odontologoService.listar();

        assertFalse(odontologos.isEmpty());
    }

    @Test
    @DisplayName("Testear que un odontologo pueda ser obtenido cuando se envia el id")
    void caso3(){
        //dado
        Integer id = 1;
        // cuando
        Odontologo odontologo = odontologoService.buscarPorId(id);
        // entonces
        assertEquals(id, odontologo.getId());
    }


}