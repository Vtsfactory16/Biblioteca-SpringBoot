package presentador;

import presentador.http.HTTPRequests;

public class PresentadorCategoria {
    private VistaCategoria vistaCategoria;

    public PresentadorCategoria(VistaCategoria vistaCategoria) {
        this.vistaCategoria = vistaCategoria;
    }

    public void borra() throws Exception {

        // categoriaDAO.borrar(vistaCategoria.getCategoria().getId());
        // TODO: Petición http DELETE de categoria
    }


    //Probado y correcto
    public void inserta() throws Exception {
    HTTPRequests.postRequest(vistaCategoria.getCategoria().toJson(), "categorias");
        // categoriaDAO.inserta(vistaCategoria.getCategoria());
        // TODO: Petición http POST de categoria
    }

    public void modifica() throws Exception {
        // categoriaDAO.modificar(vistaCategoria.getCategoria());
        // TODO: Petición http POST de categoria
    }

    public void listaAllCategorias() throws Exception {

        // VistaCategorias vistaCategorias = (VistaCategorias) vistaCategoria;
        // vistaCategorias.setCategorias(categoriaDAO.leerAllCategorias());
        // TODO: Petición http GET de categoria
    }
}