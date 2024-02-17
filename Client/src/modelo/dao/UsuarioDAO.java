package modelo.dao;

import modelo.Usuario;

import java.util.List;

/**
 * Esta será la intefaz para manejar los usuarios de la BD BIBLIOTECA
 * @author AGE
 * @version 2
 */
public interface UsuarioDAO {

    void insert(Usuario usuario) throws Exception;

    void update(Usuario usuario) throws Exception;

    void delete(int id) throws Exception;

    List<Usuario> getAll() throws Exception;

    List<Usuario> getFiltered(int id, String nombre, String apellidos) throws Exception;

    Usuario getById(int id) throws Exception;
}
