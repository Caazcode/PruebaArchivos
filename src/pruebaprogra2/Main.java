
package pruebaprogra2;


 
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
 
public class Main {
    static Scanner lea = new Scanner(System.in);
    static MyFile mf = new MyFile();
 
    public static void main(String[] args) {
        int option = 0;
 
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1- Set File");
            System.out.println("2- Info");
            System.out.println("3- Crear archivo");
            System.out.println("4- Crear folder");
            System.out.println("5- Borrar directorio");
            System.out.println("6- Arbol");
             System.out.println("7- Salir");
            System.out.print("Elija una opción: ");
 
            try {
                option = lea.nextInt();
 
                switch (option) {
                    case 1:
                        set();
                        break;
                    case 2:
                        mf.info();
                        break;
                    case 3:
                        mf.crearArchivo();
                        break;
                    case 4:
                        mf.crearFolder();
                        break;
                    case 5:
                        mf.borrarDirectorio();
                        break;
                    case 7:
                        System.out.println("Saliendo...");
                        break;
                    case 6:
                        mf.tree();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }
 
            } catch (InputMismatchException e) {
                lea.nextLine(); 
                System.out.println("Por favor ingrese una opción correcta");
            } catch (NullPointerException e) {
                System.out.println("Primero debe usar la opción 1 (Set File)");
            } catch(IOException e){
                System.out.println("Otro tipo de error");
            }
 
        } while (option != 6);
    }
 
    public static void set() {
        lea.nextLine(); 
        System.out.print("Ingrese la ruta del archivo/carpeta: ");
        String dir = lea.nextLine();
        mf.setFile(dir);
        System.out.println("Ruta asignada con éxito!");
    }
}