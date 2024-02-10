package presentador;

import presentador.http.HTTPRequests;

public class PresentadorUsuario {
    private VistaUsuario vistaUsuario;

    public PresentadorUsuario(VistaUsuario vistaUsuario) {
        this.vistaUsuario = vistaUsuario;
    }

    public void borra() throws Exception {
        // usuarioDAO.borrar(vistaUsuario.getUsuario().getId());
    }

    public void inserta() throws Exception {
        // usuarioDAO.insertar(vistaUsuario.getUsuario());
        HTTPRequests.postRequest(vistaUsuario.getUsuario().toJSON());
    }

    public void modifica() throws Exception {
        // usuarioDAO.modificar(vistaUsuario.getUsuario());
    }

    public void listaAllUsuarios() throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        // vistaUsuarios.setUsuarios(usuarioDAO.leerAllUsuarios());
    }

    public void leerUsuariosOR(int id,String nombre,String apellidos) throws Exception {
        VistaUsuarios vistaUsuarios = (VistaUsuarios) vistaUsuario;
        // vistaUsuarios.setUsuarios(usuarioDAO.leerUsuariosOR(id,nombre,apellidos));
    }

}
