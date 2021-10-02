package Entidad;

public class Cliente {
    int idcli;
    String dni;
    String nombrecli;
    String apellidocli;
    String telefonocli;
    String direccion;
    int estadocli;

    public Cliente(int idcli, String dni, String nombrecli, String apellidocli, String telefonocli, String direccion, int estadocli) {
        this.idcli = idcli;
        this.dni = dni;
        this.nombrecli = nombrecli;
        this.apellidocli = apellidocli;
        this.telefonocli = telefonocli;
        this.direccion = direccion;
        this.estadocli = estadocli;
    }

    public Cliente() {
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombrecli() {
        return nombrecli;
    }

    public void setNombrecli(String nombrecli) {
        this.nombrecli = nombrecli;
    }

    public String getApellidocli() {
        return apellidocli;
    }

    public void setApellidocli(String apellidocli) {
        this.apellidocli = apellidocli;
    }

    public String getTelefonocli() {
        return telefonocli;
    }

    public void setTelefonocli(String telefonocli) {
        this.telefonocli = telefonocli;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstadocli() {
        return estadocli;
    }

    public void setEstadocli(int estadocli) {
        this.estadocli = estadocli;
    }
}
