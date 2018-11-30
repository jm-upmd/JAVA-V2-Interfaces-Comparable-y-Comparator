package ejempcomparable;

public class Empleado implements Comparable<Empleado> {
	
	String nombre;
	String apellido;
	float salario;
	int edad;
	String colorOjos;
	
	public Empleado(String nombre, String apellido, float salario, int edad, String colorOjos) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.salario = salario;
		this.edad = edad;
		this.colorOjos = colorOjos;
	}
	
	
	// Metodo del interface Comparable que tengo que implementar
	// Establecerá la condición de orden, devolviendo un entero:
	//  -  negativo si empleado < otroEmpleado
	//	-  positivo si empleado > otroEmpleado
	//  -  cero si objeto == argo  
	// Nosotros vamos a establecer un orden atendiendo al salario
	@Override
	public int compareTo(Empleado otroEmpleado) {
		// metodo compare de clase Float devuelve negativo si primer argumento es menor que el segundo,
		// 0 sin son igual, y positivo si primer argumento es mayor que el segundo
		
		return  edad - otroEmpleado.edad;
		//return Float.compare(salario, otroEmpleado.salario);
	}


	// Getters & Setters
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public float getSalario() {
		return salario;
	}


	public void setSalario(float salario) {
		this.salario = salario;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getColorOjos() {
		return colorOjos;
	}


	public void setColorOjos(String colorOjos) {
		this.colorOjos = colorOjos;
	}
	
	
	
	


	
	
	

}
