package presentador;

import modelo.dao.UsuarioDAO;
import modelo.http.UsuarioRequests;

public class PresentadorUsuario {
    private VistaUsuario vistaUsuario;
    private UsuarioDAO usuarioDAO;

    public PresentadorUsuario(VistaUsuario vistaUsuario, UsuarioDAO usuarioDAO) {
        this.vistaUsuario = vistaUsuario;
        this.usuarioDAO = usuarioDAO;
    }
    private final String ENDPOINT = "usuarios";

    public void borra() throws Exception {
        usuarioDAO.delete(vistaUsuario.getUsuario().getId());
    }

    public void inserta() throws Exception {
        usuarioDAO.insert(vistaUsuario.getUsuario());
    }

    public void modifica() throws Exception {
        usuarioDAO.update(vistaUsuario.getUsuario());
    }

    public void listaAllUsuarios() throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        vistaUsuarios.setUsuarios(usuarioDAO.getAll());
    }

    public void leerUsuariosOR(int id,String nombre,String apellidos) throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        vistaUsuarios.setUsuarios(usuarioDAO.getFiltered(id,nombre,apellidos));
    }

}
