package Entidad;

public class TipoUser {
    int idTipoUser;
    String descripciontu;

    public TipoUser() {
    }

    public int getIdTipoUser() {
        return idTipoUser;
    }

    public void setIdTipoUser(int idTipoUser) {
        this.idTipoUser = idTipoUser;
    }

    public String getDescripciontu() {
        return descripciontu;
    }

    public void setDescripciontu(String descripciontu) {
        this.descripciontu = descripciontu;
    }

    public TipoUser(int idTipoUser, String descripciontu) {
        this.idTipoUser = idTipoUser;
        this.descripciontu = descripciontu;
    }

}
