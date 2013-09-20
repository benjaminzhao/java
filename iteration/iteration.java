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
		
		int length = 100000;
		double[] start_array = producer(length);
		now = new Date();
		System.out.println( dft.format(now) + " produced" + length);
		
		double[] end_array = sort(start_array);
		now = new Date();
		System.out.println( dft.format(now) + " sorted by sort()");
		
		double[] end_array = Qsort(start_array);
		now = new Date();
		System.out.println( dft.format(now) + " sorted by Qsort()");
		
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
		double[] a = array;
		double temp = 0;
		int index = a.length/2;
		
		temp = a[index];
		for(int i = 0; i < a.length; i++)
			if(a[i]<temp)
				a1[i] = [i];
		
		if(index > 1 && a.length-index > 0)
		{
			
		}
		double[] a1 = new double[index];
		double[] a2 = new double[a.length-index];
		
		Qsort(a1);
		Qsort(a2);
		
		return a;
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
