package edu.javeriana.ingenieria.social.vinculacion.repositorio;

import edu.javeriana.ingenieria.social.vinculacion.dominio.Vinculacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioVinculacion extends JpaRepository<Vinculacion, Integer> {
    List<Vinculacion> findAllBySemestre(String semestre);

    List<Vinculacion> findAllByProyecto(Integer proyecto);

    Vinculacion findOneByCodigo(Integer codigo);

    boolean existsByCodigo(Integer codigo);
}
