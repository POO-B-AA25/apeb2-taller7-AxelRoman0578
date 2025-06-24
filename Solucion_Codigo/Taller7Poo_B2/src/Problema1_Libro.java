import java.util.ArrayList;
public class Problema1_Libro {
    public static void main(String[] args) {

        ArrayList<Palabra> palabras1 = new ArrayList<>();
        palabras1.add(new Palabra("Llamame"));
        palabras1.add(new Palabra("cuando"));
        palabras1.add(new Palabra("no"));
        palabras1.add(new Palabra("te"));
        palabras1.add(new Palabra("encuentres"));

        ArrayList<Sentencia> sentencias1 = new ArrayList<>();
        sentencias1.add(new Sentencia(palabras1));

        ArrayList<Componente> componentes1 = new ArrayList<>();
        componentes1.add(new Parrafo("Titulo del libro", sentencias1));
        componentes1.add(new Figura("Figura 1", "Portada", "Portada original de la novela"));

        ArrayList<String> temas = new ArrayList<>();
        temas.add("Identidad");
        temas.add("Soledad");
        temas.add("Encuentro");
        componentes1.add(new Lista("Temas principales", temas));

        componentes1.add(new Tabla("Resumen por capítulos", 3, 2));

        ArrayList<String> personajes = new ArrayList<>();
        personajes.add("Samantha");
        personajes.add("Marco");
        personajes.add("La madre");
        componentes1.add(new Vineta("Personajes importantes", personajes));

        ArrayList<Seccion> secciones1 = new ArrayList<>();
        secciones1.add(new Seccion("1.1", componentes1));

        ArrayList<Palabra> palabras2 = new ArrayList<>();
        palabras2.add(new Palabra("¿Quien"));
        palabras2.add(new Palabra("soy"));
        palabras2.add(new Palabra("en"));
        palabras2.add(new Palabra("realidad?"));

        ArrayList<Sentencia> sentencias2 = new ArrayList<>();
        sentencias2.add(new Sentencia(palabras2));

        ArrayList<Componente> componentes2 = new ArrayList<>();
        componentes2.add(new Parrafo("Conflicto interno", sentencias2));
        componentes2.add(new Figura("Figura 2", "Espejo", "Reflejo simbólico del personaje"));
        componentes2.add(new Tabla("Relaciones familiares", 2, 3));
        componentes2.add(new Vineta("Elementos simbólicos", new ArrayList<>(java.util.Arrays.asList("Espejo", "Carta", "Estacion de tren"))));

        ArrayList<Seccion> secciones2 = new ArrayList<>();
        secciones2.add(new Seccion("2.1", componentes2));

        ArrayList<Capitulo> capitulos = new ArrayList<>();
        capitulos.add(new Capitulo("Capitulo 1 - El inicio", secciones1));
        capitulos.add(new Capitulo("Capitulo 2 - La busqueda", secciones2));

        Libro libro = new Libro("Gilraen Earfalas", 2, 180, capitulos);
        System.out.println(libro);
    }
}

class Libro {
    private String autor;
    private int numberCap;
    private int numberPag;
    private ArrayList<Capitulo> capitulos;

    public Libro(String autor, int numberCap, int numberPag, ArrayList<Capitulo> capitulos) {
        this.autor = autor;
        this.numberCap = numberCap;
        this.numberPag = numberPag;
        this.capitulos = capitulos;
    }

    @Override
    public String toString() {
        return "Libro{" + "autor='" + autor + "', numberCap=" + numberCap + ", numberPag=" + numberPag + ", capitulos=" + capitulos + '}';
    }
}

class Capitulo {
    private String titulo;
    private ArrayList<Seccion> secciones;

    public Capitulo(String titulo, ArrayList<Seccion> secciones) {
        this.titulo = titulo;
        this.secciones = secciones;
    }

    @Override
    public String toString() {
        return "Capitulo{" + "titulo='" + titulo + "', secciones=" + secciones + '}';
    }
}

class Seccion {
    private String numero;
    private ArrayList<Componente> componentes;

    public Seccion(String numero, ArrayList<Componente> componentes) {
        this.numero = numero;
        this.componentes = componentes;
    }

    @Override
    public String toString() {
        return "Seccion{" + "numero='" + numero + "', componentes=" + componentes + '}';
    }
}

abstract class Componente {
    protected String contenido;

    public Componente(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Componente{" + "contenido='" + contenido + "'}";
    }
}

class Parrafo extends Componente {
    private ArrayList<Sentencia> sentencias;

    public Parrafo(String contenido, ArrayList<Sentencia> sentencias) {
        super(contenido);
        this.sentencias = sentencias;
    }

    @Override
    public String toString() {
        return "Parrafo{" + "sentencias=" + sentencias + "} " + super.toString();
    }
}

class Sentencia {
    private ArrayList<Palabra> palabras;

    public Sentencia(ArrayList<Palabra> palabras) {
        this.palabras = palabras;
    }

    @Override
    public String toString() {
        return "Sentencia{" + "palabras=" + palabras + '}';
    }
}

class Palabra {
    private String texto;

    public Palabra(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Palabra{" + "texto='" + texto + "'}";
    }
}

class Figura extends Componente {
    private String titulo;
    private String leyenda;

    public Figura(String contenido, String titulo, String leyenda) {
        super(contenido);
        this.titulo = titulo;
        this.leyenda = leyenda;
    }

    @Override
    public String toString() {
        return "Figura{" + "titulo='" + titulo + "', leyenda='" + leyenda + "'} " + super.toString();
    }
}

class Lista extends Componente {
    private ArrayList<String> items;

    public Lista(String contenido, ArrayList<String> items) {
        super(contenido);
        this.items = items;
    }

    @Override
    public String toString() {
        return "Lista{" + "items=" + items + "} " + super.toString();
    }
}

class Tabla extends Componente {
    private int filas;
    private int columnas;

    public Tabla(String contenido, int filas, int columnas) {
        super(contenido);
        this.filas = filas;
        this.columnas = columnas;
    }

    @Override
    public String toString() {
        return "Tabla{" + "filas=" + filas + ", columnas=" + columnas + "} " + super.toString();
    }
}

class Vineta extends Componente {
    private ArrayList<String> puntos;

    public Vineta(String contenido, ArrayList<String> puntos) {
        super(contenido);
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Vineta{" + "puntos=" + puntos + "} " + super.toString();
    }
}