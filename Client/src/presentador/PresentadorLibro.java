package presentador;


import excepciones.CampoVacioExcepcion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PresentadorLibro {
    private VistaLibro vistaLibro;

    public PresentadorLibro(VistaLibro vistaLibro) {
        this.vistaLibro = vistaLibro;
    }

    public void borra() throws Exception {
        // libroDAO.borrar(vistaLibro.getLibro().getId());
        // TODO: Petición http DELETE de libro
    }

    public void inserta() throws Exception {
        // libroDAO.insertar(vistaLibro.getLibro());
        // TODO: Petición http POST de libro
    }

    public void modifica() throws Exception {
        // libroDAO.modificar(vistaLibro.getLibro());
        // TODO: Petición http PUT de libro
    }

    public void listaAllLibros() throws Exception {
        VistaLibros vistaLibros = (VistaLibros) vistaLibro;
        // vistaLibros.setLibros(libroDAO.leerAllLibros());
        // TODO: Petición http GET de libro
    }

    public void listaAllCategorias() {
       // vistaLibro.setCategorias(categoriaDAO.leerAllCategorias());
        // TODO: Petición http GET de libro
    }

    public void leerLibrosOR(int id, String titulo, String autor, String editorial, int categoria) throws Exception {
        // VistaLibros vistaLibros = (VistaLibros) vistaLibro;
        // vistaLibros.setLibros(libroDAO.leerLibrosOR(id, titulo, autor, editorial, categoria));
    }
}