/*
*PROGRAMA QUE REALIZA LA RESTA DE DOS CADENAS CON UN LENGUJE T={ 0,B,- } DE '0' SEPARADAS 
*CON '-' POR MEDIO DE UNA MAQUINA DE TURING
*LA PRIMER CADENA COMIENZA CON 'B' Y LA SEGUNDA CADENA TERMINA EN 'B'
*
 */
package larestamt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @authores
 * RODRIGUEZ PEREZ LAURA JANNET
 * VAZQUEZ RUELAS MARCO ANTONIO
 * 
 */
public class LARestaMT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Rest resta = new Rest();
        resta.CadenaResta();
    }
}

class Rest{
    int cont=0;
    int estado=0;
    String car;
    String resultado;
    int Config;
    
    String cad="";
    String caracter;
    List <String> list = new ArrayList();
    
    public void CadenaResta()
    {
        //Concatenamos 'B' al inicio y al final de la cadena
        cad="B";
        cad+=JOptionPane.showInputDialog(null,"Ingresa la resta, T={0,-,B}:");
        cad+="B";
        //Se inicializa en estado 0
        setEstado(0);
        setResultado("");
        
        //Se llena la lista con la cadena
        for(int i=0;i<cad.length();i++){
            caracter=Character.toString(cad.charAt(i));
            list.add(caracter);
        }
        //Cuando en la MT no se encuentre el estado final no sale
        while(getEstado()!=7){
            //Ciclo para repasar la cadena de derecha a izquierda
            for (int x=0;x<list.size();x++){
            setCar(list.get(x));
                //Condiciones de estados a la derecha
                switch(getEstado()){
                  case 0:
                      if(getCar().equals("B")){
                          setEstado(1);
                          imprimirCadena(1,x);
                      }
                      break;
                  case 1:
                      if(getCar().equals("0")){
                          setEstado(1);
                          imprimirCadena(2,x);
                      }else if(getCar().equals("$")){
                          setEstado(1);
                          imprimirCadena(3,x);
                      }else if(getCar().equals("-")){
                          setEstado(1);
                          imprimirCadena(4,x);
                      }else if(getCar().equals("B")){
                          setEstado(2);
                          imprimirCadena(5,x);
                      }
                      break;
                  case 5: 
                      if(getCar().equals("B")){
                          setEstado(1);
                          imprimirCadena(16,x);
                      }
                      break;   
                }
            }
            
            //Ciclo para repasar la cadena de derecha a izquierda
            for (int y=list.size()-2;y>=0;y--){
                     setCar(list.get(y));
                //Condiciones para estados a la izquierda
                switch(getEstado()){
                    case 2:
                        if(getCar().equals("B")){
                            imprimirCadena(6,y);
                            System.out.println("\n\nSE ENCONTRO UNA CADENA TOTALMENTE VACIA!!");
                            System.out.println(imprimirBlanco());
                            System.exit(0);
                        }else if(getCar().equals("0")){
                            setEstado(3);
                            list.remove(y);
                            list.add(y,"$");
                            imprimirCadena(7,y);
                        }else if(getCar().equals("$")){
                            setEstado(2);
                            imprimirCadena(8,y);
                        }else if(getCar().equals("-")){
                            setEstado(6);
                            imprimirCadena(9,y);
                        }
                        break;
                    case 3:
                        if(getCar().equals("0")){
                            setEstado(3);
                            imprimirCadena(10,y);
                        }else if(getCar().equals("-")){
                            setEstado(4);
                            imprimirCadena(11,y);
                        }
                        break;
                    case 4:
                        if(getCar().equals("B")){
                            imprimirCadena(12,y);
                            System.out.println("\n\n\nSE PRESENTO UN ERROR M<N!!");
                            System.out.println(cadena());
                            System.out.println(imprimirBlanco());
                            System.exit(0);
                        }else if(getCar().equals("$")){
                            setEstado(4);
                            imprimirCadena(13,y);
                        }else if(getCar().equals("0")){
                            list.remove(y);
                            list.add(y,"$");
                            setEstado(5);
                            imprimirCadena(14,y);
                        }
                        break;
                    case 5:
                        if(getCar().equals("0")){
                            setEstado(5);
                            imprimirCadena(15,y);
                        }
                        break;
                    case 6:
                        if(getCar().equals("B")){
                            setEstado(7);
                            imprimirCadena(17,y);
                            System.out.println("\n\n\nFINALIZO CORRECTAMENTE LA CADENA!!");
                            System.out.println(cadena());
                                if(getResultado().equals("")){
                                    System.out.println(imprimirBlanco());
                                }else{
                                    System.out.println("RESULTADO FINAL: "+getResultado());
                                }
                        }else if(getCar().equals("0")){
                            setResultado(getResultado()+"0");
                            setEstado(6);
                            imprimirCadena(18,y);
                        }else if(getCar().equals("$")){
                            setEstado(6);
                            imprimirCadena(19,y);
                        }
                        break;
                }
            }
        }
        
        
    }
    
    /*
    *Se imprimen las configuraciones obteniendo de parametros
    *Funcion de movimiento (int config) y posicion en la lista (int numero) 
    */
    public void imprimirCadena(int config,int numero){
        String imp="";
        
        imp+=config+"|--(q"+getEstado()+", ";
        Iterator iter = list.iterator();
        while (iter.hasNext()){
            imp+=iter.next();
        }
        imp+=", "+numero+")";
        System.out.print("    "+imp);
        cont++;
        if(cont==4){
            System.out.print("\n");
            cont=0;
        }
    }
    //Imprimir la cadena inicial que se ingreso
    public String cadena(){
        String imp="CADENA: ";
        for(int i=0;i<cad.length();i++){
            imp+=cad.charAt(i);
        }
        return imp;
    }
    //Imprimir cuando el resultado es todos blancos
    public String imprimirBlanco(){
        String imp="RESULTADO FINAL: ";
        for(int i=0;i<list.size();i++){
            imp+="B";
        }
        return imp;
    }
    //Getter & Setter para caracteres
    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
    //Getter & Setter para estado
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    //Getter & Setter para resultado
    public String getResultado() {
        return resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
