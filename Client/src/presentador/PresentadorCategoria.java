package presentador;

import presentador.http.CategoriaRequests;

public class PresentadorCategoria {
    private VistaCategoria vistaCategoria;

    public PresentadorCategoria(VistaCategoria vistaCategoria) {
        this.vistaCategoria = vistaCategoria;
    }

    public void borra() throws Exception {
        CategoriaRequests.deleteCategoria(vistaCategoria.getCategoria());
    }


    //Probado y correcto
    public void inserta() throws Exception {
        CategoriaRequests.postCategoria(vistaCategoria.getCategoria());
    }

    public void modifica() throws Exception {
        CategoriaRequests.putCategoria(vistaCategoria.getCategoria());
    }

    public void listaAllCategorias() throws Exception {
        VistaCategorias vistaCategorias = (VistaCategorias) vistaCategoria;
        vistaCategorias.setCategorias(CategoriaRequests.getCategorias());
    }
}