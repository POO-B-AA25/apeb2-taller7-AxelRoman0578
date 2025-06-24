import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Problema4_Trabajador {

    public static void main(String[] args) {

        Random aleatorio = new Random();
        Scanner entrada = new Scanner(System.in);

        String[] listaNombres = {"Axel", "Paula", "Milka", "Elena", "Cristal", "Lily", "Natalia", "Rosa"};
        String[] listaApellidos = {"Torres", "Ramírez", "Quinde", "Roman", "Castillo", "Rivas", "Cruz"};
        String[] listaDirecciones = {"Av Norte 10", "Calle Sur 33", "Camino Central 22", "Plaza Este 7", "Pasaje Oeste 55"};

        ArrayList<Empleado> registroEmpleados = new ArrayList<>();
        ArrayList<Supervisor> registroSupervisores = new ArrayList<>();

        String respuestaMasSupervisores;

        do {
            String nombreSup = listaNombres[aleatorio.nextInt(listaNombres.length)];
            String apellidoSup = listaApellidos[aleatorio.nextInt(listaApellidos.length)] + " " + listaApellidos[aleatorio.nextInt(listaApellidos.length)];
            String direccionSup = listaDirecciones[aleatorio.nextInt(listaDirecciones.length)];
            int idSup = 10000000 + aleatorio.nextInt(90000000);
            double salarioBase = 3000 + aleatorio.nextDouble() * 2000;

            Supervisor supervisor = new Supervisor(salarioBase, nombreSup, apellidoSup, direccionSup, idSup);
            registroSupervisores.add(supervisor);
            registroEmpleados.add(supervisor);

            System.out.println("\nSupervisor registrado: " + supervisor);

            String agregarEmpleados;
            do {
                System.out.println("\n¿Agregar un empleado a este supervisor? (S/N)");
                agregarEmpleados = entrada.nextLine().toUpperCase();

                if (agregarEmpleados.equals("S")) {
                    String nombreEmp = listaNombres[aleatorio.nextInt(listaNombres.length)];
                    String apellidoEmp = listaApellidos[aleatorio.nextInt(listaApellidos.length)] + " " + listaApellidos[aleatorio.nextInt(listaApellidos.length)];
                    String direccionEmp = listaDirecciones[aleatorio.nextInt(listaDirecciones.length)];
                    int idEmp = 10000000 + aleatorio.nextInt(90000000);

                    System.out.println("\nElija tipo de empleado:");
                    System.out.println("1. Sueldo mensual fijo");
                    System.out.println("2. Pago por comisión");
                    System.out.println("3. Pago por horas trabajadas");
                    System.out.print("Opción: ");
                    int tipo = entrada.nextInt();
                    entrada.nextLine();

                    switch (tipo) {
                        case 1 -> {
                            double salarioFijo = 1500 + aleatorio.nextDouble() * 1500;
                            EmpleadoFijo mensual = new EmpleadoFijo(salarioFijo, nombreEmp, apellidoEmp, direccionEmp, idEmp, supervisor);
                            registroEmpleados.add(mensual);
                            System.out.println("Empleado con salario fijo registrado: " + mensual);
                        }
                        case 2 -> {
                            int cantidadVentas = 5 + aleatorio.nextInt(50);
                            double porcentaje = 0.05 + aleatorio.nextDouble() * 0.15;
                            EmpleadoComision comisionista = new EmpleadoComision(cantidadVentas, porcentaje, nombreEmp, apellidoEmp, direccionEmp, idEmp, supervisor);
                            comisionista.calcularPago();
                            registroEmpleados.add(comisionista);
                            System.out.println("Empleado por comisión registrado: " + comisionista);
                        }
                        case 3 -> {
                            int totalHoras = 20 + aleatorio.nextInt(60);
                            double tarifaHora = 10 + aleatorio.nextDouble() * 15;
                            double tarifaExtra = tarifaHora * 1.5;
                            EmpleadoPorHora porHora = new EmpleadoPorHora(totalHoras, tarifaHora, tarifaExtra, nombreEmp, apellidoEmp, direccionEmp, idEmp, supervisor);
                            porHora.calcularPago();
                            registroEmpleados.add(porHora);
                            System.out.println("Empleado por horas registrado: " + porHora);
                        }
                        default -> System.out.println("Opción inválida.");
                    }
                }

            } while (agregarEmpleados.equals("S"));

            System.out.println("\nRegistrar otro supervisor? (S/N)");
            respuestaMasSupervisores = entrada.nextLine().toUpperCase();

        } while (respuestaMasSupervisores.equals("S"));

        System.out.println("\n--- Lista total de empleados ---");
        for (Empleado emp : registroEmpleados) {
            System.out.println(emp);
        }

        System.out.println("\n--- Supervisores registrados ---");
        for (Supervisor sup : registroSupervisores) {
            System.out.println(sup);
        }

        System.out.println("\nFin del sistema.");
    }
}

