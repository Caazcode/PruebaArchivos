
package pruebaprogra2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;
import java.util.Arrays;


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
           
            try {
                System.out.println("Padre: " + file.getAbsoluteFile().getParentFile().getParent());
            } catch (Exception e) {
                System.out.println("Padre: (sin padre)");
            }
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
        if (dir != null && dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            File[] hijos = dir.listFiles();
            if (hijos != null) {
                for (File child : hijos) {
                    if (!child.isHidden()) {
                        tree(child, tab + "-");
                    }
                }
            }
        }
    }

    void tree(){
        tree(file,"-");
    }

    public void mostrarDir() {
    if (file == null) {
        System.out.println("Primero use 'Set File'.");
        return;
    }
    if (!file.exists() || !file.isDirectory()) {
        System.out.println("Debe asignar un directorio válido con 'Set File'.");
        return;
    }

    System.out.println("Directorio de: " + file.getAbsolutePath());
    System.out.println();
    System.out.printf("%-19s %-6s %10s %s%n",
            "Última Modificación", "Tipo", "Tamaño", "Nombre");

    File[] lista = file.listFiles();
    if (lista == null || lista.length == 0) {
        System.out.println();
        System.out.println("0 archivos   0 bytes");
  
        File raiz = file;
        while (raiz.getParentFile() != null) raiz = raiz.getParentFile();
        System.out.println("0 directorios " + formatoBytes(raiz.getFreeSpace()) + " libres");
        return;
    }

    

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    int cantidadFiles = 0;
    int cantidadDirs  = 0;
    long totalBytes = 0;

    for (int i = 0; i < lista.length; i++) {
        File f = lista[i];
        String fecha = sdf.format(new Date(f.lastModified()));

        if (f.isDirectory()) {
            System.out.printf("%-19s %-6s %10s %s%n",
                    fecha, "<DIR>", "-", f.getName());
            cantidadDirs++;
        } else {
            long size = f.length();
            System.out.printf("%-19s %-6s %10s %s%n",
                    fecha, "FILE", formatoBytes(size), f.getName());
            totalBytes += size;
            cantidadFiles++;
        }
    }

        System.out.println();
        System.out.println(cantidadFiles + " archivos   " + formatoBytes(totalBytes));

        File raiz = file;
        while (raiz.getParentFile() != null) raiz = raiz.getParentFile();
        System.out.println(cantidadDirs + " directorios " + formatoBytes(raiz.getFreeSpace()) + " libres");
    }

        private String formatoBytes(long bytes) {
            double v = bytes;
            String u = "bytes";
            if (v >= 1024) { 
                v /= 1024.0; u = "KB"; 
            }
            if (v >= 1024) {
                v /= 1024.0; u = "MB"; 
            }
            if (v >= 1024) {
                v /= 1024.0; u = "GB"; 
            }
            if (u.equals("bytes")) {
                return (long)v + " " + u;
            } else {
                return String.format("%.1f %s", v, u);
            }
        }
        }
