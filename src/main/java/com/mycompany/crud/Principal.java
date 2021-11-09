
package com.mycompany.crud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import javax.persistence.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Katsu
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
        private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
        private static Session s = sf.openSession();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do{
            System.out.println("Elija accion a realizar: \n" + "Crear pedido: 1\n"
            + "Eliminar pedido: 2\n" + "Marcar pedido como recogido: 3 \n"
            +"Listar todas las comandas pendientes: 4\n"
            +"Listar la carta: 5\n"
            +"Salir: 6\n");
            opcion = sc.nextInt();
            switch (opcion){
            
                case 1: createPedido();
                break;
                case 2: eliminarPedido();
                break;
                case 3: marcarPedido();
                break;
                case 4: listarComandas();
                break;
                case 5: listarCarta();
                break;
                case 6:
                break;
                default: opcion = 0; System.out.println("Seleccione una opcion.");
            }
        }while(opcion != 6);
    }
    
    public static void createPedido(){
            String nombre;
            String ciclo;
            String estado;
            Scanner sc = new Scanner(System.in);
            System.out.println("Inserte su nombre: ");
            nombre = sc.next();
            System.out.println("Inserte su ciclo: ");
            ciclo = sc.next();
            System.out.println("Inserte el estado: ");
            estado = sc.next();
            System.out.println("Inserte el id del producto: ");
            int idProd = sc.nextInt();
            
            java.util.Date ahora = new java.util.Date();
            java.sql.Date fecha = new java.sql.Date(ahora.getTime());
            
            Pedido ped = new Pedido(0, nombre, ciclo, estado, idProd, fecha);
            Transaction t = s.beginTransaction();
            s.save(ped);
            t.commit();
    }
    
    public static void eliminarPedido(){
        
        int pedido;
        int res = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte el id del pedido a eliminar: ");
        pedido = sc.nextInt();
        Serializable id = pedido;
        
        Transaction t = s.beginTransaction();
        Pedido ped = s.load(Pedido.class, id);
        s.remove(ped);
        t.commit();
    }
    
    public static void marcarPedido(){
       
        Scanner sc = new Scanner(System.in);
        
        listarComandas();
        System.out.println("Inserte id del pedido a actualizar: ");
        int id = sc.nextInt();

        Transaction t = s.beginTransaction();
        Pedido pedido = s.load(Pedido.class, id);
        pedido.setEstado("Recogido");
        s.update(pedido);
        t.commit();
        
    }

    public static void listarComandas(){
        
        Query q = s.createQuery("FROM Pedido WHERE estado = 'Pendiente'", Pedido.class);
        
        ArrayList<Pedido> qres = (ArrayList<Pedido>) q.getResultList();
        System.out.println(qres.toString());

    }
    
    public static void listarCarta(){
    
        Query q = s.createQuery("FROM Producto", Producto.class);
        
        ArrayList<Producto> qres = (ArrayList<Producto>) q.getResultList();
        System.out.println(qres.toString());

    }
}
