import java.text.*;
import java.util.*;
import java.util.regex.Pattern;
import java.lang.Math.*;
import java.io.*;


public class recursion
{
	public static int cnt = 0;
	public static void main(String args[]) throws IOException
	{
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date now = new Date();
		System.out.println( dft.format(now) + " start");
		
		int length = Integer.parseInt(args[0]);
		double[] start_array = producer(length);
		now = new Date();
		System.out.println( dft.format(now) + " array produced " + length);
		//System.out.println( Arrays.toString(start_array) );
		
		double[] end_array1 = sort(start_array);
		now = new Date();
		System.out.println( dft.format(now) + " sorted by sort()");
		//System.out.println( Arrays.toString(end_array1) );
		
		try
		{
			double[] end_array2 = Qsort(end_array1);
			System.out.println( "Qsort "+ cnt);
		}
		catch(Throwable e)
		{
			System.out.println( "Qsort "+ cnt);
			System.err.println( "catch exception: " + e.getMessage());//
			return; // e;
		}
		now = new Date();
		System.out.println( dft.format(now) + " sorted by Qsort()");
		//System.out.println( Arrays.toString(end_array2) );
		
		demo(length);
		now = new Date();
		System.out.println( dft.format(now) + " done");
	}
	
	//simple function: produce a large 1D array
	public static double[] producer(int length)
	{
		double[] a = new double[length];
		for(int i = 0; i < length; i++)
			a[i] = Math.random()*100;//[0,100)
		return a;
	}
	
	//regular sort function: small->big
	public static double[] sort(double[] array)
	{
		double[] a = array;
		double temp = 0;
		for(int i = 0; i < a.length-1; i++)
			for(int j = i; j < a.length; j++)
				if(a[i] > a[j])
				{
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
		return a;
	}
	
	//quick sort function: small->big
	public static double[] Qsort(double[] array)
	{
		double temp = 0;
		double[] a = array;
		int len = a.length;
		cnt++;
		//System.out.println( Arrays.toString(a) );
		if(len <= 1)
		{
			return a;
		}
		int j = 0;
		for(int i = 1; i < len; i++)
			if(a[i] < a[0])
			{
				j++;
				temp = a[j];
				a[j] = a[i];
				a[i] = temp;
			}
		temp = a[j];
		a[j] = a[0];
		a[0] = temp;

		double[] a_L = new double[j+1];
		a_L = Arrays.copyOfRange(a, 0, j+1);//0~j
		double[] a_L_new = new double[a_L.length];
		a_L_new = Qsort(a_L);		
		//System.out.println( Arrays.toString(a_L) );
		
		double[] a_R = new double[len-j-1];
		a_R = Arrays.copyOfRange(a, j+1, len);//j+1~len-1
		double[] a_R_new = new double[a_R.length];
		a_R_new = Qsort(a_R);
		//System.out.println( Arrays.toString(a_R) );
		
		double[] a_LR = new double[a_L_new.length+a_R_new.length];
		System.arraycopy(a_L_new,0, a_LR, 0, a_L_new.length);
		System.arraycopy(a_R_new,0, a_LR, a_L_new.length, a_R_new.length);
		return a_LR;
	}
	
	//simple demo function
	public static void demo(int i)
	{
		//System.out.println("this is step: "+i);
		i--;
		if(i > 0)
			demo(i);
		else
			System.out.println("this is step: " + i);
	}
	
}
