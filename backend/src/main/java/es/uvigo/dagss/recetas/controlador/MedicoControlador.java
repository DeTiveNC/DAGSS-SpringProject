package es.uvigo.dagss.recetas.controlador;

import es.uvigo.dagss.recetas.servicios.MedicoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home/medicos")
public class MedicoControlador {
    @Autowired
    private MedicoServicios medicoServicios;
}
