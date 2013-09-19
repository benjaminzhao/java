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
		double[] array = producer(100);
		demo(0);
	}
	
	//simple function: produce a large 1D array
	public static double[] producer(int length)
	{
		double[] a = double[length];
		for(int i = 0; i < length; i++)
			a[i] = Math.random()*100;
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
