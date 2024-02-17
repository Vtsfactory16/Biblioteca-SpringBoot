package presentador;


import modelo.dao.CategoriaDAO;
import modelo.dao.LibroDAO;
import modelo.http.CategoriaRequests;
import modelo.http.LibroRequests;

public class PresentadorLibro {
    private VistaLibro vistaLibro;
    private CategoriaDAO categoriaDAO;
    private LibroDAO libroDAO;

    public PresentadorLibro(VistaLibro vistaLibro, LibroDAO libroDAO, CategoriaDAO categoriaDAO) {
        this.vistaLibro = vistaLibro;
        this.libroDAO = libroDAO;
        this.categoriaDAO =  categoriaDAO;
    }

    public void borra() throws Exception {
        libroDAO.delete(vistaLibro.getLibro().getId());
    }

    public void inserta() throws Exception {
        libroDAO.insert(vistaLibro.getLibro());
    }

    public void modifica() throws Exception {
        libroDAO.update(vistaLibro.getLibro());
    }

    public void listaAllLibros() throws Exception {
        VistaLibros vistaLibros = (VistaLibros) vistaLibro;
        vistaLibros.setLibros(libroDAO.getAll());
    }

    public void listaAllCategorias() throws Exception {
        vistaLibro.setCategorias(categoriaDAO.getAll());
    }

    public void leerLibrosOR(int id, String titulo, String autor, String editorial, int categoria) throws Exception {
        VistaLibros vistaLibros = (VistaLibros) vistaLibro;
        vistaLibros.setLibros(libroDAO.getFiltered(id, titulo, autor, editorial, categoria));
    }
}