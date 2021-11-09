
package models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 *
 * @author Katsu
 */

@Entity
@Table(name="pedidos")
public class Pedido implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    @Column(name="alumno")
    String alumno;
    @Column(name="ciclo")
    String ciclo;
    @Column(name="fecha")
    Date fecha;
    @Column(name="estado")
    String estado;
    @Column(name="producto_id")
    int prodId;
   // DATABASE HANDLER Long hora;

    public Pedido() {
    }
    public Pedido(int id) {
        this.id = id;
    }

    public Pedido(int id, String alumno, String ciclo, String estado, int prodId, Date fecha) {
        this.id = id;
        this.alumno = alumno;
        this.ciclo = ciclo;
        this.estado = estado;
        this.prodId = prodId;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", alumno=" + alumno + ", ciclo=" + ciclo + ", fecha=" + fecha + ", estado=" + estado + ", prodId=" + prodId + '}'+ "\n";
    }


    
}
