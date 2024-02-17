package modelo.dao;

import modelo.Prestamo;

import java.util.List;

/**
 * Esta será la intefaz para manejar los préstamos de la BD BIBLIOTECA
 * @author AGE
 * @version 2
 */
public interface PrestamoDAO {

    void insert(Prestamo prestamo) throws Exception;

    void update(Prestamo prestamo) throws Exception;

    void delete(int id) throws Exception;

    List<Prestamo> getAll() throws Exception;

    Prestamo getById(int id) throws Exception;
}
