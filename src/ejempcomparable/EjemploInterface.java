package ejempcomparable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;



public class EjemploInterface {
	
	// Creación de instancias de Comparator que luego pasaré como parámetro al metedo Arrays.sort
	// Esté método ordenara los objetos del array en función del criterio implementado 
	// en el método compare.
	
	
	
	// Orden ascendente  por Edad
	static Comparator<Empleado> ordenEdad = new Comparator<Empleado>() {

		@Override
		public int compare(Empleado o1, Empleado o2) {
			
			return o1.getEdad() - o2.getEdad();
		}
		
	};
	
	// Orden ascendente por salario
	static Comparator<Empleado> ordenSalario = new Comparator<Empleado>() {

		@Override
		public int compare(Empleado o1, Empleado o2) {
			return (int) (o1.getSalario() - o2.getSalario());
		}
		
	};
	
	// Oreden ascendente por salario y edad
	static Comparator<Empleado> ordenSalarioEdad = new Comparator<Empleado>() {

		@Override
		public int compare(Empleado o1, Empleado o2) {
			
			if(ordenSalario.compare(o1, o2) == 0)
				return ordenEdad.compare(o1, o2);
			
			return ordenSalario.compare(o1, o2);
			
			// Otra forma de hacerlo sería esta de abajo (comentada)
			
			/*if(o1.getSalario() == o2.getSalario()) {
				return o1.getEdad() - o2.getEdad();
			} else 	
				return (int) (o1.getSalario() - o2.getSalario());*/
		}
		
	};
	
	// Orden ascendente por color de ojos.
	// En este caso estamos creando una clase interna que implementa el interface
	// ya que para establecer el orden necesito crear objetos y lógica adicional para 
	// establecer el orden en base al color de ojos.
	// A la hora de pasar este Comparator a Arrays.sort tendre que instanciar un objeto 
	// de esta clase y pasarle como parámetro dicho objeto.
	 static class OrdenOjos implements Comparator<Empleado>{
		 
		// Creo un hashmap de pares "color" -> orden
		HashMap<String, Integer> color= new HashMap<String, Integer>(6);
		
		public OrdenOjos() {
			color.put("verde", 1); color.put("azul", 2); color.put("marron", 3);
			color.put("negro", 1); color.put("castaño", 0); 
			color.put("turquesa", 2); // turquesa y azul mismo orden
			
		}
		 
		// La operación para establecer el orden se hace con un entero asociado al color
		@Override
		public int compare(Empleado o1, Empleado o2) {
			return color.get(o1.getColorOjos()) - color.get(o2.getColorOjos());
			
		}
		
	 }
	 
	// Función máin
	public static void main(String[] args) {
		
		Empleado[] empleados = new Empleado[5];
		
		empleados[0] = new Empleado("Jose", "Sanchez", 2500f, 40,"verde");
		empleados[1] = new Empleado("María", "Hueta", 3000f, 45,"azul");
		empleados[2] = new Empleado("Verónica", "Muñoz", 1800.48f, 30, "negro");
		empleados[3] = new Empleado("Antonio", "Martínez", 1600.19f, 35, "marron");
		empleados[4] = new Empleado("Marcos", "Pérez", 2500f, 20, "castaño");

		
		// Como el objeto de tipo Empleado implementa el interface Comparable el método sort
		// ordenará según el método compareTo implementado en el.
		// Para ver esto ejecutar ClaseTest.java en este mismo paquete
		//Arrays.sort(empleados);
		
		
		
		// Usamos el metodo Arrays.sort utilizando el metdodo sort sobrecargado que recibe
		// como segundo parámetro un Comparator
		
		System.out.println("Orden por SALARIO:\n");
		imprimeEnOrden(empleados, ordenSalario) ;
		
		// Otra forma de ordenar por salarios sería implementar el Comparator en una clase aparte (no como clase interna) y luego 
		// usarlo tal y como se hace en la linea de abajo comentada. La clase es la ComparadorSalario.java ubicada dentro 
		// de este mismo paquete.
		// Es equivalente a la clase OrdenOjos declarada en este mismo fichero como clase interna. Esta se podría haber
		// escrito en fichero independiente igual que ComparadorSalario.java
		
		//imprimeEnOrden(empleados, new ComparadorSalario());
		
		// También se puede instanciar un objeto Comparable como clase anonima:
		Comparator<Empleado> salarioDescendente = new Comparator<Empleado>() {

			@Override
			public int compare(Empleado o1, Empleado o2) {
				
				return (int) (o2.salario - o1.salario);
			}
		};
		
		System.out.println("\nOrden por SALARIO DESCENDIENTE: (clase anónima)\n");
		imprimeEnOrden(empleados, salarioDescendente);
		
		// (JDK 8 o posterior) Como Comparator es un interface funcional podemos usar expresiones lambda
		// para instancia un objeto que lo implemente. Las exp lambda hacen el código más reducido/compacto.
		Comparator<Empleado> edadDescendiente = (Empleado o1, Empleado o2) -> o2.edad - o1.edad;
		
		System.out.println("\nOrden por EDAD DESCENDIENTE (lambda):\n");
		imprimeEnOrden(empleados, edadDescendiente);
		
		// Al quedar un código reducido con las exp lambda se suele pasar la exp directamente como 
		//parámetro del método
		System.out.println("\nOrden por EDAD DESCENDIENTE (lambda parámetro directo):\n");
		imprimeEnOrden(empleados, (Empleado o1, Empleado o2) -> o2.edad - o1.edad);
			
		
		System.out.println("\nOrden por EDAD:\n");
		imprimeEnOrden(empleados, ordenEdad);
		
		System.out.println("\nOrden por SALARIO,EDAD:\n");
		imprimeEnOrden(empleados, ordenSalarioEdad);
		
		System.out.println("\nOrden por COLOR DE OJOS:\n");
		
		// Orden ojos es una clase, luego instanciamos un objeto, que puede ejercer como Comparator
		// ya que su clase implementa la intreface Comparator.
		imprimeEnOrden(empleados, new OrdenOjos()); 
		
	}
	
	static void imprimeEnOrden(Empleado[] emps, Comparator<Empleado> orden) {
		
		Arrays.sort(emps,orden); // Ordena el array atendiendo al Comparator pasado como segundo parámetro.
		
		// Sacamos elementod por consola
		System.out.printf("%-15s  %15s  %15s  %-15s\n", "NOMBRE","SALARIO","EDAD","COLOR OJOS");

		for(Empleado e: emps) {
			System.out.printf("%-15s  %15.2f  %15d  %-15s\n", e.getNombre(),e.getSalario(),e.getEdad(),e.getColorOjos());

		}
		
	}

}