class Empleado {
    public String nombre;
    public String apellidos;
    public String direccion;
    public int identificador;
    public Supervisor supervisor;

    public Empleado() {}

    public Empleado(String nombre, String apellidos, String direccion, int identificador) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.identificador = identificador;
    }

    public Empleado(String nombre, String apellidos, String direccion, int identificador, Supervisor supervisor) {
        this(nombre, apellidos, direccion, identificador);
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "Empleado{nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion +
                ", identificador=" + identificador + ", supervisor=" + supervisor + '}';
    }
}

class EmpleadoFijo extends Empleado {
    public double salarioMensual;

    public EmpleadoFijo() {}

    public EmpleadoFijo(double salarioMensual, String nombre, String apellidos, String direccion, int identificador, Supervisor supervisor) {
        super(nombre, apellidos, direccion, identificador, supervisor);
        this.salarioMensual = salarioMensual;
    }

    @Override
    public String toString() {
        return "EmpleadoFijo{salarioMensual=" + salarioMensual + "} " + super.toString();
    }
}

class EmpleadoComision extends Empleado {
    public int totalVentas;
    public double porcentajeComision;
    private double salarioGenerado;

    public EmpleadoComision() {}

    public EmpleadoComision(int totalVentas, double porcentajeComision, String nombre, String apellidos, String direccion, int identificador, Supervisor supervisor) {
        super(nombre, apellidos, direccion, identificador, supervisor);
        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
    }

    public void calcularPago() {
        salarioGenerado = totalVentas * porcentajeComision;
    }

    @Override
    public String toString() {
        return "EmpleadoComision{totalVentas=" + totalVentas + ", porcentaje=" + porcentajeComision +
                ", salarioGenerado=" + salarioGenerado + "} " + super.toString();
    }
}

class EmpleadoPorHora extends Empleado {
    public int horasTrabajadas;
    public double tarifaBase;
    public double tarifaExtra;
    private double totalPago;

    public EmpleadoPorHora() {}

    public EmpleadoPorHora(int horasTrabajadas, double tarifaBase, double tarifaExtra, String nombre, String apellidos, String direccion, int identificador, Supervisor supervisor) {
        super(nombre, apellidos, direccion, identificador, supervisor);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaBase = tarifaBase;
        this.tarifaExtra = tarifaExtra;
    }

    public void calcularPago() {
        if (horasTrabajadas <= 40) {
            totalPago = horasTrabajadas * tarifaBase;
        } else {
            int extras = horasTrabajadas - 40;
            totalPago = (40 * tarifaBase) + (extras * tarifaExtra);
        }
    }

    @Override
    public String toString() {
        return "EmpleadoPorHora{horasTrabajadas=" + horasTrabajadas +
                ", tarifaBase=" + tarifaBase + ", tarifaExtra=" + tarifaExtra +
                ", totalPago=" + totalPago + "} " + super.toString();
    }
}

class Supervisor extends Empleado {
    public double salarioBase;
    private double salarioFinal;

    public Supervisor() {}

    public Supervisor(double salarioBase, String nombre, String apellidos, String direccion, int identificador) {
        super(nombre, apellidos, direccion, identificador);
        this.salarioBase = salarioBase;
    }

    public void calcularPago() {
        salarioFinal = salarioBase * 1.2;
    }

    @Override
    public String toString() {
        return "Supervisor{salarioBase=" + salarioBase + ", salarioFinal=" + salarioFinal + "} " + super.toString();
    }
}