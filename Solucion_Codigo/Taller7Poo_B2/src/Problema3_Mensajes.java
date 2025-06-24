import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Problema3_Mensajes {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random generador = new Random();
        ArrayList<Mensaje> mensajesEnviados = new ArrayList<>();

        String[] nombresUsuarios = {"Alma", "Milka", "Axel", "Cristal", "Camila", "Lily", "Adamaris", "Maria"};
        String[] textosDisponibles = {"¿Donde estás?", "Nos vemos pronto", "Te extranio", "Reunion cancelada", "Estoy ocupado", "Gracias por todo"};
        String[] archivosImagen = {"selfie.png", "cumpleanios.jpg", "gato.gif", "viaje.pdf", "pantalla.png"};

        char eleccion;

        do {
            System.out.println("\n--- Centro de Mensajes ---");
            System.out.println("1. Crear mensaje de texto (SMS)");
            System.out.println("2. Crear mensaje con imagen (MMS)");
            System.out.println("3. Mostrar todos los mensajes");
            System.out.println("4. Terminar programa");
            System.out.print("Elija una opcion: ");
            eleccion = entrada.next().charAt(0);
            entrada.nextLine();

            switch (eleccion) {
                case '1': {
                    int emisor = 600000000 + generador.nextInt(100000000);
                    int receptor = 600000000 + generador.nextInt(100000000);
                    String nombreEmisor = nombresUsuarios[generador.nextInt(nombresUsuarios.length)];
                    String nombreReceptor = nombresUsuarios[generador.nextInt(nombresUsuarios.length)];
                    String texto = textosDisponibles[generador.nextInt(textosDisponibles.length)];

                    MensajeTexto nuevoSMS = new MensajeTexto(texto, emisor, receptor, nombreEmisor, nombreReceptor);
                    mensajesEnviados.add(nuevoSMS);
                    System.out.println("\nMensaje de texto creado y enviado:\n" + nuevoSMS);
                    break;
                }

                case '2': {
                    int emisor = 600000000 + generador.nextInt(100000000);
                    int receptor = 600000000 + generador.nextInt(100000000);
                    String nombreEmisor = nombresUsuarios[generador.nextInt(nombresUsuarios.length)];
                    String nombreReceptor = nombresUsuarios[generador.nextInt(nombresUsuarios.length)];
                    String nombreArchivo = archivosImagen[generador.nextInt(archivosImagen.length)];

                    MensajeImagen nuevoMMS = new MensajeImagen(nombreArchivo, emisor, receptor, nombreEmisor, nombreReceptor);
                    mensajesEnviados.add(nuevoMMS);
                    System.out.println("\nMensaje con imagen creado y enviado:\n" + nuevoMMS);
                    break;
                }

                case '3': {
                    System.out.println("\n--- Historial de Mensajes ---");
                    for (Mensaje mensaje : mensajesEnviados) {
                        System.out.println(mensaje);
                    }
                    break;
                }

                case '4':
                    System.out.println("Cerrando sistema de mensajeria...");
                    break;

                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }

        } while (eleccion != '4');
    }
}

class Mensaje {
    protected int numeroOrigen;
    protected int numeroDestino;
    protected String nombreOrigen;
    protected String nombreDestino;

    public Mensaje(int numeroOrigen, int numeroDestino) {
        this.numeroOrigen = numeroOrigen;
        this.numeroDestino = numeroDestino;
    }

    public Mensaje(int numeroOrigen, int numeroDestino, String nombreOrigen, String nombreDestino) {
        this.numeroOrigen = numeroOrigen;
        this.numeroDestino = numeroDestino;
        this.nombreOrigen = nombreOrigen;
        this.nombreDestino = nombreDestino;
    }

    @Override
    public String toString() {
        return "De: " + nombreOrigen + " (" + numeroOrigen + ") -> A: " + nombreDestino + " (" + numeroDestino + ")";
    }
}

class MensajeTexto extends Mensaje {
    private String contenido;

    public MensajeTexto(String contenido, int numeroOrigen, int numeroDestino, String nombreOrigen, String nombreDestino) {
        super(numeroOrigen, numeroDestino, nombreOrigen, nombreDestino);
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "[SMS] \"" + contenido + "\"\n" + super.toString();
    }
}

class MensajeImagen extends Mensaje {
    private String archivo;

    public MensajeImagen(String archivo, int numeroOrigen, int numeroDestino, String nombreOrigen, String nombreDestino) {
        super(numeroOrigen, numeroDestino, nombreOrigen, nombreDestino);
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "[MMS] Imagen adjunta: " + archivo + "\n" + super.toString();
    }
}