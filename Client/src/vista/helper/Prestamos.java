package vista.helper;


import presentador.PresentadorPrestamo;
import modelo.Prestamo;
import vista.FichaPrestamo;
import vista.ListaPrestamos;

public class Prestamos {
    public static ListaPrestamos listaPrestamos() throws Exception {
        ListaPrestamos listaPrestamos=new ListaPrestamos();
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(listaPrestamos);
        listaPrestamos.setPresentador(presentadorPrestamo);
        listaPrestamos.lanzar();
        return listaPrestamos;
    }

    public static FichaPrestamo fichaPrestamo(Prestamo prestamo) throws Exception {
        FichaPrestamo fichaPrestamo=new FichaPrestamo(prestamo);
        PresentadorPrestamo presentadorPrestamo=new PresentadorPrestamo(fichaPrestamo);
        fichaPrestamo.setPresentador(presentadorPrestamo);
        fichaPrestamo.lanzar();
        return fichaPrestamo;

    }
}
