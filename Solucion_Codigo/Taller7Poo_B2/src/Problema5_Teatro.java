import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Problema5_Teatro {

    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner entrada = new Scanner(System.in);

        String[] posiblesCompradores = {"Axel", "Milka", "Valentina", "Cristal", "Camila", "Lily", "Isabel", "Anahi"};

        ArrayList<Entrada> entradasGeneradas = new ArrayList<>();
        ArrayList<Zona> listaZonas = new ArrayList<>();

        listaZonas.add(new Zona("Principal", 200, 25, 17.5));
        listaZonas.add(new Zona("PalcoB", 40, 70, 40));
        listaZonas.add(new Zona("Central", 400, 20, 14));
        listaZonas.add(new Zona("Lateral", 100, 15.5, 10));

        int siguienteId = 1;
        String seguirVendiendo = "S";

        do {
            System.out.println("\n--- Seleccion de zona ---");
            for (int i = 0; i < listaZonas.size(); i++) {
                System.out.println((i + 1) + ". " + listaZonas.get(i).nombre);
            }
            System.out.print("Elija una zona: ");
            int opcionZona = entrada.nextInt();
            entrada.nextLine();

            if (opcionZona < 1 || opcionZona > listaZonas.size()) {
                System.out.println("Zona no valida.");
                continue;
            }

            Zona zonaElegida = listaZonas.get(opcionZona - 1);
            System.out.println(zonaElegida);

            if (!zonaElegida.hayEspacio()) {
                System.out.println("No hay localidades disponibles en esta zona.");
                continue;
            }

            String nombreComprador = posiblesCompradores[rnd.nextInt(posiblesCompradores.length)];
            System.out.println("Nombre del comprador: " + nombreComprador);

            System.out.println("Tipo de entrada:");
            System.out.println("1. Entrada normal");
            System.out.println("2. Entrada reducida (estudiante o pensionista)");
            System.out.println("3. Entrada abonado");
            System.out.print("Seleccione el tipo: ");
            int tipo = entrada.nextInt();
            entrada.nextLine();

            Entrada nuevaEntrada = null;

            switch (tipo) {
                case 1 -> nuevaEntrada = new EntradaNormal(siguienteId, nombreComprador, zonaElegida);
                case 2 -> nuevaEntrada = new EntradaReducida(siguienteId, nombreComprador, zonaElegida);
                case 3 -> nuevaEntrada = new EntradaAbonado(siguienteId, nombreComprador, zonaElegida);
                default -> {
                    System.out.println("Tipo invalido.");
                    continue;
                }
            }

            nuevaEntrada.calcularPrecio();
            zonaElegida.ocuparLocalidad();
            entradasGeneradas.add(nuevaEntrada);

            System.out.println("Entrada generada con exito:");
            System.out.println(nuevaEntrada);
            siguienteId++;

            System.out.print("\nDesea consultar una entrada por su ID? (S/N): ");
            String deseaConsultar = entrada.nextLine().toUpperCase();

            if (deseaConsultar.equals("S")) {
                System.out.print("Ingrese el ID de la entrada: ");
                int buscarId = entrada.nextInt();
                entrada.nextLine();

                boolean fueEncontrada = false;
                for (Entrada e : entradasGeneradas) {
                    if (e.id == buscarId) {
                        System.out.println("Entrada encontrada:");
                        System.out.println(e);
                        fueEncontrada = true;
                        break;
                    }
                }

                if (!fueEncontrada) {
                    System.out.println("No hay ninguna entrada con ese ID.");
                }
            }

            System.out.print("\nDesea continuar vendiendo entradas? (S/N): ");
            seguirVendiendo = entrada.nextLine().toUpperCase();

        } while (seguirVendiendo.equals("S"));

        System.out.println("\n--- Entradas vendidas ---");
        for (Entrada e : entradasGeneradas) {
            System.out.println(e);
        }

        System.out.println("\nFin del sistema.");
    }
}

class Zona {
    public String nombre;
    public int totalAsientos;
    public int asientosOcupados = 0;
    public double precioBase;
    public double precioDescuentoAbonado;

    public Zona(String nombre, int totalAsientos, double precioBase, double precioDescuentoAbonado) {
        this.nombre = nombre;
        this.totalAsientos = totalAsientos;
        this.precioBase = precioBase;
        this.precioDescuentoAbonado = precioDescuentoAbonado;
    }

    public boolean hayEspacio() {
        return asientosOcupados < totalAsientos;
    }

    public void ocuparLocalidad() {
        asientosOcupados++;
    }

    @Override
    public String toString() {
        return "Zona{" + "nombre=" + nombre + ", totalAsientos=" + totalAsientos + ", asientosOcupados=" + asientosOcupados
                + ", precioBase=" + precioBase + ", precioAbonado=" + precioDescuentoAbonado + '}';
    }
}

abstract class Entrada {
    public int id;
    public String comprador;
    public Zona zona;
    public double precioFinal;

    public Entrada(int id, String comprador, Zona zona) {
        this.id = id;
        this.comprador = comprador;
        this.zona = zona;
    }

    public abstract void calcularPrecio();

    @Override
    public String toString() {
        return "Entrada{" + "ID=" + id + ", comprador=" + comprador + ", zona=" + zona.nombre + ", precio=" + precioFinal + '}';
    }
}

class EntradaNormal extends Entrada {

    public EntradaNormal(int id, String comprador, Zona zona) {
        super(id, comprador, zona);
    }

    @Override
    public void calcularPrecio() {
        this.precioFinal = zona.precioBase;
    }

    @Override
    public String toString() {
        return "EntradaNormal -> " + super.toString();
    }
}

class EntradaReducida extends Entrada {

    public EntradaReducida(int id, String comprador, Zona zona) {
        super(id, comprador, zona);
    }

    @Override
    public void calcularPrecio() {
        this.precioFinal = zona.precioBase * 0.85;
    }

    @Override
    public String toString() {
        return "EntradaReducida -> " + super.toString();
    }
}

class EntradaAbonado extends Entrada {

    public EntradaAbonado(int id, String comprador, Zona zona) {
        super(id, comprador, zona);
    }

    @Override
    public void calcularPrecio() {
        this.precioFinal = zona.precioDescuentoAbonado;
    }

    @Override
    public String toString() {
        return "EntradaAbonado -> " + super.toString();
    }
}