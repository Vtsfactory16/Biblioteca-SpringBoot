package presentador;

import helper.Table;
import modelo.dao.CategoriaDAO;
import modelo.http.CategoriaRequests;
import vista.FormMain;

public class PresentadorCategoria {
    private VistaCategoria vistaCategoria;
    private CategoriaDAO categoriaDAO;

    public PresentadorCategoria(VistaCategoria vistaCategoria, CategoriaDAO categoriaDAO) {
        this.vistaCategoria = vistaCategoria;
        this.categoriaDAO = categoriaDAO;
    }

    public void borra() throws Exception {
        categoriaDAO.delete(vistaCategoria.getCategoria().getId());
        notifyForms();
    }


    //Probado y correcto
    public void inserta() throws Exception {
        categoriaDAO.insert(vistaCategoria.getCategoria());
        notifyForms();
    }

    public void modifica() throws Exception {
        categoriaDAO.update(vistaCategoria.getCategoria());
        notifyForms();
    }

    /**
     * 1. recuperar las categorias de la bd
     */
    public void listaAllCategorias() throws Exception {
        VistaCategorias vistaCategorias = (VistaCategorias) vistaCategoria;
        vistaCategorias.setCategorias(categoriaDAO.getAll());
    }

    private void notifyForms() throws Exception {
        FormMain.getInstance().updateForms(Table.CATEGORIAS);
    }
}