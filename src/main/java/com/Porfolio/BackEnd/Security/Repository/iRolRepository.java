package com.Porfolio.BackEnd.Security.Repository;

import com.Porfolio.BackEnd.Security.Entity.Rol;
import com.Porfolio.BackEnd.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
