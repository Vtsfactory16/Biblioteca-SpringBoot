package vista.helper;

import modelo.BusquedaLibro;
import modelo.Libro;
import modelo.dao.CategoriaDAO;
import modelo.dao.LibroDAO;
import modelo.http.CategoriaRequests;
import modelo.http.LibroRequests;
import presentador.PresentadorLibro;
import vista.FichaLibro;
import vista.ListaLibros;
import vista.SeleccionaLibro;

import java.awt.*;

public class Libros {

    private static CategoriaDAO categoriaDAO = new CategoriaRequests();
    private static LibroDAO libroDAO = new LibroRequests();

    public static ListaLibros listaLibros() throws Exception {
        ListaLibros listaLibros=new ListaLibros();
        PresentadorLibro presentadorLibro=new PresentadorLibro(listaLibros, libroDAO,categoriaDAO);
        listaLibros.setPresentador(presentadorLibro);
        listaLibros.lanzar();
        return listaLibros;
    }

    public static SeleccionaLibro seleccionaLibro(Frame owner, String title, boolean modal, BusquedaLibro busquedaLibro) throws Exception {
        SeleccionaLibro seleccionaLibro=new SeleccionaLibro(owner,title,modal,busquedaLibro);
        PresentadorLibro presentadorLibro=new PresentadorLibro(seleccionaLibro, libroDAO, categoriaDAO);
        seleccionaLibro.setPresentador(presentadorLibro);
        seleccionaLibro.lanzar();
        return seleccionaLibro;
    }

    public static FichaLibro fichaLibro(Libro libro) throws Exception {
        FichaLibro fichaLibro=new FichaLibro(libro);
        PresentadorLibro presentadorLibro=new PresentadorLibro(fichaLibro, libroDAO, categoriaDAO);
        fichaLibro.setPresentador(presentadorLibro);
        fichaLibro.lanzar();
        return fichaLibro;
    }
}
