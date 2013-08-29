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
//		for (int i=0; i<10000; i++)
//		{
//			for (int j=0; j<10000; j++)
//			{
//				buf++;
//			}
//		}
		
		
//		int[][] a = new int[3][4];
//		int[][] b = new int[3][4];
//		for (int i = 0; i<3; i++)
//		{
//			for(int j = 0; j<4; j++)
//			{
//				a[i][j] = i*j;
//				b[i][j] = 0;
//			}
//		}
//		System.out.println(a.length);
		
		int[][] c = { {10,10000,1000,1,100}, {1, 2, 3, 5, 4} };
		//System.out.println(Arrays.toString(c));
		//int[] order = reorder(c);
		//System.out.println(Arrays.toString(order));
		System.out.println(Arrays.toString(c[0]));
		System.out.println(Arrays.toString(c[1]));
		int[][] favo = { {1, 4}, {0,3} };
		//int[] order2 = retrim(c,favo);
		//System.out.println(Arrays.toString(order2));
		
		int[][] order3 = get_JC_order(c,favo);
		System.out.println(Arrays.toString(order3[0]));
		System.out.println(Arrays.toString(order3[1]));
//		File in_file = new File("testin.txt")
//		if (in_file.exists() == false)
//		{/*if input file does not exsit, done*/
//			System.out.println("File does not exists!");
//			return;
//		}
//		/*else, go*/
//		FileReader a_a = new FileReader(in_file);
		System.out.println( dft.format(new Date()) + " end @ " + buf);
		
		
		
	}
	
	/* return the order array of a after sort:big->small */
	public static int[] reorder(int[] a)
	{
		int temp;
		int temp_id;
		int i,j;
		int len = a.length;
		int[] b = new int[len];
		int[] order = new int[len];
		
		for (i = 0; i< len; i++)
		{
			order[i] = i;
		}
		
		for (i = 0; i < len-1; i++)
		{
			for (j = i; j<len; j++)
			{
				if (a[i] < a[j])
				{/*big->small*/
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					temp_id = order[i];
					order[i] = order[j];
					order[j] = temp_id;
				}
			}
		}
		return order; /*  array saves the order number before sorting */
	}
	
	public static int[] retrim(int[] J, int[] J_f)
	{ /* remove the ith units of J, defined by J_f[] */
		int i,j,c,d;
		boolean found;
		
		int c_cnt = J.length;
		int jc_favor_cnt = J_f.length;
		
		int[] J_temp = new int[c_cnt-jc_favor_cnt];
		
			d = 0;
			for (c = 0; c < c_cnt; c++)
			{
				found = false;
				for (i = 0; i < jc_favor_cnt; i++)
				{
					if (c == J_f[i])
					{
						found = true;
						break;
					}
				}
				if (found == false)
				{
					J_temp[d] = J[c];
					d++;
				}
			}

		return J_temp;
	}
	
	/* get the J assign to C order, favorite first, then order based on J_value, big->small */
	public static int[][] get_JC_order(int[][] Js_value, int[][] Js_favorC)
	{   
		int i;
		int j;
		int c;
		int d;
		int temp,temp_id;
		boolean found = false;
		
		int j_cnt = Js_value.length;    /*Js_value = col number = total J number */
		int c_cnt = Js_value[0].length; /*c_cnt = row number = total C number */
		int jc_favor_cnt = Js_favorC[0].length; //

		if (j_cnt != Js_favorC.length)
		{
			throw new Error("array's col number is not matched");
		}
		
		int[]   order = new int[c_cnt];
		int[][] JC_order = new int[j_cnt][c_cnt];
		
		int[][] J_trimed = new int[j_cnt][c_cnt-jc_favor_cnt];
		int[][] J_trimed_order = new int[j_cnt][c_cnt-jc_favor_cnt];
		
		
		
		
		for (j = 0; j < j_cnt; j++)
		{
			
			for (c = 0; c < c_cnt; c++)
			{
				order[c] = c;
			}
			/* trim Js_value, delete number in favorite group */
			d = 0;
			for (c = 0; c < c_cnt; c++)
			{
				found = false;
				for (i = 0; i < jc_favor_cnt; i++)
				{
					if (c == Js_favorC[j][i])
					{
						found = true;
						break;
					}
				}
				if (found == false)
				{
					J_trimed[j][d] = Js_value[j][c];
					J_trimed_order[j][d] = order[c];
					d++;
				}
			}
			
			/* get the order array */
			//J_temp_order[j] = reorder(Js_value[j]);	

			for (c = 0; c < c_cnt-jc_favor_cnt-1; c++)
			{
				for (i = c; i < c_cnt-jc_favor_cnt; i++)
				{
					if (J_trimed[j][c] < J_trimed[j][i])
					{/*big->small*/
						temp = J_trimed[j][c];
						J_trimed[j][c] = J_trimed[j][i];
						J_trimed[j][i] = temp;
						temp_id = J_trimed_order[j][c];
						J_trimed_order[j][c] = J_trimed_order[j][i];
						J_trimed_order[j][i] = temp_id;
					}
				}
			}
			
			/*copy favorite order*/
			for (c = 0; c < jc_favor_cnt; c++)
			{
				JC_order[j][c] = Js_favorC[j][c];
			}
			/*assign non-favorite order*/
			d=0;
			for (c = jc_favor_cnt; c < c_cnt; c++)
			{
				JC_order[j][c] = J_trimed_order[j][d];
				d++;
			}
		}

		return JC_order;
	}
	
	
//	public void swap(int a, int b)
//	{
//		int temp;
//		temp = a;
//		a = b;
//		b = temp;
//	}
	

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