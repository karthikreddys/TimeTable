package TimeTable;
import java.io.*;

public class ClasstimeTable {
	
	public static String[][] readCSV(String path) throws FileNotFoundException, IOException {
	    try (FileReader fr = new FileReader(path);
	        BufferedReader br = new BufferedReader(fr)) {
	    	String[][] csvvalues=new String[10][7];
	    	int n=0;
	        for (String line = br.readLine(); line != null; line = br.readLine()) {
	        	
	            String[] lines=(line.split(",",7));
	            
	            for(int i=0;i<7;i++)
	            {
	            	csvvalues[n][i]=lines[i];
	            }
	            n++;
			 }
	        return csvvalues;
	    }
	}
	
	
	public static void main(String[] args) throws IOException  {
		
		String[] fName = {"InputCsv\\Teacher wise class timetable - Maths.csv","InputCsv\\Teacher wise class timetable - Science.csv","InputCsv\\Teacher wise class timetable - English.csv","InputCsv\\Teacher wise class timetable - Kannada.csv","InputCsv\\Teacher wise class timetable - Hindi.csv"};
		String[] sub= {"Maths","Science","English","Kannada","Hindi"};
		
	
		String[][] class6=new String[10][7];
		String[][] class7=new String[10][7];
		String[][] class8=new String[10][7];
		String[][] class9=new String[10][7];
		String[][] class10=new String[10][7];
		int z=0;
		
		int k=0;
		int r=0;
		//String h=null;
		String[][] InputData=null;
		//English=readCSV(fName[2]);
		for(int l=0;l<fName.length;l++)
		{
			InputData=readCSV(fName[l]);
			 
			 //System.out.println(English[0][0].split(","));
			for(int i=0 ;i<=InputData.length-1;i++) {
				for(int j=0 ;j<=InputData[i].length-1;j++) {
					//System.out.println(English[i][j]);
					if(InputData[i][j].equals("6th"))
					{
						class6[i][j]=sub[z];
					}
					else if(InputData[i][j].equals("7th"))
					{
						class7[i][j]=sub[z];
					}
					else if(InputData[i][j].equals("8th"))
					{
						class8[i][j]=sub[z];
					}

					else if(InputData[i][j].equals("9th"))
					{
						class9[i][j]=sub[z];
					}

					else if(InputData[i][j].equals("10th"))
					{
						class10[i][j]=sub[z];
					}
					else if(InputData[i][j].equals("Maths")||InputData[i][j].equals("Science")||InputData[i][j].equals("English")||InputData[i][j].equals("Kannada")|| InputData[i][j].equals("Hindi"))
					{
						continue;
					}
					
					
				}
			}
			z++;
		}
		
		handleTT(class6,"Class Six TT");
		handleTT(class7,"Class Seven TT");
		handleTT(class8,"Class Eight TT");
		handleTT(class9,"Class Ninth TT");
		handleTT(class10,"Class Tenth TT");
		System.out.print("Class Wise Time Table's are Generated and stored 'inOutPut TT' folder based on Teaches availability in InputCSV files.");
		
	}
	
	public static void writeData(String[][] array2D,String Filename) throws IOException {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i <= array2D.length-1; i++)//for each row
		{
		   for(int j = 0; j <= array2D[i].length-1; j++)//for each column
		   {
		      builder.append(array2D[i][j]+"");//append to the output string
		      if(j < array2D.length - 1)//if this is not the last row element
		         builder.append(",");//then add comma (if you don't like commas you can use spaces)
		   }
		   builder.append('\n');//append new line at the end of the row
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("OutPut TT\\"+Filename + ".csv"));
		writer.write(builder.toString());//save the string representation of the board
		writer.close(); 
   }
	
	public static void handleTT(String[][] array2D,String Filename) throws IOException {
		array2D[0][0] = Filename;
		String[] Time= {"","8:00 AM","9:00 AM","10:00 AM","11:00 AM","12:00 PM","1:00 PM","2:00 PM","3:00 PM","4:00 PM"};
		String[] Days= {"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		String[] sub= {"Maths","Science","English","Kannada","Hindi"};
		int z=0;
		int k=0;
		for(int i=1 ;i<array2D[0].length;i++) {
			for(int j=1 ;j<array2D.length;j++) {
				array2D[0][i] = Days[i];
				array2D[j][0] = Time[j];	
			}
		}
		for(int i=1 ;i<=array2D[0].length-1;i++) {
			String h="Hindi";
			int y=0;
			for(int j=1 ;j<=array2D.length-2;j++) {
				k=j+1;
				if(array2D[j][i]==null)
				{  
					array2D[j][i] = "Free Period";
					continue;
				}
				else if(array2D[j][i].equals(h) )
				{
					array2D[k][i]=sub[y];
					if(y==0||y==1||y==2)
					{
						h=sub[y];
						y++;
					}
					
				}	
				
			}
		}
		writeData(array2D,Filename);
	}
}



