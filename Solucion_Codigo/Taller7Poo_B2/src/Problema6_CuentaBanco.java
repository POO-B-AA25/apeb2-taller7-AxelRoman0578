import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Problema6_CuentaBanco {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        String[] nombres = {"Axel", "María", "Milka", "Ana", "Cristal", "Lily", "Natalia", "Anahi"};
        String[] tiposCuenta = {"Cuenta de Ahorro", "Cuenta de Cheque", "Cuenta Platino"};

        ArrayList<CuentaBancaria> listaCuentas = new ArrayList<>();

        String continuar;

        do {
            String numeroCuenta = "C" + (10000000 + random.nextInt(90000000));
            String nombreCliente = nombres[random.nextInt(nombres.length)];
            double balanceInicial = 500 + random.nextDouble() * 9500;

            System.out.println("\nSeleccione el tipo de cuenta:");
            System.out.println("1. Cuenta de Ahorro");
            System.out.println("2. Cuenta de Cheque");
            System.out.println("3. Cuenta Platino");
            System.out.print("Opcion: ");
            int tipoCuenta = sc.nextInt();
            sc.nextLine();

            switch (tipoCuenta) {
                case 1 -> {
                    double tasaInteres = 0.02 + random.nextDouble() * 0.08;
                    CuentaAhorro ca = new CuentaAhorro(tasaInteres, numeroCuenta, nombreCliente, balanceInicial);
                    listaCuentas.add(ca);
                    System.out.println("\nCuenta de Ahorro creada: " + ca);
                }
                case 2 -> {
                    boolean sobregiro = random.nextBoolean();
                    CuentaCheque cc = new CuentaCheque(sobregiro, numeroCuenta, nombreCliente, balanceInicial);
                    listaCuentas.add(cc);
                    System.out.println("\nCuenta de Cheque creada: " + cc);
                }
                case 3 -> {
                    double tasaInteres = 0.05 + random.nextDouble() * 0.10;
                    CuentaPlatino cp = new CuentaPlatino(tasaInteres, numeroCuenta, nombreCliente, balanceInicial);
                    listaCuentas.add(cp);
                    System.out.println("\nCuenta Platino creada: " + cp);
                }
                default -> System.out.println("Opción no valida.");
            }

            if (!listaCuentas.isEmpty()) {
                CuentaBancaria cuentaActual = listaCuentas.get(listaCuentas.size() - 1);
                
                System.out.println("\nDesea realizar operaciones con esta cuenta? (S/N)");
                String operar = sc.nextLine().toUpperCase();
                
                while (operar.equals("S")) {
                    System.out.println("\nOperaciones disponibles:");
                    System.out.println("1. Depositar");
                    System.out.println("2. Retirar");
                    System.out.println("3. Mostrar informacion");
                    System.out.print("Opcion: ");
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    
                    switch (opcion) {
                        case 1 -> {
                            System.out.print("Ingrese monto a depositar: ");
                            double montoDeposito = sc.nextDouble();
                            sc.nextLine();
                            cuentaActual.depositar(montoDeposito);
                            System.out.println("Depoosito realizado. Nuevo balance: " + cuentaActual.balance);
                        }
                        case 2 -> {
                            System.out.print("Ingrese monto a retirar: ");
                            double montoRetiro = sc.nextDouble();
                            sc.nextLine();
                            cuentaActual.retirar(montoRetiro);
                            System.out.println("Retiro realizado. Nuevo balance: " + cuentaActual.balance);
                        }
                        case 3 -> {
                            System.out.println(cuentaActual.toString());
                        }
                        default -> System.out.println("Opcion no vslida.");
                    }
                    
                    System.out.println("\nDesea realizar otra operacion con esta cuenta? (S/N)");
                    operar = sc.nextLine().toUpperCase();
                }
            }

            System.out.println("\nDesea crear otra cuenta? (S/N)");
            continuar = sc.nextLine().toUpperCase();

        } while (continuar.equals("S"));

        System.out.println("\n--- Lista Final de Cuentas ---");
        for (CuentaBancaria cuenta : listaCuentas) {
            System.out.println(cuenta);
        }

        System.out.println("\nPrograma finalizado.");
    }
}

class CuentaBancaria {
    public String numeroCuenta;
    public String nombreCliente;
    public double balance;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String numeroCuenta, String nombreCliente, double balance) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = balance;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            balance += monto;
        } else {
            System.out.println("Monto inválido para depósito.");
        }
    }

    public void retirar(double monto) {
        if (monto > 0 && balance >= monto) {
            balance -= monto;
        } else {
            System.out.println("Monto invalido o saldo insuficiente para retiro.");
        }
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "numeroCuenta=" + numeroCuenta + ", nombreCliente=" + nombreCliente + ", balance=" + balance + '}';
    }
}

class CuentaCheque extends CuentaBancaria {
    public boolean sobregiro;

    public CuentaCheque() {
    }

    public CuentaCheque(boolean sobregiro, String numeroCuenta, String nombreCliente, double balance) {
        super(numeroCuenta, nombreCliente, balance);
        this.sobregiro = sobregiro;
    }

    public void revisarSobregiro() {
        if (balance < 0) {
            sobregiro = true;
            System.out.println("Cuenta en sobregiro!");
        } else {
            sobregiro = false;
        }
    }

    @Override
    public void retirar(double monto) {
        if (monto > 0) {
            balance -= monto;
            revisarSobregiro();
        } else {
            System.out.println("Monto invalido para retiro.");
        }
    }

    @Override
    public String toString() {
        return "CuentaCheque{sobregiro=" + sobregiro + "} " + super.toString();
    }
}

class CuentaAhorro extends CuentaBancaria {
    public double tasaInteres;

    public CuentaAhorro() {
    }

    public CuentaAhorro(double tasaInteres, String numeroCuenta, String nombreCliente, double balance) {
        super(numeroCuenta, nombreCliente, balance);
        this.tasaInteres = tasaInteres;
    }

    public void calcularInteres() {
        double interes = balance * tasaInteres;
        balance += interes;
        System.out.println("Interes calculado y agregado: " + interes);
    }

    @Override
    public String toString() {
        return "CuentaAhorro{tasaInteres=" + tasaInteres + "} " + super.toString();
    }
}

class CuentaPlatino extends CuentaBancaria {
    public double tasaInteres;

    public CuentaPlatino() {
    }

    public CuentaPlatino(double tasaInteres, String numeroCuenta, String nombreCliente, double balance) {
        super(numeroCuenta, nombreCliente, balance);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void depositar(double monto) {
        super.depositar(monto * 1.01);
    }

    @Override
    public String toString() {
        return "CuentaPlatino{tasaInteres=" + tasaInteres + "} " + super.toString();
    }
}