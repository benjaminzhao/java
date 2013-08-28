import java.util.*;
import java.io.*;

public class hello
{
	public static void main(String args[])
	{
		System.out.println("hello world");
		
		File in_file = new File("testin.txt")
		if (in_file.exists() == false)
		{/*if input file does not exsit, done*/
			System.out.println("File does not exists!");
			return;
		}
		/*else, go*/
		FileReader a_a = new FileReader(in_file);
		
		
	}

}

public class circuit
{
	private int ID;
	
	private int H;
	private int	E;
	private int P;
	
	public void set_circuit(int id, int h, int e, int p)
	{
		ID = id;
		
		H = h;
		E = e;
		P = p;	
	}
}

public class juggler
{
	private int ID;
	
	private int H;
	private int E;
	private int P;
	
	private int[10] favor_C;
	
	public void set_juggler(int id, int h, int e, int p, int[] c_id)
	{
		ID = id;
		
		H = h;
		E = e;
		P = p;
		
		for (int i = 1; i<=10; i++)
		{
			favor_C[i] = c_id[i];
		}
	}
}