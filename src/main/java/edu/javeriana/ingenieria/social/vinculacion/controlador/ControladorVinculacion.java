package edu.javeriana.ingenieria.social.vinculacion.controlador;

import edu.javeriana.ingenieria.social.vinculacion.dominio.Vinculacion;
import edu.javeriana.ingenieria.social.vinculacion.servicio.ServicioVinculacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vinculaciones")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorVinculacion {
    @Autowired
    private ServicioVinculacion servicio;

    @GetMapping("listar")
    public List<Vinculacion> obtenerVinculaciones(){
        return servicio.obtenerVinculaciones();
    }
    
    @GetMapping("obtener")
    public ResponseEntity<Vinculacion> obtenerVinculacion(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerVinculacion(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerVinculacion(id), HttpStatus.OK);
    }

    @GetMapping("obtenerCodigo")
    public ResponseEntity<Vinculacion> obtenerVinculacionPorCodigo(@RequestParam Integer codigo){
        if(codigo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!servicio.vinculacionExiste(codigo)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerVinculacionPorCodigo(codigo), HttpStatus.OK);
    }

    @GetMapping("obtenerProyecto")
    public ResponseEntity<List<Vinculacion>> obtenerVinculaciones(@RequestParam Integer proyecto){
        if(proyecto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerVinculaciones(proyecto).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerVinculaciones(proyecto), HttpStatus.OK);
    }

    @GetMapping("obtenerSemestre")
    public ResponseEntity<List<Vinculacion>> obtenerVinculaciones(@RequestParam String semestre){
        if(semestre == null || semestre.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerVinculaciones(semestre).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerVinculaciones(semestre), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Vinculacion> crearVinculacion(@RequestBody Vinculacion vinculacion){
        if(vinculacion == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.vinculacionExiste(vinculacion.getId()) || servicio.vinculacionExiste(vinculacion) || servicio.vinculacionExisteCodigo(vinculacion.getCodigo())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearVinculacion(vinculacion), HttpStatus.OK);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Vinculacion> actualizarVinculacion(@RequestParam Integer id, @RequestBody Vinculacion vinculacion){
        if(id == null || vinculacion == null || !id.equals(vinculacion.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarVinculacion(id,vinculacion) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vinculacion, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> borrarVinculacion(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarVinculacion(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
