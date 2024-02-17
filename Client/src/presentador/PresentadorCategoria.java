package presentador;

import modelo.dao.CategoriaDAO;
import modelo.http.CategoriaRequests;

public class PresentadorCategoria {
    private VistaCategoria vistaCategoria;
    private CategoriaDAO categoriaDAO;

    public PresentadorCategoria(VistaCategoria vistaCategoria, CategoriaDAO categoriaDAO) {
        this.vistaCategoria = vistaCategoria;
        this.categoriaDAO = categoriaDAO;
    }

    public void borra() throws Exception {
        categoriaDAO.delete(vistaCategoria.getCategoria().getId());
    }


    //Probado y correcto
    public void inserta() throws Exception {
        categoriaDAO.insert(vistaCategoria.getCategoria());
    }

    public void modifica() throws Exception {
        categoriaDAO.update(vistaCategoria.getCategoria());
    }

    public void listaAllCategorias() throws Exception {
        VistaCategorias vistaCategorias = (VistaCategorias) vistaCategoria;
        vistaCategorias.setCategorias(categoriaDAO.getAll());
    }
}