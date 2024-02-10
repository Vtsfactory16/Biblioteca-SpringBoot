package presentador;

public class PresentadorPrestamo {
    private VistaPrestamo vistaPrestamo;

    public PresentadorPrestamo(VistaPrestamo vistaPrestamo) {
        this.vistaPrestamo = vistaPrestamo;
    }

    public void borra() throws Exception {
        // prestamoDAO.borrar(vistaPrestamo.getPrestamo().getId());
        // TODO: Petición http DELETE de prestamo
    }

    public void inserta() throws Exception {
        // prestamoDAO.insertar(vistaPrestamo.getPrestamo());
        // TODO: Petición http POST de prestamo
    }

    public void modifica() throws Exception {
        // prestamoDAO.modificar(vistaPrestamo.getPrestamo());
        // TODO: Petición http PUT de prestamo
    }

    public void listaAllPrestamos() throws Exception {
        // VistaPrestamos vistaPrestamos = (VistaPrestamos) vistaPrestamo;
        // vistaPrestamos.setPrestamos(prestamoDAO.leerAllPrestamos());
        // TODO: Petición http GET de prestamo
    }

    public void listaAllCategorias() {
        // vistaPrestamo.setCategorias(categoriaDAO.leerAllCategorias());
        // TODO: Petición http GET de prestamo
    }

}
