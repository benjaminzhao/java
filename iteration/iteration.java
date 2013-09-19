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
		abc(0);
	}
	//simple demo function
	public static void abc(int i)
	{
		System.out.println("this is step: "+i);
		i++;
		if(i < 10)
			abc(i);
	}
}
