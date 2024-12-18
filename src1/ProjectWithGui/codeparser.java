package ProjectWithGui;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;




public class codeparser {

	
    static int checkerror = 0 ;
	
	
	
public static int getCheckerror() {
		return checkerror;
	}
public static ArrayList<Short>readFile(String fileName) {
	ArrayList<Short> result = new ArrayList<Short>() ;
	String text ="" ;
	int res = 0 ;
	int res1 = 0 ;
	int res2 = 0 ;
    short res3 =0;
	int index =0 ;
	String x1 = "" ;
	String x2 = "" ;
	String x3 = "" ;
	String x4 = "";
	
	int check = 0 ;
	try {
		File f = new File(fileName);
		Scanner myReader = new Scanner(f);
		while (myReader.hasNextLine()) {
    		text = myReader.nextLine();
    		String [] x  = text.split(" ");
    		
    		switch (x[0]) {
    		
    		case "ADD":
			res=0;
			x1 = dectobin_4(res);
			if (x[1].length()>2) {
		    x4 = x[1].substring(1, 3) ;
			res1 =Integer.parseInt(x4);  					
			x2 = dectobin_6(res1);
			}
			else {
				res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
				x2 = dectobin_6(res1);	
			}
			if (x[2].length()>2) {
			    x4 = x[2].substring(1, 3) ;
				res2 =Integer.parseInt(x4);  					
				x3 = dectobin_6(res2);					}
				else {
					res2 =Character.getNumericValue(x[2].charAt(1)) ;  					
					x3 = dectobin_6(res2);	
				}
			
			
			break ;
    		case "SUB":
    			res=1;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			if (x[2].length()>2) {
    			    x4 = x[2].substring(1, 3) ;
    				res2 =Integer.parseInt(x4);  					
    				x3 = dectobin_6(res2);					}
    				else {
    					res2 =Character.getNumericValue(x[2].charAt(1)) ;  					
    					x3 = dectobin_6(res2);	
    				}
    			break ;
	
    		case "MUL":
    			res=2;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			if (x[2].length()>2) {
    			    x4 = x[2].substring(1, 3) ;
    				res2 =Integer.parseInt(x4);  					
    				x3 = dectobin_6(res2);					}
    				else {
    					res2 =Character.getNumericValue(x[2].charAt(1)) ;  					
    					x3 = dectobin_6(res2);	
    				}
    			break ;

    		case "MOVI":
    			res=3;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			res1 = Integer.parseInt(x[2]);  					
    			x3 = dectobin_6(res1);  			
    			break ;

    		case "BEQZ":
    			res=4;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			res1 = Integer.parseInt(x[2]);  					
    			x3 = dectobin_6(res1);  			
    			break ;
	
    		case "ANDI":
    			res=5;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			res1 = Integer.parseInt(x[2]);  					
    			x3 = dectobin_6(res1); 
    			break ;

    		case "EOR":
    			res=6;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			if (x[2].length()>2) {
    			    x4 = x[2].substring(1, 3) ;
    				res2 =Integer.parseInt(x4);  					
    				x3 = dectobin_6(res2);					}
    				else {
    					res2 =Character.getNumericValue(x[2].charAt(1)) ;  					
    					x3 = dectobin_6(res2);	
    				}
    			break ;

    		case "BR":
    			res=7;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			if (x[2].length()>2) {
    			    x4 = x[2].substring(1, 3) ;
    				res2 =Integer.parseInt(x4);  					
    				x3 = dectobin_6(res2);					}
    				else {
    					res2 =Character.getNumericValue(x[2].charAt(1)) ;  					
    					x3 = dectobin_6(res2);	
    				}
    			break ;
    			
    		case "SAL":
    			res=8;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			res1 = Integer.parseInt(x[2]);  					
    			x3 = dectobin_6(res1); 
    			break ;

    		case "SAR":
    			res=9;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			res1 = Integer.parseInt(x[2]);  					
    			x3 = dectobin_6(res1); 
    			break ;

    		case "LDR":
    			res=10;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			res1 = Integer.parseInt(x[2]);  					
    			x3 = dectobin_6(res1); 
    			break ;
	
    		case "STR":
    			res=11;
    			x1 = dectobin_4(res);
    			if (x[1].length()>2) {
    			    x4 = x[1].substring(1, 3) ;
    				res1 =Integer.parseInt(x4);  					
    				x2 = dectobin_6(res1);
    				}
    				else {
    					res1 =Character.getNumericValue(x[1].charAt(1)) ;  					
    					x2 = dectobin_6(res1);	
    				}
    			res1 = Integer.parseInt(x[2]);  					
    			x3 = dectobin_6(res1); 
    			break ;

			   }
    		
    	
    			
    		
 	 	x3=x1+x2+x3 ;
 	 	res3 = (short) integerfrmbinary(x3) ;
 	 	
 	 	if (check > 1024 ) {
 	 		System.out.print("THE INSTRUCTION MEMORY IS FULL");
 	 		break ;
 	 	}
 	 	else {
 	 		check ++ ;
 	 	}
 	 	
 	    if (!myReader.hasNextLine()) {
 	    	result.add(index, res3);
 	 	 	index ++ ;
 	 	   break ;
 	 	
 	    }
 	    else {
 	    	result.add(index, res3);
 	 	 	index ++ ;
 	    }
 	 	 
 	 	
 	 	
 	 	
		}
		checkerror = 0 ;
		myReader.close();
		

	} catch (FileNotFoundException  e) {
		checkerror = 1 ;
		
	}
    
	return result;
}
public static int integerfrmbinary(String str){
	int j=0;
	int pow=0;
	int i= str.length()-1;
	while(i>-1) {
		if(str.charAt(i)=='1') {
			j=j+power(2,pow);
		}
		i--;
		pow++;
	}

	return j;
}
public static int power(int x , int y) {
	int res=1;
	while(y!=0) {
		res=res*x;
		y--;
	}
	return res;
}

	public static String dectobin_6 (int num) {
		int binary[] = new int[6];
		int index = 0;
	    String x =""  ;
	    
		while(index < 6){
		  binary[index++] = num % 2;
		  num/=2;
		}
		for(int i = index-1; i >= 0; i--){
			x = x + "" +binary[i] ;
		}
		
		return x ;
	}
	public static String dectobin_4 (int num) {
		int binary[] = new int[4];
		int index = 0;
	    String x =""  ;
	    
		while(index < 4){
		  binary[index++] = num % 2;
		  num/=2;
		}
		for(int i = index-1; i >= 0; i--){
			x = x + "" +binary[i] ;
		}
		
		return x ;
	}
	public static void main (String [] args ) {
		 short[] result = new short [1024];
		 readFile("test.txt");       
       


	}
	
}
