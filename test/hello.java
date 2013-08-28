import java.text.*;
import java.util.*;
import java.lang.Math.*;
import java.io.*;


public class hello
{
	public static void main(String args[])
	{
		long buf = 0L;
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		System.out.println( dft.format(new Date()) + " start");
		for (int i=0; i<10000; i++)
		{
			for (int j=0; j<10000; j++)
			{
				buf++;
			}
		}
		System.out.println( dft.format(new Date()) + " end @ " + buf);
		
		int[][] a = new int[3][4];
		int[][] b = new int[3][4];
		for (int i = 0; i<3; i++)
		{
			for(int j = 0; j<4; j++)
			{
				a[i][j] = i*j;
				b[i][j] = 0;
			}
		}
		System.out.println(a.length);
//		File in_file = new File("testin.txt")
//		if (in_file.exists() == false)
//		{/*if input file does not exsit, done*/
//			System.out.println("File does not exists!");
//			return;
//		}
//		/*else, go*/
//		FileReader a_a = new FileReader(in_file);
		
		
		
		
	}
	
	public int[] reorder(int[] a)
	{
		
		int[] b;
		int temp;
		for (int i = 0; i < a.length; i++)
		{
			
		}
	}
	
	public int[][] set_JC_order(int[][] Js_value, int[][] Js_favorC)
	{
		int j = -1;
		int c = -1;
		
		int j_cnt = Js_value.length; /*Js_value = col number = total J number */
		int c_cnt = Js_value[0].length; /*c_cnt = row number = total C number */
		int jc_favor_cnt = Js_favorC[0].length; //

		if (j_cnt != Js_favorC.length)
		{
			throw new Error("array's col number is not matched");
		}
		int[][] JC_order = new int[j_cnt][c_cnt];
		
		for (j = 0; j < j_cnt; j++)
		{
			/*copy favorite order*/
			for (c = 0; c < jc_favor_cnt; c++)
			{
				JC_order[j][c] = Js_favorC[j][c];
			}
			/*assign non-favorite order*/
			for (c = jc_favor_cnt; c < c_cnt; c++)
			{
				
			}
		}
		return JC_order;
	}
	
	public void swap(int a, int b)
	{
		int temp;
		temp = a;
		a = b;
		b = temp;
	}
	

}

//public class circuit
//{
//	private int ID;
//	
//	private int H;
//	private int	E;
//	private int P;
//	
//	public void set_circuit(int id, int h, int e, int p)
//	{
//		ID = id;
//		
//		H = h;
//		E = e;
//		P = p;	
//	}
//}
//
//public class juggler
//{
//	private int ID;
//	
//	private int H;
//	private int E;
//	private int P;
//	
//	private int[] favor_C;
//	
//	public void set_juggler(int id, int h, int e, int p, int[] c_id)
//	{
//		ID = id;
//		
//		H = h;
//		E = e;
//		P = p;
//		
//		for (int i = 1; i<=10; i++)
//		{
//			favor_C[i] = c_id[i];
//		}
//	}
//}