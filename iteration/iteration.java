import java.text.*;
import java.util.*;
import java.util.regex.Pattern;
import java.lang.Math.*;
import java.io.*;


public class iteration
{
	//public static int i = 0;
	
	public static void main(String args[]) throws IOException
	{
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date now = new Date();
		System.out.println( dft.format(now) + " start");
		
		int length = 4;
		double[] start_array = producer(length);
		now = new Date();
		System.out.println( dft.format(now) + " produced " + length);
		System.out.println( Arrays.toString(start_array) );
		
		double[] end_array1 = sort(start_array);
		now = new Date();
		System.out.println( dft.format(now) + " sorted by sort()");
		System.out.println( Arrays.toString(end_array1) );
		
		double[] end_array2 = Qsort(start_array);
		now = new Date();
		System.out.println( dft.format(now) + " sorted by Qsort()");
		System.out.println( Arrays.toString(end_array2) );
		
		demo(0);
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
		//System.out.println( Arrays.toString(a) );
		if(a.length <= 1)
		{
			return a;
		}
		int j = 0;
		for(int i = 1; i < a.length; i++)
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
		double[] a_R = new double[a.length-j-1];
		
		a_L = Arrays.copyOfRange(a, 0, j);
		a_R = Arrays.copyOfRange(a, j+1, a.length-1);
		System.out.println( Arrays.toString(a_L) );
		System.out.println( Arrays.toString(a_R) );
		
		double[] a_L_new = new double[a_L.length];
		a_L_new = Qsort(a_L);
		double[] a_R_new = new double[a_R.length];
		a_R_new = Qsort(a_R);
		
		double[] a_LR = new double[a_L_new.length+a_R_new.length];
		System.arraycopy(a_L_new,0, a_LR,0, a_L_new.length);
		System.arraycopy(a_R_new,0, a_LR,a_L_new.length, a_R_new.length);
		return a_LR;
	}
	
	//simple demo function
	public static void demo(int i)
	{
		System.out.println("this is step: "+i);
		i++;
		if(i < 10)
			demo(i);
	}
}
