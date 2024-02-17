package modelo.dao;

import modelo.Libro;

import java.util.List;

/**
 * Esta será la intefaz para manejar los libros de la BD BIBLIOTECA
 * @author AGE
 * @version 2
 */
public interface LibroDAO {

    void insert(Libro libro) throws Exception;

    void update(Libro libro) throws Exception;

    void delete(int id) throws Exception;

    Libro getById(int id) throws Exception;

    List<Libro> getAll() throws Exception;

    List<Libro> getFiltered(int id, String titulo, String autor, String editorial, int categoria) throws Exception;

}
