package modelo;


import java.sql.Timestamp;
import java.util.Objects;

public class Historico {
    private int idHistorico;
    private String user;
    private Timestamp fecha;
    private String info;

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Historico that = (Historico) o;
        return idHistorico == that.idHistorico && Objects.equals(user, that.user) && Objects.equals(fecha, that.fecha) && Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorico, user, fecha, info);
    }
}
