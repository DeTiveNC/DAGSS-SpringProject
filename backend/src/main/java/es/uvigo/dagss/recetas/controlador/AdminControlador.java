package es.uvigo.dagss.recetas.controlador;


import es.uvigo.dagss.recetas.servicios.AdministradorServicios;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/home/admins")
@Tag(name = "Admin Endpoint", description = "Admin Controlador")
public class AdminControlador {
    @Autowired
    private AdministradorServicios administradorServicios;
    
}
