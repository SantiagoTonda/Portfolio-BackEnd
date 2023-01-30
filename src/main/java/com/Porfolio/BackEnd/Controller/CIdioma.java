package com.Porfolio.BackEnd.Controller;

import com.Porfolio.BackEnd.Dto.dtoIdioma;
import com.Porfolio.BackEnd.Entity.Idioma;
import com.Porfolio.BackEnd.Security.Controller.Mensaje;
import com.Porfolio.BackEnd.Service.SIdioma;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/idioma")
@CrossOrigin(origins = {"https://frontendportfolio-f789c.web.app", "http://localhost:4200"})
public class CIdioma {
    @Autowired
    SIdioma sIdioma;

    @GetMapping("/lista")
    public ResponseEntity<List<Idioma>> list() {
        List<Idioma> list = sIdioma.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Idioma> getById(@PathVariable("id") int id) {
        if (!sIdioma.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Idioma idioma = sIdioma.getOne(id).get();
        return new ResponseEntity(idioma, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sIdioma.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sIdioma.delete(id);
        return new ResponseEntity(new Mensaje("Idioma eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoIdioma dtoidioma) {
        if (StringUtils.isBlank(dtoidioma.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sIdioma.existsByNombre(dtoidioma.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese idioma ya existe"), HttpStatus.BAD_REQUEST);
        }

        Idioma idioma = new Idioma(dtoidioma.getNombre(), dtoidioma.getNivel());
        sIdioma.save(idioma);

        return new ResponseEntity(new Mensaje("Idioma agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoIdioma dtoidioma) {
        //Validamos si existe el ID
        if (!sIdioma.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de experiencias
        if (sIdioma.existsByNombre(dtoidioma.getNombre()) && sIdioma.getByNombre(dtoidioma.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoidioma.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Idioma idioma = sIdioma.getOne(id).get();
        idioma.setNombre(dtoidioma.getNombre());
        idioma.setNivel((dtoidioma.getNivel()));

        sIdioma.save(idioma);
        return new ResponseEntity(new Mensaje("Idioma agregado"), HttpStatus.OK);

    }
}
