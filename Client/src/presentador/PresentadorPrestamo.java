package presentador;

import presentador.http.CategoriaRequests;
import presentador.http.PrestamoRequests;

public class PresentadorPrestamo {
    private VistaPrestamo vistaPrestamo;

    public PresentadorPrestamo(VistaPrestamo vistaPrestamo) {
        this.vistaPrestamo = vistaPrestamo;
    }

    public void borra() throws Exception {
        PrestamoRequests.deletePrestamo(vistaPrestamo.getPrestamo());
    }

    public void inserta() throws Exception {
        PrestamoRequests.postPrestamo(vistaPrestamo.getPrestamo());
    }

    public void modifica() throws Exception {
        PrestamoRequests.putPrestamo(vistaPrestamo.getPrestamo());
    }

    public void listaAllPrestamos() throws Exception {
        VistaPrestamos vistaPrestamos = (VistaPrestamos) vistaPrestamo;
        vistaPrestamos.setPrestamos(PrestamoRequests.getPrestamos());
    }

    public void listaAllCategorias() throws Exception {
        vistaPrestamo.setCategorias(CategoriaRequests.getCategorias());
    }

}
