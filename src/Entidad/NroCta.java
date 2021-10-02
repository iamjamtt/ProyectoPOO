package Entidad;

public class NroCta {
    int idnrocta;
    String codigocta;
    double montocta;
    int estadocta;
    int idcli;

    public NroCta() {
    }

    public NroCta(int idnrocta, String codigocta, double montocta, int estadocta, int idcli) {
        this.idnrocta = idnrocta;
        this.codigocta = codigocta;
        this.montocta = montocta;
        this.estadocta = estadocta;
        this.idcli = idcli;
    }

    public int getIdnrocta() {
        return idnrocta;
    }

    public void setIdnrocta(int idnrocta) {
        this.idnrocta = idnrocta;
    }

    public String getCodigocta() {
        return codigocta;
    }

    public void setCodigocta(String codigocta) {
        this.codigocta = codigocta;
    }

    public double getMontocta() {
        return montocta;
    }

    public void setMontocta(double montocta) {
        this.montocta = montocta;
    }

    public int getEstadocta() {
        return estadocta;
    }

    public void setEstadocta(int estadocta) {
        this.estadocta = estadocta;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }
    
    
}
