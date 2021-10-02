package Entidad;

public class User {
    int idUser;
    String nombre;
    String apellido;
    String telefono;
    String username;
    String pass;
    int estadou;
    int tipouser;

    public User() {
    }

    public User(int idUser, String nombre, String apellido, String telefono, String username, String pass, int estadou, int tipouser) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.username = username;
        this.pass = pass;
        this.estadou = estadou;
        this.tipouser = tipouser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getEstadou() {
        return estadou;
    }

    public void setEstadou(int estadou) {
        this.estadou = estadou;
    }

    public int getTipouser() {
        return tipouser;
    }

    public void setTipouser(int tipouser) {
        this.tipouser = tipouser;
    }
    
}
