package com.Porfolio.BackEnd.Controller;

import com.Porfolio.BackEnd.Dto.dtoProyectos;
import com.Porfolio.BackEnd.Entity.Proyectos;
import com.Porfolio.BackEnd.Security.Controller.Mensaje;
import com.Porfolio.BackEnd.Service.SProyectos;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://frontendportfolio-f789c.web.app", "http://localhost:4200"})
public class CProyectos {

    @Autowired
    SProyectos sProyectos;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id) {
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sProyectos.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyectos dtoproyectos) {
        if (StringUtils.isBlank(dtoproyectos.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sProyectos.existsByNombre(dtoproyectos.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyectos = new Proyectos(dtoproyectos.getNombre(), dtoproyectos.getDescripcion(), dtoproyectos.getLink(), dtoproyectos.getImg());
        sProyectos.save(proyectos);

        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyectos dtoproyectos) {
        //Validamos si existe el ID
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de experiencias
        if (sProyectos.existsByNombre(dtoproyectos.getNombre()) && sProyectos.getByNombre(dtoproyectos.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoproyectos.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyectos = sProyectos.getOne(id).get();
        proyectos.setNombre(dtoproyectos.getNombre());
        proyectos.setDescripcion((dtoproyectos.getDescripcion()));
        proyectos.setLink(dtoproyectos.getLink());
        proyectos.setImg(dtoproyectos.getImg());

        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);

    }
}
