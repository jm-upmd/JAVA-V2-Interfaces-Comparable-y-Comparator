package ejempcomparable;

import java.util.Comparator;

public class ComparadorSalario implements Comparator<Empleado> {

	@Override
	public int compare(Empleado o1, Empleado o2) {
		return (int) (o1.getSalario() - o2.getSalario());	}
}
