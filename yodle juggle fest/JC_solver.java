import java.text.*;
import java.util.*;
import java.lang.Math.*;
import java.io.*;

public class JC_solver
{
	
	public static void main(String args[]) throws IOException
	{
		int c_cnt = 0;	//total count of C
		int j_cnt = 0;	//total count of J
		int jc_favo_cnt = 10;	//total count of j to C in favorite group 
		int element_cnt = 3;	//total count of element: normally, 3 H:E:P
		int Cteam_vol = 0;

		/*re-code using console input*/
		String fin_name = "jugglefest.txt";
		String fout_name = "out.txt";
		
		/* set start time tag */
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date start_time = new Date();
		System.out.println( dft.format(start_time) + " start");
		
		/* read input file */
		File fin = new File(fin_name);
		if (fin.exists() == false)
		{	//if input file does not exsit, done
			throw new FileNotFoundException(fin_name);
			//throw new Error("File: " + fin_name + "does not exist!");
		}
		else if (fin.isFile() == false)
		{
			throw new Error("File: " + fin_name + "is not a file!");
		}
		else if (fin.canRead() == false)
		{
			throw new Error("File: " + fin_name + "can not be read!");
			
		}
		RandomAccessFile fin_handler = new RandomAccessFile(fin,"r");//for multiple read
		FileReader fin_reader = new FileReader(fin);
		BufferedReader fin_br = new BufferedReader(fin_reader);// bufferio is much efficient, buffer reader is for char stream
		Scanner fin_Scanner = new Scanner(fin_br);
			/* 1st read input file, get C_cnt and J_cnt */
			while(fin_Scanner.hasNextLine())
			{
				String fin_line = fin_Scanner.nextLine();
				Scanner thisline = new Scanner(fin_line);
				if(thisline.hasNext())
				{
					switch(thisline.next().charAt(0))
					{
						case 'C':
						case 'c':
							c_cnt ++;
							break;
						case 'J':
						case 'j':
							j_cnt ++;
							break;
						default:
							break;
					}
				}
			}//while
			int[][] C = new int[c_cnt][element_cnt];
			int[][] J = new int[j_cnt][element_cnt];
			int[][] JC_favo = new int[j_cnt][jc_favo_cnt];
			//System.out.println( "C count: " + c_cnt + " J count: " + j_cnt);	
						
			/* 2nd read input file,  */
			fin_handler.seek(0);	// goto the beginning fo file, read again
			fin_reader = new FileReader(fin);
			fin_br = new BufferedReader(fin_reader);
			fin_Scanner = new Scanner(fin_br);
			
			while(fin_Scanner.hasNextLine())
			{
				String fin_line = fin_Scanner.nextLine();
				//System.out.println( fin_line);	
				Scanner thisline = new Scanner(fin_line);
				if(thisline.hasNext())
				{
					String thisString = thisline.next();
					if(thisString.equalsIgnoreCase("C"))
					{
						int i = Integer.parseInt( thisline.next().substring(1) );
						//System.out.println(i);
						for(int e = 0; e < element_cnt; e++)
						{
							C[i][e] = Integer.parseInt( thisline.next().substring(2) );
						}
					}
					else if(thisString.equalsIgnoreCase("J"))
					{

						int i = Integer.parseInt( thisline.next().substring(1) );
						//System.out.println(i);
						for(int e = 0; e < element_cnt; e++)
						{
							J[i][e] = Integer.parseInt( thisline.next().substring(2) );
						}
						
						int index = 0;
						String temp = thisline.next();
						Scanner thissubline = new Scanner(temp);
						thissubline.useDelimiter(",");
						while(thissubline.hasNext())
						{
							JC_favo[i][index] = Integer.parseInt( thissubline.next().substring(1) );
							index++;
						}
					}
				}
			}//while
		Date now_time = new Date();
		System.out.println( dft.format(now_time) + " file parsed @ ");	
		
		//for test
//		System.out.println("J array:");
//		for (int j = 0; j < j_cnt;j++)
//				System.out.println(J[j]);
			
		/* calc Js_value = J dot C */
		int[][] Js_value = new int[j_cnt][c_cnt];
		for (int j = 0; j < j_cnt;j++)
		{
			for(int c = 0; c < c_cnt; c++)
			{
				Js_value[j][c] = J[j][0]*C[c][0] + J[j][1]*C[c][1] + J[j][2]*C[c][2];
			}
		}
		now_time = new Date();
		System.out.println( dft.format(now_time) + " JdotC calculated @ ");
		
		
		
		/* set J to C order table */
		int[][] JC_order = new int[j_cnt][c_cnt];
		JC_order = get_JC_order(Js_value, JC_favo);
//		for(int i=0; i<j_cnt; i++)
//			System.out.println(Arrays.toString(JC_order[i]));
		now_time = new Date();
		System.out.println( dft.format(now_time) + " J to C re-order @ ");
		
		/* assign J to C*/
		Cteam_vol = j_cnt/c_cnt;
		int[][] C_assigned = new int[c_cnt][Cteam_vol+1];
		
		C_assigned = assign_J2C(Js_value, JC_order);
		now_time = new Date();
		System.out.println( dft.format(now_time) + " J to C assign @ ");
		
		/* write output file */
		File fout = new File(fout_name);
		if(fout.exists())
		{
			fout.delete();
		}
		fout.createNewFile();
		FileWriter fout_writer = new FileWriter(fout); 
		BufferedWriter fout_br = new BufferedWriter(fout_writer);
		
		{
			String thisline;
			int j_id;
			int c_id;
			int k;
			int i;
			int c;
			for(c = 0; c < c_cnt; c++)
			{
				thisline = String.format("C%d ", c);
				fout_br.write(thisline);
				for(i = 0; i < Cteam_vol-1; i++)
				{
					j_id = C_assigned[c][i];
					thisline = String.format("J%d ", j_id);
					fout_br.write(thisline);
					for(k = 0; k < jc_favo_cnt-1; k++)
					{
						c_id = JC_order[j_id][k];
						thisline = String.format("C%d:%d ", c_id, Js_value[j_id][c_id]);
						fout_br.write(thisline);
					}
					k = jc_favo_cnt-1;
					c_id = JC_order[j_id][k];
					thisline = String.format("C%d:%d,", c_id, Js_value[j_id][c_id]);
					fout_br.write(thisline);
				}
				
				i = Cteam_vol - 1;
				j_id = C_assigned[c][i];
				thisline = String.format("J%d ", j_id);
				fout_br.write(thisline);
				for(k = 0; k < jc_favo_cnt-1; k++)
				{
					c_id = JC_order[j_id][k];
					thisline = String.format("C%d:%d ", c_id, Js_value[j_id][c_id]);
					fout_br.write(thisline);
				}
				k = jc_favo_cnt-1;
				c_id = JC_order[j_id][k];
				thisline = String.format("C%d:%d", c_id, Js_value[j_id][c_id]);
				fout_br.write(thisline);
				fout_br.newLine();
			}
		}
		fout_br.flush();
		fout_br.close();
		/* set end time tag */
		now_time = new Date();
		System.out.println( dft.format(now_time) + " end @ ");
		
		int sum = 0;
		for(int i = 0; i<Cteam_vol; i++)
			sum = sum + C_assigned[1970][i];
		System.out.println("total number of C1970 is: " + sum);
		
	}//main
	
	
	
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
			for (c = jc_favor_cnt, d = 0; c < c_cnt; c++,d++)
			{
				JC_order[j][c] = J_trimed_order[j][d];
			}
		}

		return JC_order;
	}
	
	public static int[][] assign_J2C(int[][] Js_value, int[][] JC_order)
	{
		int j_cnt = Js_value.length;
		int c_cnt = Js_value[0].length;
		int team_vol = j_cnt/c_cnt;
		int[][] C_assign = new int[c_cnt][team_vol+1];
		int[]   order_id = new int[j_cnt];
		int c_id;
		int j_id;
		int temp_j_id;
		
		Arrays.fill(order_id, 0);
		for (int c = 0; c < c_cnt; c++)
			Arrays.fill(C_assign[c], -1);
		
		
		for(int j = 0; j < j_cnt; j++)
		{
			c_id = JC_order[j][ order_id[j] ];//get the C id in JtoC order
			C_assign[c_id][team_vol] = j;	//put the j id to the last of C assign group
			
			do
			{
				//get pop out j_id
				j_id = C_assign[c_id][team_vol];
				c_id = JC_order[j_id][ order_id[j_id] ];
				order_id[j_id]++;
				C_assign[c_id][team_vol] = j_id;
				
				//reorder in C assign group
				for(int m = 0; m < team_vol; m++)
				{
					for(int n = m + 1; n < team_vol + 1; n++)
					{
						if(C_assign[c_id][m] == -1 && C_assign[c_id][n] != -1)
						{
							temp_j_id = C_assign[c_id][m];
							C_assign[c_id][m] = C_assign[c_id][n];
							C_assign[c_id][n] = temp_j_id;
						}	
						else if (C_assign[c_id][m] != -1 && C_assign[c_id][n] != -1)
						{
							if(Js_value[ C_assign[c_id][m] ][c_id] < Js_value[ C_assign[c_id][n] ][c_id])
							{
								temp_j_id = C_assign[c_id][m];
								C_assign[c_id][m] = C_assign[c_id][n];
								C_assign[c_id][n] = temp_j_id;
							}
						}
					}
				}
			}//while
			while(C_assign[c_id][team_vol] != -1);
		}
//		for(int c = 0;c < c_cnt; c++)
//			System.out.println( Arrays.toString(C_assign[c]) );		
		
		return C_assign;

	}	
	
}

