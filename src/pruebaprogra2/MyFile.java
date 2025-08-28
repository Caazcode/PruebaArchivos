
package pruebaprogra2;

 
import java.io.File;
import java.io.IOException;
import java.util.Date;
 
public class MyFile {
 
    private File file = null;
 
    public void setFile(String dir) {
        file = new File(dir);
    }
 
    public void info() {
        if (file.exists()) {
            System.out.println("\n SI EXISTE: \n-----------");
            System.out.println("Nombre: " + file.getName());
            System.out.println("Path: " + file.getPath());
            System.out.println("Absoluto: " + file.getAbsolutePath());
            System.out.println("Padre: " + file.getAbsoluteFile().getParentFile().getParent());
            System.out.println("Bytes: " + file.length());
 
            if (file.isFile()) {
                System.out.println("ES UN ARCHIVO");
            } else if (file.isDirectory()) {
                System.out.println("ES UN FOLDER");
            }
 
            System.out.println("Ultima Modif: " + new Date(file.lastModified()));
        } else {
            System.out.println("Aun no existe");
        }
    }
 
    public void crearArchivo() throws IOException {
        if (file == null) {
            System.out.println("Primero use 'Set File'.");
            return;
        }
 
        System.out.println("Intentando crear archivo en: " + file.getAbsolutePath());
 
        if (file.exists()) {
            System.out.println("No se pudo crear: ya existe (archivo/carpeta).");
            return;
        }
        File parent = file.getAbsoluteFile().getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs()) {
                System.out.println("No se pudieron crear los directorios padre: " + parent.getAbsolutePath());
                return;
            }
        }
 
        try {
            if (file.createNewFile()) {
                System.out.println("Creado con éxito!");
            } else {
                System.out.println("No se pudo crear (verifique permisos/ruta).");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
            throw e;
        }
    }
 
    public void crearFolder() {
        if (file == null) {
            System.out.println("Primero use 'Set File'.");
            return;
        }
 
        System.out.println("Intentando crear carpeta en: " + file.getAbsolutePath());
 
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("La carpeta ya existe.");
            } else {
                System.out.println("Ya existe un archivo con ese nombre.");
            }
            return;
        }
 
        if (file.mkdirs()) {
            System.out.println("Creado con éxito!");
        } else {
            System.out.println("No se pudo crear (verifique permisos/ruta).");
        }
    }
 
    public void borrarDirectorio() {
        if (file != null && file.isDirectory()) {
            File[] archivos = file.listFiles();
            if (archivos != null) {
                for (File f : archivos) {
                    if (f.delete()) {
                        System.out.println("Borrado: " + f.getAbsolutePath());
                    } else {
                        System.out.println("No se pudo borrar: " + f.getAbsolutePath());
                    }
                }
            }
            if (file.delete()) {
                System.out.println("Directorio borrado: " + file.getAbsolutePath());
            } else {
                System.out.println("No se pudo borrar el directorio: " + file.getAbsolutePath());
            }
        } else {
            System.out.println("El path no es un directorio o no existe");
        }
    }
 
    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            for (File child : dir.listFiles()) {
                if (!child.isHidden()) {
                    tree(child, tab + "-");
                }
 
            }
        }
 
    }
 
    void tree(){
    tree(file,"-");
    }
 
}