package com.Porfolio.BackEnd.Service;

import com.Porfolio.BackEnd.Entity.Idioma;
import com.Porfolio.BackEnd.Repository.RIdioma;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SIdioma {
    @Autowired
    RIdioma rIdioma;
    
    public List<Idioma> list() {
        return rIdioma.findAll();
    }
    
    public Optional<Idioma> getOne (int id) {
        return rIdioma.findById(id);
    }
    
    public Optional<Idioma> getByNombre(String nombre) {
        return rIdioma.findByNombre(nombre);
    }
    
    public void save(Idioma idioma) {
        rIdioma.save(idioma);
    }
    
    public void delete(int id) {
        rIdioma.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return rIdioma.existsById(id);
    }
    
    public boolean existsByNombre(String nombre) {
        return rIdioma.existsByNombre(nombre);
    }
}
