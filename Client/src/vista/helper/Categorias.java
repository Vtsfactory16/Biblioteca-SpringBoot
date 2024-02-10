package vista.helper;

import modelo.*;
import presentador.PresentadorCategoria;
import vista.FichaCategoria;
import vista.ListaCategorias;

public class Categorias {

    public static ListaCategorias listaCategorias() throws Exception {
        ListaCategorias listaCategorias=new ListaCategorias();
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(listaCategorias);
        listaCategorias.setPresentador(presentadorCategoria);
        listaCategorias.lanzar();
        return listaCategorias;
    }

    public static FichaCategoria fichaCategoria(Categoria categoria) throws Exception {
        FichaCategoria fichaCategoria=new FichaCategoria(categoria);
        PresentadorCategoria presentadorCategoria=new PresentadorCategoria(fichaCategoria);
        fichaCategoria.setPresentador(presentadorCategoria);
        fichaCategoria.lanzar();
        return fichaCategoria;
    }
}
