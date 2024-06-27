package com.api.rest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.apirest.Entities.Libro;
import com.api.rest.apirest.Repositories.LibroRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/libros") // Ruta de la API 
public class LibroController {
    
    @Autowired // Inyección de dependencias
    private LibroRepository libroRepository;
    
    @GetMapping
    public List<Libro> obtenerLibros(){          //devuelve lista de libros
        return libroRepository.findAll();
    }

    @GetMapping("/{id}")    //ruta para obtener un libro por id http://localhost:8080/libros/1 en postman
    public Libro obtenerLibro(@PathVariable Long id) {    //devuelve libro
        return libroRepository.findById(id).orElse(null);
    }

    @PostMapping("/crearLibro")   //ruta para crear un libro http://localhost:8080/libros/crearLibro en postman
    public Libro creaLibro(@RequestBody Libro libro) {     //devuelve libro
        return libroRepository.save(libro);    //guardamos en la base de datos y lo devolvemos
        
    }

    @PutMapping("/{id}")    //ruta para actualizar un libro por id http://localhost:8080/libros/1 en postman
    public Libro actualizarLibro(@PathVariable Long id, @RequestBody Libro detallesLibro) {
        Libro libro = libroRepository.findById(id).orElse(null);
        libro.setTitulo(detallesLibro.getTitulo());        //lamo al setter de titulo en la clase libro
        libro.setIsbn(detallesLibro.getIsbn());            //lamo al setter de isbn en la clase libro
        return libroRepository.save(libro);

    }

    @DeleteMapping("/{id}")
    public void borrarLibro(@PathVariable Long id) {
        try {
            libroRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error al borrar el libro: " + e.getMessage());
        }
    }
}
