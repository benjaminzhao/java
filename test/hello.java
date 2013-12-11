import java.text.*;
import java.util.*;
import java.util.regex.Pattern;
import java.lang.Math.*;
import java.io.*;


public class hello{
	
	public static void main(String args[]) throws IOException{
		long buf = 0L;
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date starttimeDate = new Date();
		System.out.println( dft.format(starttimeDate) + " start");

		String fin_name = "test.txt";
		
		File fin = new File(fin_name);
		if (fin.exists() == false)	{/*if input file does not exsit, done*/
			throw new FileNotFoundException(fin_name);
			//throw new Error("File: " + fin_name + "does not exist!");
		}
		else if (fin.isFile() == false)	{
			throw new Error("File: " + fin_name + "is not a file!");
		}
		else if (fin.canRead() == false){
			throw new Error("File: " + fin_name + "can not be read!");
			
		}
		
		FileReader fin_reader = new FileReader(fin);
		/* buffer io is much efficient, buffer reader is for char stream */
		BufferedReader fin_br = new BufferedReader(fin_reader);		
		String fin_line;
		
		/*  */
		Scanner fin_Scanner = new Scanner(fin_br);
		int c_cnt = 0;
		int j_cnt = 0;
		int[][] C = new int[3][3];
		int[][] J = new int[12][3];
		int[][] JC_favo = new int[12][3];
		
		while(fin_Scanner.hasNextLine()){
			fin_line = fin_Scanner.nextLine();
			
			Scanner thisline = new Scanner(fin_line);
			
			//fin_line.split("[ ]+")
			
			if(thisline.hasNext()){
				String thisString = thisline.next();
				//System.out.println(thisString);
								
				if(thisString.equalsIgnoreCase("C")){
					int i = Integer.parseInt( thisline.next().substring(1) );
					//System.out.println(i);
					C[i][0] = Integer.parseInt( thisline.next().substring(2) );
					C[i][1] = Integer.parseInt( thisline.next().substring(2) );
					C[i][2] = Integer.parseInt( thisline.next().substring(2) );
					c_cnt ++;

				}
				else if(thisString.equalsIgnoreCase("J")){
					int i = Integer.parseInt( thisline.next().substring(1) );
					//System.out.println(i);
					J[i][0] = Integer.parseInt( thisline.next().substring(2) );
					J[i][1] = Integer.parseInt( thisline.next().substring(2) );
					J[i][2] = Integer.parseInt( thisline.next().substring(2) );
					int index = 0;
					
					String temp = thisline.next();
					Scanner thissubline = new Scanner(temp);
					thissubline.useDelimiter(",");
					while(thissubline.hasNext()){
						JC_favo[i][index] = Integer.parseInt( thissubline.next().substring(1) );
						index++;
					}
					j_cnt ++;
				}
			}
			//System.out.println(fin_line);
		}//while
		Date scanfiletimeDate = new Date();
		System.out.println( dft.format(scanfiletimeDate) + " file scaned");
		
		//int[][] C = { {10,10000,1000,1,100}, {1, 2, 3, 5, 4} };
		//int[][] J;
		//System.out.println(Arrays.toString(c));
		//int[] order = reorder(c);
		//System.out.println(Arrays.toString(order));
//		for(int i=0; i<3;i++)
//			System.out.println(Arrays.toString(C[i]));
//		for(int i=0; i<12; i++)
//			System.out.println(Arrays.toString(J[i]));
//		for(int i=0; i<12; i++)
//			System.out.println(Arrays.toString(JC_favo[i]));
		//int[][] JC_favo = { {1, 4}, {0,3} };
		//int[] order2 = retrim(c,favo);
		//System.out.println(Arrays.toString(order2));
		
		/*calc Js_value = J dot C*/
		
//		int[][] Js_value = new int[j_cnt][c_cnt];
//		for (int j = 0; j<j_cnt;j++)
//		{
//			for(int c = 0; c<c_cnt; c++)
//			{
//				Js_value[j][c] = J[j][0]*C[c][0] + J[j][1]*C[c][1] + J[j][2]*C[c][2];
//			}
//		}
		
//		Date JCcalctimeDate = new Date();
//		System.out.println( dft.format(JCcalctimeDate) + " J dot C calculated");
		
//		int[][] order3 = new int[j_cnt][c_cnt];
//		order3 = get_JC_order(Js_value,JC_favo);
//		for(int i=0; i<12; i++)
//			System.out.println(Arrays.toString(order3[i]));

		String fout_name = "JCorder.txt";
		File fout = new File(fout_name);
		if(fout.exists())
			fout.delete();
		fout.createNewFile();
		
		FileWriter fout_writer = new FileWriter(fout); 
		/* buffer io is much efficient, buffer reader is for char stream */
		BufferedWriter fout_br = new BufferedWriter(fout_writer);
		String thisline;
		
		int i,j;
		for(i=0; i<3; i++){
			thisline  = String.format("C C%d", i);
			fout_br.write(thisline);
			
				thisline  = String.format(" H:%d", C[i][0]);
				fout_br.write(thisline);
				thisline  = String.format(" E:%d", C[i][1]);
				fout_br.write(thisline);
				thisline  = String.format(" P:%d", C[i][2]);
				fout_br.write(thisline);
				fout_br.newLine();
			
		}
		fout_br.flush();
		fout_br.close();
		
		
		Date endtimeDate = new Date();
		System.out.println( dft.format(endtimeDate) + " end @ " + buf);
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
		
		for (i = 0; i< len; i++){
			order[i] = i;
		}
		
		for (i = 0; i < len-1; i++)	{
			for (j = i; j<len; j++)	{
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
	
	public static int[] retrim(int[] J, int[] J_f){ 
		/* remove the ith units of J, defined by J_f[] */
		int i,j,c,d;
		boolean found;
		
		int c_cnt = J.length;
		int jc_favor_cnt = J_f.length;
		
		int[] J_temp = new int[c_cnt-jc_favor_cnt];
		
			d = 0;
			for (c = 0; c < c_cnt; c++)	{
				found = false;
				for (i = 0; i < jc_favor_cnt; i++){
					if (c == J_f[i]){
						found = true;
						break;
					}
				}
				if (found == false)	{
					J_temp[d] = J[c];
					d++;
				}
			}

		return J_temp;
	}
	
	/* get the J assign to C order, favorite first, then order based on J_value, big->small */
	public static int[][] get_JC_order(int[][] Js_value, int[][] Js_favorC){   
		int i;
		int j;
		int c;
		int d;
		int temp,temp_id;
		boolean found = false;
		
		int j_cnt = Js_value.length;    /*Js_value = col number = total J number */
		int c_cnt = Js_value[0].length; /*c_cnt = row number = total C number */
		int jc_favor_cnt = Js_favorC[0].length; //

		
		if (j_cnt != Js_favorC.length)	{
			throw new Error("array's col number is not matched");
		}
		
		
		int[]   order = new int[c_cnt];
		int[][] JC_order = new int[j_cnt][c_cnt];
		System.out.println(c_cnt-jc_favor_cnt);
		int[][] J_trimed = new int[j_cnt][c_cnt-jc_favor_cnt];
		int[][] J_trimed_order = new int[j_cnt][c_cnt-jc_favor_cnt];
		
		for (j = 0; j < j_cnt; j++)	{
			for (c = 0; c < c_cnt; c++)	{
				order[c] = c;
			}
			/* trim Js_value, delete number in favorite group */
			d = 0;
			for (c = 0; c < c_cnt; c++)	{
				found = false;
				for (i = 0; i < jc_favor_cnt; i++){
					if (c == Js_favorC[j][i]){
						found = true;
						break;
					}
				}
				if (found == false)	{
					J_trimed[j][d] = Js_value[j][c];
					J_trimed_order[j][d] = order[c];
					d++;
				}
			}
			
			/* get the order array */
			//J_temp_order[j] = reorder(Js_value[j]);	

			for (c = 0; c < c_cnt-jc_favor_cnt-1; c++){
				for (i = c; i < c_cnt-jc_favor_cnt; i++){
					if (J_trimed[j][c] < J_trimed[j][i]){
						/*big->small*/
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
			for (c = 0; c < jc_favor_cnt; c++){
				JC_order[j][c] = Js_favorC[j][c];
			}
			/*assign non-favorite order*/
			for (c = jc_favor_cnt, d = 0; c < c_cnt; c++,d++){
				JC_order[j][c] = J_trimed_order[j][d];
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