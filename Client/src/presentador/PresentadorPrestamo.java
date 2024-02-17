package presentador;

import modelo.dao.CategoriaDAO;
import modelo.dao.PrestamoDAO;
import modelo.http.CategoriaRequests;
import modelo.http.PrestamoRequests;

public class PresentadorPrestamo {
    private VistaPrestamo vistaPrestamo;
    private CategoriaDAO categoriaDAO;
    private PrestamoDAO prestamoDAO;

    public PresentadorPrestamo(VistaPrestamo vistaPrestamo, PrestamoDAO prestamoDAO, CategoriaDAO categoriaDAO) {
        this.vistaPrestamo = vistaPrestamo;
        this.prestamoDAO = prestamoDAO;
        this.categoriaDAO = categoriaDAO;
    }

    public void borra() throws Exception {
        prestamoDAO.delete(vistaPrestamo.getPrestamo().getIdPrestamo());
        listaAllPrestamos();
    }

    public void inserta() throws Exception {
        prestamoDAO.insert(vistaPrestamo.getPrestamo());
        listaAllPrestamos();
    }

    public void modifica() throws Exception {
        prestamoDAO.update(vistaPrestamo.getPrestamo());
        listaAllPrestamos();
    }

    public void listaAllPrestamos() throws Exception {
        VistaPrestamos vistaPrestamos = (VistaPrestamos) vistaPrestamo;
        vistaPrestamos.setPrestamos(prestamoDAO.getAll());
    }

    public void listaAllCategorias() throws Exception {
        vistaPrestamo.setCategorias(categoriaDAO.getAll());
    }

}
