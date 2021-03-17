package ejempcomparable;

import java.util.Arrays;
import java.util.Comparator;

public class ClaseTest {

	static Empleado[] empleados = new Empleado[5];

	public static void main(String[] args) {

		empleados[0] = new Empleado("Jose", "Sanchez", 2500f, 40, "verde");
		empleados[1] = new Empleado("María", "Hueta", 3000f, 45, "azul");
		empleados[2] = new Empleado("Verónica", "Muñoz", 1800.48f, 30, "negro");
		empleados[3] = new Empleado("Antonio", "Martínez", 1600.19f, 35, "marron");
		empleados[4] = new Empleado("Marcos", "Pérez", 2500f, 20, "castaño");

		// Como el objeto de tipo Empleado implementa el interface Comparable el método
		// sort ordenará según el método compareTo implementado en el.
		Arrays.sort(empleados);
		listaEmpleados("Ordenado por edad");

		// Podemos crear un objeto en línea que implementa un interface Comparator
		// y luego pasarselo a sort

		Comparator<Empleado> comp = new Comparator<Empleado>() {

			@Override
			public int compare(Empleado o1, Empleado o2) {
				return (int) ((o1.getSalario() - o2.getSalario()));
			}

		};
		
		Arrays.sort(empleados, comp);
		listaEmpleados("Ordendado por salario");
		
		// Podemos crear objeto con una clase anónima en línea y asarselo a sort
		
		Arrays.sort(empleados,new Comparator<Empleado>() {

			@Override
			public int compare(Empleado o1, Empleado o2) {
				return (int) ((o1.getSalario() - o2.getSalario()));
			}
			
		});
		
		listaEmpleados("Ordendado por salario (clase anónima)");
		
		
		

	}

	// Podemos crear un objeto en línea que implementa un interface Comparator

	static void listaEmpleados(String orden) {
		System.out.println("** " + orden + " **");
		System.out.printf("%-15s  %15s  %15s  %-15s\n", "NOMBRE", "SALARIO", "EDAD", "COLOR OJOS");

		for (Empleado e : empleados) {
			System.out.printf(e.lineaEmpleado());

		}
	}

}
