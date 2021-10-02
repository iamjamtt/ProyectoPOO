package Entidad;

public class Detalle {
    int iddet;
    int idpro;
    int idven;
    int cantdet;
    double preciodet;

    public Detalle() {
    }

    public Detalle(int iddet, int idpro, int idven, int cantdet, double preciodet) {
        this.iddet = iddet;
        this.idpro = idpro;
        this.idven = idven;
        this.cantdet = cantdet;
        this.preciodet = preciodet;
    }

    public int getIddet() {
        return iddet;
    }

    public void setIddet(int iddet) {
        this.iddet = iddet;
    }

    public int getIdpro() {
        return idpro;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public int getIdven() {
        return idven;
    }

    public void setIdven(int idven) {
        this.idven = idven;
    }

    public int getCantdet() {
        return cantdet;
    }

    public void setCantdet(int cantdet) {
        this.cantdet = cantdet;
    }

    public double getPreciodet() {
        return preciodet;
    }

    public void setPreciodet(double preciodet) {
        this.preciodet = preciodet;
    }
    
    
}
