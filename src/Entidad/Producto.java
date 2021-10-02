package Entidad;

public class Producto {
    int idpro;
    String codpro;
    String nompro;
    double prepro;
    int stock;
    int estpro;

    public Producto() {
    }

    public Producto(int idpro, String codpro, String nompro, double prepro, int stock, int estpro) {
        this.idpro = idpro;
        this.codpro = codpro;
        this.nompro = nompro;
        this.prepro = prepro;
        this.stock = stock;
        this.estpro = estpro;
    }

    public int getIdpro() {
        return idpro;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    public String getNompro() {
        return nompro;
    }

    public void setNompro(String nompro) {
        this.nompro = nompro;
    }

    public double getPrepro() {
        return prepro;
    }

    public void setPrepro(double prepro) {
        this.prepro = prepro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEstpro() {
        return estpro;
    }

    public void setEstpro(int estpro) {
        this.estpro = estpro;
    }

    
}
