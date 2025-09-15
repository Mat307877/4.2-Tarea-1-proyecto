package com.tarea;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InventarioApp {

    static class Categoria {
        private String nombre;
        private String descripcion;
        private List<String> atributos;

        public Categoria(String nombre, String descripcion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.atributos = new ArrayList<>();
        }

        public void agregarAtributo(String atributo) {
            atributos.add(atributo);
        }

        public List<String> getAtributos() {
            return atributos;
        }

        public String getNombre() {
            return nombre;
        }
    }

    static class Producto implements Cloneable {
        private String nombre;
        private Categoria categoria;
        private double precio;
        private int cantidad;
        private Map<String, String> atributos;

        public Producto(String nombre, Categoria categoria, double precio, int cantidad) {
            this.nombre = nombre;
            this.categoria = categoria;
            this.precio = precio;
            this.cantidad = cantidad;
            this.atributos = new HashMap<>();
        }

        public void setAtributo(String nombreAtributo, String valor) {
            atributos.put(nombreAtributo, valor);
        }

        public Producto clonar() {
            try {
                return (Producto) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void mostrarInfo() {
            System.out.println("Producto: " + nombre + ", Categoría: " + categoria.getNombre() +
                    ", Precio: " + precio + ", Cantidad: " + cantidad);
            for (String attr : atributos.keySet()) {
                System.out.println(" - " + attr + ": " + atributos.get(attr));
            }
        }

        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
        public int getCantidad() { return cantidad; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Categoria> categorias = new ArrayList<>();
        List<Producto> inventario = new ArrayList<>();

        System.out.println("=== Bienvenido a la aplicación de Inventario ===");

        // Crear categorías
        System.out.print("¿Cuántas categorías deseas crear? ");
        int numCategorias = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        for (int i = 0; i < numCategorias; i++) {
            System.out.print("Nombre de la categoría: ");
            String nombreCat = sc.nextLine();
            System.out.print("Descripción de la categoría: ");
            String descCat = sc.nextLine();

            Categoria cat = new Categoria(nombreCat, descCat);

            System.out.print("¿Cuántos atributos tendrá esta categoría? ");
            int numAtributos = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            for (int j = 0; j < numAtributos; j++) {
                System.out.print("Nombre del atributo " + (j + 1) + ": ");
                cat.agregarAtributo(sc.nextLine());
            }

            categorias.add(cat);
        }

        // Crear productos
        System.out.print("¿Cuántos productos deseas crear? ");
        int numProductos = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numProductos; i++) {
            System.out.print("Nombre del producto: ");
            String nombreProd = sc.nextLine();

            System.out.println("Selecciona la categoría:");
            for (int j = 0; j < categorias.size(); j++) {
                System.out.println((j + 1) + ". " + categorias.get(j).getNombre());
            }
            int opcionCat = sc.nextInt() - 1;
            sc.nextLine();

            System.out.print("Precio: ");
            double precio = sc.nextDouble();
            System.out.print("Cantidad en inventario: ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            Producto prod = new Producto(nombreProd, categorias.get(opcionCat), precio, cantidad);

            // Agregar atributos específicos
            for (String attr : categorias.get(opcionCat).getAtributos()) {
                System.out.print("Valor para " + attr + ": ");
                prod.setAtributo(attr, sc.nextLine());
            }

            inventario.add(prod);
        }

        // Mostrar inventario
        System.out.println("\n=== Inventario Final ===");
        for (Producto p : inventario) {
            p.mostrarInfo();
            System.out.println();
        }

        sc.close();
    }
}