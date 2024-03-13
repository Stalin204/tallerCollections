package library;


import java.util.ArrayList;
import java.util.Iterator;
import exception.NoElementsException;
import exception.OutLimitException;

public class Printer<E> {
	private String name;
	public String getNombre() {
		return name;
	}

	public void setNombre(String nombre) {
		this.name = nombre;
	}

	@Override
	public String toString() {
		return "Printer [name=" + name + "]";
	}

	public Printer(String nombre) {
		super();
		this.name = nombre;
	}
	public Printer() {
		super();
		this.name = "WithOut name";
	}

	public static void checkLimit(int i, int tam)throws OutLimitException, NoElementsException{
		Library.checkLimit(i,tam);

	}


	/*-------------------------------------------------metodos para imprimir--------------------------------------------------------------------------------------------------------*/

	public void print(E dato)
	{
		System.out.print(dato.toString());
	}
	public void println(E dato)
	{
		System.out.print("\n"+dato.toString());
	}

	public void print(E[] list){
		print(list, 0, list.length-1);
	}
	public void print(E[] list, int i, int j){
		for (i=i; i <= j; i++) {
			System.out.print("[");
			print(list[i]);
			System.out.print("]\n");
		}
	}



}