package com.Porfolio.BackEnd.Repository;

import com.Porfolio.BackEnd.Entity.Idioma;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RIdioma extends JpaRepository<Idioma, Integer>{
    Optional<Idioma> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
