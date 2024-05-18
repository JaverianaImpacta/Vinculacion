package edu.javeriana.ingenieria.social.vinculacion.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vinculacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer codigo, estudiante, proyecto;
    @Column(name="tipo_vinculacion")
    private Integer tipoVinculacion;
    private String semestre;
    private Boolean certificado;
}
