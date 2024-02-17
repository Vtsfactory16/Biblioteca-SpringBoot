package vista.helper;


import modelo.dao.CategoriaDAO;
import modelo.dao.PrestamoDAO;
import modelo.http.CategoriaRequests;
import modelo.http.PrestamoRequests;
import presentador.PresentadorPrestamo;
import modelo.Prestamo;
import vista.FichaPrestamo;
import vista.ListaPrestamos;

public class Prestamos {

    private static PrestamoDAO prestamoDAO = new PrestamoRequests();
    private static CategoriaDAO categoriaDAO = new CategoriaRequests();

    public static ListaPrestamos listaPrestamos() throws Exception {
        ListaPrestamos listaPrestamos=new ListaPrestamos();
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(listaPrestamos, prestamoDAO, categoriaDAO);
        listaPrestamos.setPresentador(presentadorPrestamo);
        listaPrestamos.lanzar();
        return listaPrestamos;
    }

    public static FichaPrestamo fichaPrestamo(Prestamo prestamo) throws Exception {
        FichaPrestamo fichaPrestamo=new FichaPrestamo(prestamo);
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(fichaPrestamo, prestamoDAO, categoriaDAO);
        fichaPrestamo.setPresentador(presentadorPrestamo);
        fichaPrestamo.lanzar();
        return fichaPrestamo;
    }
}
