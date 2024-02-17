package presentador;

import modelo.dao.CategoriaDAO;
import modelo.http.CategoriaRequests;

public class PresentadorCategoria {

    private VistaCategorias vistaCategorias;
    private CategoriaDAO categoriaDAO;

    public PresentadorCategoria(VistaCategorias vistaCategorias, CategoriaDAO categoriaDAO) {
        this.vistaCategorias = vistaCategorias;
        this.categoriaDAO = categoriaDAO;

    }

    public void borra() throws Exception {
        categoriaDAO.delete(vistaCategorias.getCategoria().getId());
        listaAllCategorias();


    }


    //Probado y correcto
    public void inserta() throws Exception {
        categoriaDAO.insert(vistaCategorias.getCategoria());
        listaAllCategorias();

    }

    public void modifica() throws Exception {
        categoriaDAO.update(vistaCategorias.getCategoria());
        listaAllCategorias();

    }

    public void listaAllCategorias() throws Exception {

        VistaCategorias vistaCategorias = this.vistaCategorias;
        vistaCategorias.setCategorias(categoriaDAO.getAll());
    }
}