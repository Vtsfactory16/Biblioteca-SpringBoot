package modelo.dao;

import modelo.Categoria;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Esta será la intefaz para manejar las categorias de la BD BIBLIOTECA
 * @author AGE
 * @version 2
 */
public interface CategoriaDAO {

    void insert(Categoria categoria) throws Exception;

    void update(Categoria categoria) throws Exception;

    void delete(int id) throws Exception;


    Categoria getById(int id) throws Exception;

    List<Categoria> getAll() throws Exception;


}
