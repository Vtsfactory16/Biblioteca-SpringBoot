package presentador;

import helper.Table;
import modelo.dao.UsuarioDAO;
import modelo.http.UsuarioRequests;
import vista.FormMain;

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
        notifyForms();
    }

    public void inserta() throws Exception {
        usuarioDAO.insert(vistaUsuario.getUsuario());
        notifyForms();
    }

    public void modifica() throws Exception {
        usuarioDAO.update(vistaUsuario.getUsuario());
        notifyForms();
    }

    public void listaAllUsuarios() throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        vistaUsuarios.setUsuarios(usuarioDAO.getAll());
    }

    public void leerUsuariosOR(int id,String nombre,String apellidos) throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        vistaUsuarios.setUsuarios(usuarioDAO.getFiltered(id,nombre,apellidos));
    }

    private void notifyForms() throws Exception {
        FormMain.getInstance().updateForms(Table.USUARIOS);
    }

}
