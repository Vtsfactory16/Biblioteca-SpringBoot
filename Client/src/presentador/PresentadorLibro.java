package presentador;


import excepciones.CampoVacioExcepcion;
import modelo.Libro;
import presentador.http.CategoriaRequests;
import presentador.http.LibroRequests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PresentadorLibro {
    private VistaLibro vistaLibro;

    public PresentadorLibro(VistaLibro vistaLibro) {
        this.vistaLibro = vistaLibro;
    }

    public void borra() throws Exception {
        LibroRequests.deleteLibro(vistaLibro.getLibro());
    }

    public void inserta() throws Exception {
        LibroRequests.postLibro(vistaLibro.getLibro());
    }

    public void modifica() throws Exception {
        LibroRequests.putLibro(vistaLibro.getLibro());
    }

    public void listaAllLibros() throws Exception {
        VistaLibros vistaLibros = (VistaLibros) vistaLibro;
        vistaLibros.setLibros(LibroRequests.getLibros());
    }

    public void listaAllCategorias() throws Exception {
        vistaLibro.setCategorias(CategoriaRequests.getCategorias());
    }

    public void leerLibrosOR(int id, String titulo, String autor, String editorial, int categoria) throws Exception {
        // VistaLibros vistaLibros = (VistaLibros) vistaLibro;
        // vistaLibros.setLibros(libroDAO.leerLibrosOR(id, titulo, autor, editorial, categoria));
    }
}