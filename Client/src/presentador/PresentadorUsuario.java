package presentador;

import presentador.http.UsuarioRequests;

public class PresentadorUsuario {
    private VistaUsuario vistaUsuario;

    public PresentadorUsuario(VistaUsuario vistaUsuario) {
        this.vistaUsuario = vistaUsuario;
    }
    private final String ENDPOINT = "usuarios";

    public void borra() throws Exception {
        UsuarioRequests.deleteUser(vistaUsuario.getUsuario());
    }

    public void inserta() throws Exception {
        UsuarioRequests.postUser(vistaUsuario.getUsuario());
    }

    public void modifica() throws Exception {
        UsuarioRequests.putUser(vistaUsuario.getUsuario());
    }

    public void listaAllUsuarios() throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        vistaUsuarios.setUsuarios(UsuarioRequests.getUsers());
    }

    public void leerUsuariosOR(int id,String nombre,String apellidos) throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        // vistaUsuarios.setUsuarios(usuarioDAO.leerUsuariosOR(id,nombre,apellidos));
    }

}
