package com.api.rest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.rest.apirest.Entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
    
}
