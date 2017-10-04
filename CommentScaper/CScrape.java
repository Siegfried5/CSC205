import java.util.Scanner;
import java.io.*;


public class CScrape {
    public static void main(String[] args){
    	
    	System.out.println("Cscrape - R. Ponce");
    	
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Path to File: ");
        String FileName = keyboard.nextLine();
        
        String outFile = FileName.substring(0, FileName.indexOf(".java"))+".txt";

        int lineNum=0;

        try{
            File read_file = new File(FileName);
            Scanner read_line = new Scanner(read_file);
            PrintWriter out = new PrintWriter(new FileOutputStream(new File(outFile), true));

            while (read_line.hasNextLine()){
            	
                String line = read_line.nextLine();
                lineNum++;

                if ( line.contains("//") == true ){
                    //System.out.println(lineNum+": "+ line.substring( line.indexOf('/'), line.length() ));
                    out.println(line.substring(line.indexOf('/'), line.length())+"\n" );
                }


                if ( line.contains("/*") == true){

                    //System.out.println(lineNum+": "+line.replaceAll("\\t", ""));
                    out.println(line.replaceAll("\\t", "")+"\n");

                    while(!line.contains("*/")) //&& read_line.hasNextLine());
                    {
                    	lineNum++;
                        line = read_line.nextLine();
                        //System.out.println(lineNum+": "+line.replaceAll("\\t", ""));
                        out.println(line.replaceAll("\\t", "")+"\n");
                    }
                    lineNum++;
                }
                
                //if(line.contains("*/"))
                /*{
                    System.out.println(lineNum+": "+line.replaceAll("\\t", ""));
                    out.append(line.replaceAll("\\t", "")+"\n");
                    lineNum++;
                }*/
            }
            read_line.close();
            out.close();
        }
        catch(FileNotFoundException error){
            System.out.println("Unable to Find File '"+FileName+"'");
        }
        keyboard.close();
    }
}