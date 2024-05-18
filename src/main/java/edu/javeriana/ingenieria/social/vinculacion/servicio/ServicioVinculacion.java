package edu.javeriana.ingenieria.social.vinculacion.servicio;

import edu.javeriana.ingenieria.social.vinculacion.dominio.Vinculacion;
import edu.javeriana.ingenieria.social.vinculacion.repositorio.RepositorioVinculacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class ServicioVinculacion {
    @Autowired
    private RepositorioVinculacion repositorio;

    public List<Vinculacion> obtenerVinculaciones(){
        return repositorio.findAll();
    }

    public List<Vinculacion> obtenerVinculaciones(String semestre){
        return repositorio.findAllBySemestre(semestre);
    }

    public List<Vinculacion> obtenerVinculaciones(Integer proyecto){
        return repositorio.findAllByProyecto(proyecto);
    }

    public Vinculacion obtenerVinculacion(Integer id){
        return repositorio.findById(id).orElse(null);
    }

    public Vinculacion obtenerVinculacionPorCodigo(Integer codigo){
        return repositorio.findOneByCodigo(codigo);
    }

    public Vinculacion crearVinculacion(Vinculacion vinculacion){
        return repositorio.save(vinculacion);
    }

    public Vinculacion actualizarVinculacion(Integer id, Vinculacion vinculacion){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        vinculacion.setId(id);
        return repositorio.save(vinculacion);
    }

    public Vinculacion borrarVinculacion(Integer id){
        Vinculacion aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return aux;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean vinculacionExiste(Integer id){
        return repositorio.existsById(id);
    }

    public boolean vinculacionExisteCodigo(Integer codigo){
        return repositorio.existsByCodigo(codigo);
    }

    public boolean vinculacionExiste(Vinculacion vinculacion){
        List<Vinculacion> aux = obtenerVinculaciones(vinculacion.getSemestre());
        for(Vinculacion v : aux){
            if(Objects.equals(v.getEstudiante(), vinculacion.getEstudiante()) && Objects.equals(v.getProyecto(), vinculacion.getProyecto()) && Objects.equals(v.getTipoVinculacion(), vinculacion.getTipoVinculacion())){
                return true;
            }
        }
        return false;
    }

}
