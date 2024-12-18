package ProjectWithGui;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class processor {

    static short pc  = 0 ; 
    static byte  SREG = 0 ;
    
    static byte registerFile [] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
    	      ,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
  
	static int opcode = 0;  
	static int r1 = 0;     
	static	int r2 = 0;      
	static	int imm = 0;     
	static	int address = 0; 
    static int valueR1= 0;
    static int valueR2 = 0;

    
	static int clk=1;
	
	
	static short MemInst = 0;
   	static int fetchedInst=1;
   	static int decodedInst=1;
   	static int excutedInst=1;
	
	
   	static InstructionMemorY x =new InstructionMemorY() ;
	static ArrayList<Short> InstructionMemory = x.InstructionMemory ;
	
	
	 static DataMemory x1 = new DataMemory() ;
	  static byte [] DataMemory  = x1.Data_Memory ;
		
	
  //  static short Instructions= ((short) InstructionMemory.size());
	 static short NofInstructions= 0;
	  
	static Scanner sc = new Scanner(System.in);
	static String filename = "" ;
	
	static int flag1 = 0 ;
	static int flag2 = 0 ;
	static int flag3 = 0 ;

    static String sregvalue = "" ;
	static ArrayList<Short> p = new ArrayList<Short>(1024) ;
	
    public  void processor () {
    	intialize(filename);
    	// check the name of the test true 
    	
    	NofInstructions= ((short) InstructionMemory.size());
    	System.out.println("PIPLINE STAGES : " );
    	
    	
    	run();
    	 System.out.println(" ");
    	 System.out.println("---------------* INSTRUCTIONS ARE FINISHED *-----------------" );
         System.out.println(" ");
         System.out.println("THE CONTENT OF ALL REGISTERS ARE : ");
         printregisters(registerFile) ;
         System.out.println(" ");
         System.out.println("THE CONTENT OF INSTRUCTION MEMORY ARE : ");
         printinstructionmemory(p) ;
         System.out.println(" ");
         System.out.println("THE CONTENT OF DATA MEMORY ARE : ");
         printdatamemory(DataMemory) ;
         System.out.println(" ");
    	 System.out.println("---------------* FINALLY  WE HAVE FINISHED *-----------------" );
        	 
        	 
    }
	
	
    /// this method fill the instruction memory with instruction in the text after parsing it to binary 
	
public static void  intialize (String x) {
    	
    	
    	InstructionMemory = codeparser.readFile(filename);
    	p = codeparser.readFile(filename);
    	System.out.println("");
 	
    }
public static void setFilename(String filename) {
	processor.filename = filename;
}
    
   // fetch 
   
    public static short fetch() {
        
    	short instruction =0;
        
    	   
    		instruction= InstructionMemory.get(pc) ;
            pc ++ ;
            
    	
    	
    
        return instruction ; 
        
    }
    
    public static void decode(short instruction) {
    	opcode =  (0b1111000000000000 &  instruction) >>> 12;
    	
        r1 =  (0b0000111111000000 &  instruction) >>> 6 ;
    	//____________ R-Type
    	if(opcode == 0 || opcode == 1 || opcode == 2 ||
    			opcode == 6 || opcode == 7) {
    	    r2 =  (0b0000000000111111 &  instruction) ;
    		 		
    	}
    	
    	//____________ I-Type
    	else if (opcode == 3 || opcode == 4 || opcode == 5 ||
    			opcode == 8 || opcode == 9) {
    	    imm =  0b0000000000111111 &  instruction  ;

    		
    	}
    	else {
    		address = 0b0000000000111111 &  instruction ;
    	}
    	
    	
    	valueR1 = registerFile[r1];
		valueR2 = registerFile[r2];
		
	

    	
    	
    }
    public static void excute() {
    	
    	byte A = registerFile[r1] ;
		byte B = registerFile[r2];
		int cout = 0 ;
        int  cout1 = 0 ;
        int bitA  = 0 ;
        int  bitB = 0;
        flag1 = 0 ;
    	flag2 = 0 ;
    	flag3 = 0 ;

    	SREG = 0 ;
    	sregvalue =dectobin_8(SREG) ;
	    System.out.println("The OLD Value of SREG" + " is :"  +SREG +" IN BINARY : " + sregvalue);

    	switch (opcode) {
		
    	
    	// ADD **************************
		case 0:
			
		    System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
		    System.out.println("The second input is R" +r2+ " which : "  +valueR2 );
		    System.out.println("and the opreation is ADD and the opcode is " + opcode );

		    System.out.println("The Old Value of R" +r1+ " was :"  +valueR1);
			valueR1 = valueR1 + valueR2 ;
			registerFile[r1] = (byte) valueR1 ;
		    System.out.println("The new Value of R" +r1+ " is :"  +registerFile[r1]);
			
			
			// ****************************
            // Carry Flag
			
			if (valueR1 > 127) {
            	SREG = (byte) (SREG | 0b00010000) ;

            }
			else {
            	SREG = (byte) (SREG & 0b11101111) ;

			}
			
			// overflow flag
			//********************************
			
			
  
            for(int i = 0 ; i < 8 ; ++i){
              
            	 if(( A & (1<<i)) != 0){
                     bitA = 1;
            	  }
                 else 
            	     bitA = 0;
            	       
            	 if((B& (1<<i)) != 0){
            	     bitB = 1;
            	   }
            	    else 
            	     bitB = 0;
            	 
            	
            	 if ( cout1 + bitA + bitB >= 2) {
                	 cout1 = 1 ; 
                 }
            	 else {
                	 cout1 = 0; 

            	 }
            	 if (i == 6 ) {
                	 if ( cout1 + bitA + bitB >= 2) {
                	 cout = 1 ; 
                 }
                 }
            	}
             
            
            int V = cout ^ cout1 ;
            if (V == 1) {
            SREG =(byte) (SREG | 0b00001000) ;
            }
            else {
            	SREG = (byte) (SREG & 0b11110111) ;

			}
            
            cout = 0 ;
            cout1 = 0 ;
            bitA  = 0 ;
            bitB = 0;
            
            // Negative Flag
            // *****************************
            
            if (valueR1 <0) {
            	SREG =(byte) (SREG | 0b00000100) ;
            }
            else {
            	SREG = (byte) (SREG & 0b11111011) ;

			}
            
            // sign Flag
            // *********************
            int V1 = 0 ;
            int V2 =0 ;
            
           if   ( ((SREG) & 0b00001000) == 8 ) {
        	     V1 = 1 ;
           }
           else {
        	   V1 =0 ;
           }
           if   ( ((SREG)  & 0b00000100) == 4 ) {
      	     V2 = 1 ;
            }
           else {
      	   V2 =0 ;
           }
           int V3 = V1 ^ V2  ;
           if (V3 == 1) {
               SREG =(byte) (SREG | 0b00000010) ;
               }
               else {
               	SREG = (byte) (SREG & 0b11111101) ;

   			}
           // zero flag 
           //****************************
           
           
           if (valueR1 == 0) { 
        	   SREG =(byte) (SREG | 0b00000001) ;
             }
           else {
        	   SREG = (byte) (SREG & 0b11111110) ;
           }
             sregvalue =dectobin_8(SREG) ;
		    System.out.println("The new  Value of SREG" + " is :"  +SREG + " IN BINARY : " + sregvalue);
            break ;
            
         // SUB *********************
            
		case 1 : 
			
			
			System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
		    System.out.println("The second input is R" +r2+ " which : "  +valueR2 );
		    System.out.println("and the opreation is SUB and the opcode is " + opcode );

		    System.out.println("The Old Value of R" +r1+ " was :"  +valueR1);
			valueR1 = valueR1 - valueR2 ;
			registerFile[r1] = (byte) valueR1 ;
		    System.out.println("The new Value of R" +r1+ " is :"  +registerFile[r1]);
			
			
			// ****************************
            // Carry Flag
			
			
			
            if (valueR1 > 127) {
            	SREG = (byte) (SREG | 0b00001000) ;
            }
            else {
            	SREG = (byte) (SREG & 0b11101111) ;

			}
            cout1 = 1 ;

            for(int i = 0 ; i < 8 ; ++i){
                
           	 if(( A & (1<<i)) != 0){
                    bitA = 1;
           	  }
                else 
           	     bitA = 0;
           	       
           	 if((B& (1<<i)) != 0){
           	     bitB = 0;
           	   }
           	    else 
           	     bitB = 1;
           	 
           	
           	 if ( cout1 + bitA + bitB  >= 2) {
               	 cout1 = 1 ; 
                }
           	 else {
               	 cout1 = 0; 

           	 }
           	 if (i == 6 ) {
               	 if ( cout1 + bitA + bitB >= 2) {
               	 cout = 1 ; 
                }
                }
           	}
            
          
           
           int V4 = cout ^ cout1 ;
           if (V4 == 1) {
           SREG =(byte) (SREG | 0b00001000) ;
           }
           else {
           	SREG = (byte) (SREG & 0b11110111) ;

			}
           cout = 0 ;
           cout1 = 0 ;
           bitA  = 0 ;
           bitB = 0;
            
           // Negative Flag
           // *****************************
           
           if (valueR1 <0) {
           	SREG =(byte) (SREG | 0b00000100) ;
           }
           else {
           	SREG = (byte) (SREG & 0b11111011) ;

			}
           
           // sign Flag
           // *********************
           int V5 = 0 ;
           int V6 =0 ;
           
          if   ( ((SREG) & 0b00001000) == 8 ) {
       	     V5 = 1 ;
          }
          else {
       	   V5 =0 ;
          }
          if   ( ((SREG)  & 0b00000100) == 4 ) {
     	     V6 = 1 ;
           }
          else {
     	   V6 =0 ;
          }
          int V7 = V5 ^ V6  ;
          if (V7 == 1) {
              SREG =(byte) (SREG | 0b00000010) ;
              }
              else {
              	SREG = (byte) (SREG & 0b11111101) ;

  			} 
          // zero flag 
          //****************************
          
          
          if (valueR1 == 0) { 
       	   SREG =(byte) (SREG | 0b00000001) ;
            }
          else {
       	   SREG = (byte) (SREG & 0b11111110) ;
          }
          
            
            
            sregvalue =dectobin_8(SREG) ;
		    System.out.println("The new  Value of SREG" + " is :"  +SREG + " IN BINARY : " + sregvalue);
            break ;
			
            
         // MUL *********************
            
		case 2 :
			
			
			System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
		    System.out.println("The second input is R" +r2+ " which : "  +valueR2 );
		    System.out.println("and the opreation is MUL and the opcode is " + opcode );

		    
		    System.out.println("The Old Value of R" +r1+ " was :"  +valueR1);
			valueR1 = valueR1 * valueR2 ;
			registerFile[r1] = (byte) valueR1 ;
		    System.out.println("The new Value of R" +r1+ " is :"  +registerFile[r1]);
			// Negative Flag
	           // *****************************
	           
	           if (valueR1 <0) {
	           	SREG =(byte) (SREG | 0b00000100) ;
	           }
	           else {
	           	SREG = (byte) (SREG & 0b11111011) ;

				}
	           
	           
	          
	          // zero flag 
	          //****************************
	          
	          
	          if (valueR1 == 0) { 
	       	   SREG =(byte) (SREG | 0b00000001) ;
	            }
	          else {
	       	   SREG = (byte) (SREG & 0b11111110) ;
	          }
	          
	            
	            
	            sregvalue =dectobin_8(SREG) ;
			    System.out.println("The new  Value of SREG" + " is :"  +SREG + " IN BINARY : " + sregvalue);
	            break ;
			
	            
	         // MOVI *********************  
	            
	            
		case 3 :
			System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
		    System.out.println("The second input is immediate and it is value : " + imm );
		    System.out.println("and the opreation is MOVI and the opcode is " + opcode );

		    
		    System.out.println("The Old Value of R" +r1+ " was :"  +valueR1);
			valueR1 = imm ;
			registerFile[r1] = (byte) valueR1 ;
		    System.out.println("The new Value of R" +r1+ " is :"  +registerFile[r1]);

			
			 break ;
			
            
		 // BEQZ ****************
		case 4 :
	        System.out.println("The OLD value of PC is : " + (pc-1));
	        System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
		    System.out.println("and the opreation is BEQZ and the opcode is " + opcode );

	        flag1 = 1 ;
			if (valueR1 == 0 ) {
				pc = (short) (pc +imm) ;
		        System.out.println("The new value of PC is : " + (pc));
		        flag3 = 1 ;

			}
			else {
		        System.out.println("The value of PC still : " + (pc));
		        flag3 = 0 ;

			}
			
             break ;

            /// ANDI **************     
		case 5 : 
			
		        System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
			    System.out.println("The second input is immediate and it is value : " + imm );
		        System.out.println("and the opreation is ANDI and the opcode is " + opcode );
			
			    System.out.println("The Old Value of R" +r1+ " was :"  +valueR1);

			valueR1 = valueR1 & imm ;
			registerFile[r1] = (byte) valueR1 ;
		    System.out.println("The new Value of R" +r1+ " is :"  +registerFile[r1]);

			
			// Negative Flag
	           // *****************************
	           
	           if (valueR1 <0) {
	           	SREG =(byte) (SREG | 0b00000100) ;
	           }
	           else {
	           	SREG = (byte) (SREG & 0b11111011) ;

				}
	           
	           
	          
	          // zero flag 
	          //****************************
	          
	          
	          if (valueR1 == 0) { 
	       	   SREG =(byte) (SREG | 0b00000001) ;
	            }
	          else {
	       	   SREG = (byte) (SREG & 0b11111110) ;
	          }

	          sregvalue =dectobin_8(SREG) ;
			   System.out.println("The new  Value of SREG" + " is :"  +SREG + " IN BINARY : " + sregvalue);

			 break ;
			
		case 6 :
			
			System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
		    System.out.println("The second input is R" +r2+ " which : "  +valueR2 );
		    System.out.println("and the opreation is EOR and the opcode is " + opcode );

		    
		    System.out.println("The Old Value of R" +r1+ " was :"  +valueR1);
			
			valueR1 = valueR1 ^ valueR2 ;
			registerFile[r1] = (byte) valueR1 ;
		    System.out.println("The new Value of R" +r1+ " is :"  +registerFile[r1]);

			
			// Negative Flag
	           // *****************************
	           
	           if (valueR1 <0) {
	           	SREG =(byte) (SREG | 0b00000100) ;
	           }
	           else {
	           	SREG = (byte) (SREG & 0b11111011) ;

				}
	           
	           
	          
	          // zero flag 
	          //****************************
	          
	          
	          if (valueR1 == 0) { 
	       	   SREG =(byte) (SREG | 0b00000001) ;
	            }
	          else {
	       	   SREG = (byte) (SREG & 0b11111110) ;
	          }
	          
	          sregvalue =dectobin_8(SREG) ;
			  System.out.println("The new  Value of SREG" + " is :"  +SREG + " IN BINARY : " + sregvalue);

	        break ; 
	        
		case 7 :
			
			 System.out.println("The OLD value of PC is : " + pc);
		        System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
		        System.out.println("The second input is R" +r2 + " which : "  +valueR2 );
			    System.out.println("and the opreation is BR and the opcode is " + opcode );
			
			pc  = (short) ((( (short)valueR1 << 8) | valueR2) -1)  ;
			registerFile[r1] = (byte) valueR1 ;
			flag2 = 1 ;

			
	        System.out.println("The new value of PC is : " + (pc+1));

			break ;
			
	    
		case  8 :
			
			  System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
			    System.out.println("The second input is immediate and it is value : " + imm );
			    System.out.println("and the opreation is SAL and the opcode is " + opcode );
			
			    System.out.println("The Old Value of R" +r1+ " was :"  +valueR1);

			
			valueR1 = valueR1 <<  imm ;
			registerFile[r1] = (byte) valueR1 ;
		    System.out.println("The new Value of R" +r1+ " is :"  +registerFile[r1]);

			
			// Negative Flag
	           // *****************************
	           
	           if (valueR1 <0) {
	           	SREG =(byte) (SREG | 0b00000100) ;
	           }
	           else {
	           	SREG = (byte) (SREG & 0b11111011) ;

				}
	           
	           
	          
	          // zero flag 
	          //****************************
	          
	          
	          if (valueR1 == 0) { 
	       	   SREG =(byte) (SREG | 0b00000001) ;
	            }
	          else {
	       	   SREG = (byte) (SREG & 0b11111110) ;
	          }
			
	          sregvalue =dectobin_8(SREG) ;
			    System.out.println("The new  Value of SREG" + " is :"  +SREG + " IN BINARY : " + sregvalue);

			break ;
			
			
		case 9 :
			
			  System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
			    System.out.println("The second input is immediate and it is value : " + imm );
			    System.out.println("and the opreation is SAR and the opcode is " + opcode );
			
			    System.out.println("The Old Value of R" +r1+ " was :"  +valueR1);

			
			valueR1 = valueR1 >>  imm ;
			registerFile[r1] = (byte) valueR1 ;
		    System.out.println("The new Value of R" +r1+ " is :"  +registerFile[r1]);

			
			// Negative Flag
	           // *****************************
	           
	           if (valueR1 <0) {
	           	SREG =(byte) (SREG | 0b00000100) ;
	           }
	           else {
	           	SREG = (byte) (SREG & 0b11111011) ;

				}
	           
	           
	          
	          // zero flag 
	          //****************************
	          
	          
	          if (valueR1 == 0) { 
	       	   SREG =(byte) (SREG | 0b00000001) ;
	            }
	          else {
	       	   SREG = (byte) (SREG & 0b11111110) ;
	          }
			
	          sregvalue =dectobin_8(SREG) ;
			    System.out.println("The new  Value of SREG" + " is :"  +SREG + " IN BINARY : " + sregvalue);

			break ;
			
			
			case 10 : 
				
				  System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
				  System.out.println("The second input is address and it is value : " + address );
				    System.out.println("and the opreation is LDR and the opcode is " + opcode );

				if (address < 2048) { 
                     if (valueR1 != DataMemory[address]) {
         			    System.out.println("The new Value of R" +r1+ " is :"  + DataMemory[address]);

                     }
                     else {	
			    System.out.println("The Value of R" +r1+ " still :"  +valueR1);
                     }
			    valueR1 = DataMemory[address] ;

				}
				else {
					System.out.print("ERROR ADDRESS SORRY > 2048 ");
				}
				
			break ;
			
			
			case 11 :
				
				 System.out.println("The first input is R" +r1+ " which : "  +valueR1 );
				  System.out.println("The second input is address and it is value : " + address );
				    System.out.println("and the opreation is STR and the opcode is " + opcode );

				
				if (address < 2048) { 
					byte o = DataMemory[address] ;
					DataMemory[address]  = (byte) valueR1 ;
				    System.out.println("The address " + address + " in the memory was : " + o + "  and now it is updated to : " +(byte) valueR1);

					}
					else {
						System.out.print("ERROR ADDRESS sorry > 2048 ");
					}
					
				break ;
			   
			
    	}
    }
    
    
    
    //// this method responsible for the pipline stages 
    
        public static void  run () {
        	
        	if  (pc >= InstructionMemory.size() ) {
        		System.out.println("There are no instructions in the file OR the instructions have been finished rerun the program again");
        		
        	}
        	else {
        	
        	
        	short MemInst1 = 0 ;
        	short MemInst2 = 0 ;

        	int fetchedInst1=1;
        	int decodedInst1=1;
        	int excutedInst1=1;
       	 
         
         for (int i =0 ; i < 3 + ((InstructionMemory.size()-1) *1 ) ; i ++  ) { 
        	 
          	
        	   if (i == 0 )    {
                   System.out.println("----------------------------------------------" );
        		   System.out.println("CYCLE NUMBER " + clk + " :");
        		   MemInst = fetch() ; 
        		   System.out.println("ISTRUCTION NUMBER " + fetchedInst + " which : " + MemInst + " has been fetched ");
        		   fetchedInst ++ ;
        		   fetchedInst1 ++ ;


                   clk ++ ;
        	   }
        	   else {
        		   if (i == 1) {
        	           System.out.println("----------------------------------------------" );
        			   System.out.println("CYCLE NUMBER " + clk);
        			    MemInst1 = MemInst ;
        			//    MemInst2 = MemInst1 ;
        			    if (fetchedInst1<InstructionMemory.size()+1 && fetchedInst<InstructionMemory.size()+1) {    			 
        			   MemInst = fetch() ; 
             		   System.out.println("ISTRUCTION NUMBER " + fetchedInst + " which : " + MemInst + " has been fetched ");
             		   fetchedInst ++ ;
             		   fetchedInst1 ++ ;

        			    }
        			   decode (MemInst1);
        			   System.out.println("ISTRUCTION NUMBER " + decodedInst + " which : " + MemInst1 + " has been decoded ");
        			   decodedInst ++ ;
        			   decodedInst1 ++ ;
            		   clk ++ ;
        		   }
        		   else {
        			   int pc1 =pc ;
        			   if (excutedInst1<InstructionMemory.size()+1 && excutedInst<InstructionMemory.size()+1) { 
        		          	 System.out.println("----------------------------------------------" );
        			   
        			   System.out.println("CYCLE NUMBER " + clk);
            		   System.out.println("ISTRUCTION NUMBER " + excutedInst + " which : " + MemInst1 + " has been EXECUTED " +"And these are what happens : ");
            		   
        			   excute();
                       excutedInst ++ ;
                       excutedInst1 ++ ;
                       int pc2 =pc ;
                       MemInst1 = MemInst ;
                       
                       if (flag1 == 1 ) {
                      		
                      		if (pc>=NofInstructions+1) { 
                      			pc =(short) pc1 ;
                      			if (fetchedInst1<InstructionMemory.size()+1 && fetchedInst<InstructionMemory.size()+1) {
                       			    MemInst1 = MemInst ;
                       			    MemInst = fetch() ; 
                                	   
                       			  
                            	   System.out.println("ISTRUCTION NUMBER " + fetchedInst + " which : " + MemInst + " has been fetched ");
                            	   fetchedInst ++ ;
                            	   fetchedInst1 ++ ;

                                   }
                                   if (decodedInst1<InstructionMemory.size()+1 && decodedInst<InstructionMemory.size()+1) {
                           	       System.out.println("ISTRUCTION NUMBER " + decodedInst + " which : " + MemInst1 + " has been decoded ");
                       			   decode (MemInst1);
                       			   decodedInst ++ ;
                       			   decodedInst1 ++ ;

                                   }
                      			System.out.println("");
                      			System.out.println("**THER ARE NO MORE INSTRUCTION AFTER THE BEQZ**");
                      			 System.out.println(" ");
                      	    	 System.out.println("---------------* INSTRUCTIONS ARE FINISHED *-----------------" );
                      	         System.out.println(" ");
                      	         System.out.println("THE CONTENT OF ALL REGISTERS ARE : ");
                      	         printregisters(registerFile) ;
                      	         System.out.println(" ");
                      	         System.out.println("THE CONTENT OF INSTRUCTION MEMORY ARE : ");
                      	         printinstructionmemory(p) ;
                      	         System.out.println(" ");
                      	         System.out.println("THE CONTENT OF DATA MEMORY ARE : ");
                      	         printdatamemory(DataMemory) ;
                      	         System.out.println(" ");
                      	    	 System.out.println("---------------* FINALLY  WE HAVE FINISHED *-----------------" );
                      			 System.exit(0) ;                     		}
                      		else {

                      		if (flag3 == 1 ) {
                      		
                      		flag1 = 0;
                      		pc =(short) pc1;
                      		if (fetchedInst1<InstructionMemory.size()+1 && fetchedInst<InstructionMemory.size()+1) {
                   			    MemInst1 = MemInst ;
                   			    MemInst = fetch() ; 
                            	   
                   			  
                        	   System.out.println("ISTRUCTION NUMBER " + fetchedInst + " which : " + MemInst + " has been fetched ");
                        	   fetchedInst ++ ;
                        	   fetchedInst1 ++ ;

                               }
                               if (decodedInst1<InstructionMemory.size()+1 && decodedInst<InstructionMemory.size()+1) {
                       	       System.out.println("ISTRUCTION NUMBER " + decodedInst + " which : " + MemInst1 + " has been decoded ");
                   			   decode (MemInst1);
                   			   decodedInst ++ ;
                   			   decodedInst1 ++ ;

                               }
                               pc = (short) pc2 ;
                            fetchedInst = pc ;
                            excutedInst = pc ;
                            decodedInst = pc ;
                            fetchedInst1 = pc ;
                            excutedInst1= pc ;
                            decodedInst1 =pc ;
                      		pc = (short) (pc -1);
                        	clk ++ ;
                        	flag3 = 0 ;
                      		run () ;
                      		}	
                      		
                      		}
                      	}
                          
                   	else if (flag2 == 1 ) {
                   		
                   		if (pc>=NofInstructions) {  
                   			pc =(short) pc1 ;
                  			if (fetchedInst1<InstructionMemory.size()+1 && fetchedInst<InstructionMemory.size()+1) {
                   			    MemInst1 = MemInst ;
                   			    MemInst = fetch() ; 
                            	   
                   			  
                        	   System.out.println("ISTRUCTION NUMBER " + fetchedInst + " which : " + MemInst + " has been fetched ");
                        	   fetchedInst ++ ;
                        	   fetchedInst1 ++ ;

                               }
                               if (decodedInst1<InstructionMemory.size()+1 && decodedInst<InstructionMemory.size()+1) {
                       	       System.out.println("ISTRUCTION NUMBER " + decodedInst + " which : " + MemInst1 + " has been decoded ");
                   			   decode (MemInst1);
                   			   decodedInst ++ ;
                   			   decodedInst1 ++ ;

                               }
                  			System.out.println("");
                  			System.out.println("**THER ARE NO MORE INSTRUCTION AFTER THE BR**");
                 			 System.out.println(" ");
                  			System.out.println("---------------* INSTRUCTIONS ARE FINISHED *-----------------" );
                 	         System.out.println(" ");
                 	         System.out.println("THE CONTENT OF ALL REGISTERS ARE : ");
                 	         printregisters(registerFile) ;
                 	         System.out.println(" ");
                 	         System.out.println("THE CONTENT OF INSTRUCTION MEMORY ARE : ");
                 	         printinstructionmemory(p) ;
                 	         System.out.println(" ");
                 	         System.out.println("THE CONTENT OF DATA MEMORY ARE : ");
                 	         printdatamemory(DataMemory) ;
                 	         System.out.println(" ");
                 	    	 System.out.println("---------------* FINALLY  WE HAVE FINISHED *-----------------" );
                 			 System.exit(0) ;   
                  		}
                  		else {
                  		
                  			pc =(short) pc1 ;
                  			if (fetchedInst1<InstructionMemory.size()+1 && fetchedInst<InstructionMemory.size()+1) {
                   			    MemInst1 = MemInst ;
                   			    MemInst = fetch() ; 
                            	   
                   			  
                        	   System.out.println("ISTRUCTION NUMBER " + fetchedInst + " which : " + MemInst + " has been fetched ");
                        	   fetchedInst ++ ;
                        	   fetchedInst1 ++ ;

                               }
                               if (decodedInst1<InstructionMemory.size()+1 && decodedInst<InstructionMemory.size()+1) {
                       	       System.out.println("ISTRUCTION NUMBER " + decodedInst + " which : " + MemInst1 + " has been decoded ");
                   			   decode (MemInst1);
                   			   decodedInst ++ ;
                   			   decodedInst1 ++ ;

                               }
                               
                  		flag2 = 0;
                  		pc = (short) pc2 ;
                  		fetchedInst = pc+1 ;
                  		excutedInst = pc+1 ;
                  		decodedInst = pc+1;
                    	clk ++ ;
                  		run () ;
                        
                       
        			   }
                   	}
        			   }
        			   
           			    if (fetchedInst1<InstructionMemory.size()+1 && fetchedInst<InstructionMemory.size()+1) {
           			    MemInst1 = MemInst ;
           			    MemInst = fetch() ; 
                    	   
           			  
                	   System.out.println("ISTRUCTION NUMBER " + fetchedInst + " which : " + MemInst + " has been fetched ");
                	   fetchedInst ++ ;
                	   fetchedInst1 ++ ;

                       }
                       if (decodedInst1<InstructionMemory.size()+1 && decodedInst<InstructionMemory.size()+1) {
               	       System.out.println("ISTRUCTION NUMBER " + decodedInst + " which : " + MemInst1 + " has been decoded ");
           			   decode (MemInst1);
           			   decodedInst ++ ;
           			   decodedInst1 ++ ;

                       }
                       clk ++ ;
        		   }
        		   
        	   }
        	   
        	   }
        
         }
        }
    	
    	
         
    
    
    
    /// get the values of all registers 
    public static void printregisters(byte x []) {
    	System.out.println("");
    	for (int i = 0 ; i < x.length ; i ++ ) {
    		System.out.println("The content of register" + " R" + i + " is" + " : " + x[i]);
    	}
		System.out.println("The content of register" + " PC"  + " is" + " : " + pc);
		System.out.println("The content of register" + " Status Register" + " is" + " : " + SREG);
		System.out.println("");
		System.out.println("-------------------------------------------------");

    }
    // get the instructions in the instruction memory
    
    public static void printinstructionmemory (ArrayList<Short> x) {
    	for (int i = 0 ; i < x.size() ; i ++ ) {
    		System.out.println("INSTRUCTION NUMBER " + i + " is" + " : " + x.get(i));
    	}
		System.out.println("");
		System.out.println("THERE ARE NO MORE INSTRUCTION IN THE INSTRUCTION MEMORY");
		System.out.println("-------------------------------------------------");

    }
    
    // get the values in the memory 
    
    public static void printdatamemory (byte x[]) {
    	System.out.println("");
    	for (int i = 0 ; i < x.length ; i ++ ) {
    		System.out.println("MEMORY CELL NUMBER " + i + " is" + " : " + x[i]);
    	}
		System.out.println("");

    }
    
    public static String dectobin_8 (int num) {
		int binary[] = new int[8];
		int index = 0;
	    String x =""  ;
	    
		while(index < 8){
		  binary[index++] = num % 2;
		  num/=2;
		}
		for(int i = index-1; i >= 0; i--){
			x = x + "" +binary[i] ;
		}
		
		return x ;
	}
    
    public static void main (String [] args ) { 
    	
    	processor x = new processor() ;
    	}


	
	
	
}
