package vista.helper;

import modelo.*;
import modelo.dao.CategoriaDAO;
import modelo.http.CategoriaRequests;
import presentador.PresentadorCategoria;
import vista.FichaCategoria;
import vista.ListaCategorias;

public class Categorias {

    private static CategoriaDAO categoriaDAO = new CategoriaRequests();

    public static ListaCategorias listaCategorias() throws Exception {
        ListaCategorias listaCategorias=new ListaCategorias();
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(listaCategorias,categoriaDAO);
        listaCategorias.setPresentador(presentadorCategoria);
        listaCategorias.lanzar();
        return listaCategorias;
    }

    public static FichaCategoria fichaCategoria(Categoria categoria) throws Exception {
        FichaCategoria fichaCategoria=new FichaCategoria(categoria);
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(fichaCategoria,categoriaDAO);
        fichaCategoria.setPresentador(presentadorCategoria);
        fichaCategoria.lanzar();
        return fichaCategoria;
    }
}
