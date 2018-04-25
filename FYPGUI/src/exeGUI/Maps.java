package exeGUI;

import java.util.HashMap;

//Class containing segregation of residues based on attributes
//Four HashMaps, one for each attribute

public class Maps {
	
	//Input protein sequence
	 public String protein;
	 
	 //Length of protein protein
	 public int length;
	 int[][] theOldCounts = new int [4][3];
	 public int stats = -1;
	 String label;
	 
	 //Each String Array for one attribute
	 String Array1="",Array2="",Array3="",Array4="";
	 
	 HashMap<Character,Character> hydro=new HashMap<Character,Character>();
	 HashMap<Character,Character> polarz=new HashMap<Character,Character>();
	 HashMap<Character,Character> polarity=new HashMap<Character,Character>();
	 HashMap<Character,Character> vander=new HashMap<Character,Character>();
	 
	 //Maps Constructor taking protein passed as command line argument
	 public Maps (String protein) 
	 { 
		 this.protein = protein;
		 this.length = this.protein.length();
		 hydro.put('R','H'); polarz.put('G','H'); polarity.put('L','H'); vander.put('G','H');
		 hydro.put('K','H'); polarz.put('A','H'); polarity.put('I','H'); vander.put('A','H');
		 hydro.put('E','H'); polarz.put('S','H'); polarity.put('F','H'); vander.put('S','H');
		 hydro.put('D','H'); polarz.put('C','H'); polarity.put('W','H'); vander.put('D','H');
		 hydro.put('Q','H'); polarz.put('T','H'); polarity.put('C','H'); vander.put('T','H');
		 hydro.put('N','H'); polarz.put('P','H'); polarity.put('M','H'); vander.put('C','E');
	 	
		 hydro.put('G','E'); polarz.put('D','H'); polarity.put('V','H'); vander.put('P','E');
		 hydro.put('A','E'); polarz.put('N','E'); polarity.put('Y','H'); vander.put('N','E');
	 	 hydro.put('S','E'); polarz.put('V','E'); polarity.put('P','E'); vander.put('V','E');
	 	 hydro.put('T','E'); polarz.put('E','E'); polarity.put('A','E'); vander.put('E','E');
	 	 hydro.put('P','E'); polarz.put('Q','E'); polarity.put('T','E'); vander.put('Q','E');
	 	 hydro.put('H','E'); polarz.put('I','E'); polarity.put('G','E'); vander.put('I','E');
	 	 hydro.put('Y','E'); polarz.put('L','E'); polarity.put('S','E'); vander.put('L','E');
	 
	 	 hydro.put('C','-'); polarz.put('M','-'); polarity.put('H','-'); vander.put('K','-');
	 	 hydro.put('V','-'); polarz.put('H','-'); polarity.put('Q','-'); vander.put('M','-');
	 	 hydro.put('L','-'); polarz.put('K','-'); polarity.put('R','-'); vander.put('H','-');
	 	 hydro.put('I','-'); polarz.put('F','-'); polarity.put('K','-'); vander.put('F','-');
	 	 hydro.put('M','-'); polarz.put('R','-'); polarity.put('N','-'); vander.put('R','-');
	 	 hydro.put('F','-'); polarz.put('Y','-'); polarity.put('E','-'); vander.put('Y','-'); 
	 	 hydro.put('W','-'); polarz.put('W','-'); polarity.put('D','-'); vander.put('W','-');
			 
	 	 this.setArray();
//		 this.CompositionFunction();
//		 this.TransitionFunction();
//		 this.DistributionFunction();
//		 this.PercentFrequencyFunction();
//		 this.VectorFormFunction();
	 }
	 
	//Assigning amino acids to their respective classes
	public void setArray() 
	{
		for(int i=0;i<length;i++) 
		{ 
			char ch=protein.charAt(i);
			Array1=Array1+hydro.get(ch);
			Array2=Array2+polarz.get(ch);
			Array3=Array3+polarity.get(ch);
			Array4=Array4+vander.get(ch);	
		}	
	}		
	
	//Setting composition values for each property
	public float[] CompositionFunction() {	
		
		//Storing percentage wise composition of each group
		float[][] thecounts = new float [4][3];
		
		//Gets the composition values
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 3; j++)
				thecounts[i][j] = 0;
			
		//Keeping track of integer Count value
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 3; j++)
				theOldCounts[i][j] = 0;


		for(int i = 0; i <this.length; i++) {
			if(this.Array1.charAt(i) == 'H')
				thecounts[0][0]++;
			else if(this.Array1.charAt(i) == 'E') 
				thecounts[0][1]++;
			else
				thecounts[0][2]++;
			if(this.Array2.charAt(i) == 'H')
				thecounts[1][0]++;
			else if(this.Array2.charAt(i) == 'E') 
				thecounts[1][1]++;
			else
				thecounts[1][2]++;
			if(this.Array3.charAt(i) == 'H')
				thecounts[2][0]++;
			else if(this.Array3.charAt(i) == 'E') 
				thecounts[2][1]++;
			else
				thecounts[2][2]++;
			if(this.Array4.charAt(i) == 'H')
			    thecounts[3][0]++;
			else if(this.Array4.charAt(i) == 'E') 
				thecounts[3][1]++;
			else
				thecounts[3][2]++;
		}
			
		//Getting composition value between 1 and 0
		for(int i=0;i<4;i++) {
			for(int j=0;j<3;j++) {
				theOldCounts[i][j]=(int) thecounts[i][j];
				thecounts[i][j]/=this.length;
			}
		}
			
		float array[]=new float[12];
			
		int index=-1;
				
		for(int i=0;i<4;i++) { 
			for(int j=0;j<3;j++) { 
				++index;
				array[index]=thecounts[i][j];
			}
		}	
		return array;	
	}
	
	//Getting transition values for each property
	public float[] TransitionFunction() {
		
	float[][] thecounts = new float [4][3];
	float array[]=new float[12];
	int index=-1;

	//Gets the transition values				
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 3; j++)
				thecounts[i][j] = 0;
			
		for(int i=0;i<this.length-1;i++) {
			if(this.Array1.charAt(i) == 'H' && this.Array1.charAt(i+1) == 'E')
				thecounts[0][0]++;
			else if(this.Array1.charAt(i) == 'E' && this.Array1.charAt(i+1) =='-') 
				thecounts[0][1]++;
			else if(this.Array1.charAt(i) == '-' && this.Array1.charAt(i+1) == 'H')
				thecounts[0][2]++;
			if(this.Array2.charAt(i) == 'H' && this.Array2.charAt(i+1) == 'E')
				thecounts[1][0]++;
			else if(this.Array2.charAt(i) == 'E' && this.Array2.charAt(i+1) == '-') 
				thecounts[1][1]++;
			else if(this.Array2.charAt(i) == '-' && this.Array2.charAt(i+1) == 'H')
				thecounts[1][2]++;
			if(this.Array3.charAt(i) == 'H' && this.Array3.charAt(i+1) == 'E')
				thecounts[2][0]++;
			else if(this.Array3.charAt(i) == 'E' && this.Array3.charAt(i+1) == '-') 
				thecounts[2][1]++;
			else if(this.Array3.charAt(i) == '-' && this.Array3.charAt(i+1) == 'H')
				thecounts[2][2]++;
			if(this.Array4.charAt(i) == 'H' && this.Array4.charAt(i+1) == 'E')
				thecounts[3][0]++;
			else if(this.Array4.charAt(i) == 'E'&&this.Array4.charAt(i+1) == '-') 
				thecounts[3][1]++;
			else if(this.Array4.charAt(i) == '-' && this.Array1.charAt(i+1) == 'H')
				thecounts[3][2]++;
		}
			
		//Getting transition value between 1 and 0
		for(int i=0;i<4;i++) {
			for(int j=0;j<3;j++) {
				thecounts[i][j]/=this.length;
			}
		}

		for(int i=0;i<4;i++) {
			for(int j=0;j<3;j++) {
				++index;
				array[index]=thecounts[i][j]; 
			}
		}
			
		return array;
	}
	
	//Getting percentage-wise frequency of each amino acid in sequence
	public float[] PercentFrequencyFunction() {
		
		float[] thecounts = new float[20];
		//Get amino acid composition
		for(int i=0;i<20;i++)
			thecounts[i]=0;
			
		for(int i=0;i<this.length;i++) {
			char ch=this.protein.charAt(i);
			if(ch=='R')
				thecounts[0]++;
			if(ch=='K')
				thecounts[1]++;
			if(ch=='E')
				thecounts[2]++;
			if(ch=='D')
				thecounts[3]++;
			if(ch=='Q')
				thecounts[4]++;
			if(ch=='N')
				thecounts[5]++;
			if(ch=='G')
				thecounts[6]++;
			if(ch=='A')
				thecounts[7]++;
			if(ch=='S')
				thecounts[8]++;
			if(ch=='T')
				thecounts[9]++;
			if(ch=='P')
				thecounts[10]++;
			if(ch=='H')
				thecounts[11]++;
			if(ch=='Y')
				thecounts[12]++;
			if(ch=='C')
				thecounts[13]++;
			if(ch=='V')
				thecounts[14]++;
			if(ch=='L')
				thecounts[15]++;
			if(ch=='I')
				thecounts[16]++;
			if(ch=='M')
				thecounts[17]++;
			if(ch=='F')
				thecounts[18]++;
			if(ch=='W')
				thecounts[19]++;
		}
			
		//Getting percentage values of amino acid composition
		for(int i=0;i<20;i++) {
			thecounts[i]/=this.length;
		}

		return thecounts;
	}
	
	//Getting distribution values for each property
	public float[] DistributionFunction() {
		
		int count[][]=new int[12][5];
		int temp[]=new int[12];
		int index[]=new int[12];
		
		//End result of distribution
		float thecounts[][]=new float[4][15];
		float array[]=new float[60];
		int indice=-1;
		
		//Getting 0%,25%,50%,75%,100% positioning of residue in protein
		for(int i=0;i<12;i++) 
			for(int j=0;j<5;j++) 
				count[i][j]=0;
			
			for(int i=0;i<4;i++) 
				for(int j=0;j<15;j++) 
					thecounts[i][j]=0;
			
			int index1=0;
			for(int i=0;i<4;i++) {
				for(int j=0;j<3;j++) {
					int value=(int)theOldCounts[i][j];
					count[index1][0]=0;
					count[index1][1]=(int) (value*0.25);
					count[index1][2]=(int) (value*0.5);
					count[index1][3]=(int) (value*0.75);
					count[index1][4]=value;
					index1++;
				}
			}
		
		//Getting distribution values for attribute Hydrophobicity
		for(int i=0;i<this.length;i++) {
				char ch=this.Array1.charAt(i);
				
				if(ch=='H') {
					index[0]=i;
					temp[0]++;
				}
				
				else if(ch=='E') {
					index[1]=i;
					temp[1]++;
				}
				
				else {
					index[2]=i;
					temp[2]++;
				}
				
				if(temp[0]==count[0][1])
					thecounts[0][1]=i;
				
				else if(temp[0]==count[0][2])
					thecounts[0][2]=i;
				
				else if(temp[0]==count[0][3])
					thecounts[0][3]=i;
				
				if(temp[1]==count[1][1])
					thecounts[0][6]=i;
				
				else if(temp[1]==count[1][2])
					thecounts[0][7]=i;
				
				else if(temp[1]==count[1][3])
					thecounts[0][8]=i;
				
				if(temp[2]==count[2][1])
					thecounts[0][11]=i;	
				
				else if(temp[2]==count[2][2])
					thecounts[0][12]=i;
				
				else if(temp[2]==count[2][3])
					thecounts[0][13]=i;
			}	
			
			thecounts[0][4]=index[0];
			thecounts[0][9]=index[1];
			thecounts[0][14]=index[2];	
		
		//Getting distribution values for attribute Polarizability
			for(int i=0;i<this.length;i++) {
				char ch=this.Array2.charAt(i);
				
				if(ch=='H') {
					index[3]=i;
					temp[3]++;
				}
				
				else if(ch=='E') {
					index[4]=i;
					temp[4]++;
				}
				
				else {
					index[5]=i;
					temp[5]++;
				}
				
				if(temp[3]==count[3][1])
					thecounts[1][1]=i;
				
				else if(temp[3]==count[3][2])
					thecounts[1][2]=i;
				
				else if(temp[3]==count[3][3])
					thecounts[1][3]=i;
				
				if(temp[4]==count[4][1])
					thecounts[1][6]=i;
				
				else if(temp[4]==count[4][2])
					thecounts[1][7]=i;
				
				else if(temp[4]==count[4][3])
					thecounts[1][8]=i;
				
				if(temp[5]==count[5][1])
					thecounts[1][11]=i;	
				
				else if(temp[5]==count[5][2])
					thecounts[1][12]=i;
				
				else if(temp[5]==count[5][3])
					thecounts[1][13]=i;
			}
			
			thecounts[1][4]=index[3];
			thecounts[1][9]=index[4];
			thecounts[1][14]=index[5];	

		//Getting distribution values for attribute Polarity
		for(int i=0;i<this.length;i++) {
				char ch=this.Array3.charAt(i);
		
				if(ch=='H') {
					index[6]=i;
					temp[6]++;
				}
				
				else if(ch=='E') {
					index[7]=i;
					temp[7]++;
				}
				
				else {
					index[8]=i;
					temp[8]++;
				}
				
				if(temp[6]==count[6][1])
					thecounts[2][1]=i;
			
				else if(temp[6]==count[6][2])
					thecounts[2][2]=i;
			
				else if(temp[6]==count[6][3])
					thecounts[2][3]=i;
			
				if(temp[7]==count[7][1])
					thecounts[2][6]=i;
			
				else if(temp[7]==count[7][2])
					thecounts[2][7]=i;
			
				else if(temp[7]==count[7][3])
					thecounts[2][8]=i;
			
				if(temp[8]==count[8][1])
					thecounts[2][11]=i;
					
				else if(temp[8]==count[8][2])
					thecounts[2][12]=i;
			
				else if(temp[8]==count[8][3])
					thecounts[2][13]=i;
			}	
			
			thecounts[2][4]=index[0];
			thecounts[2][9]=index[1];
			thecounts[2][14]=index[2];	

		//Getting distribution values for attribute Van Der Waals Volume
			for(int i=0;i<this.length;i++) {
				char ch=this.Array4.charAt(i);
			
				if(ch=='H') {
					index[9]=i;
					temp[9]++;
				}
				
				else if(ch=='E') {
					index[10]=i;
					temp[10]++;
				}
				
				else {
					index[11]=i;
					temp[11]++;
				}
			
				if(temp[9]==count[9][1])
					thecounts[3][1]=i;
			
				else if(temp[9]==count[9][2])
					thecounts[3][2]=i;
			
				else if(temp[9]==count[9][3])
					thecounts[3][3]=i;
			
				if(temp[10]==count[10][1])
					thecounts[3][6]=i;
			
				else if(temp[10]==count[10][2])
					thecounts[3][7]=i;
			
				else if(temp[10]==count[10][3])
					thecounts[3][8]=i;
			
				if(temp[11]==count[11][1])
					thecounts[3][11]=i;	
			
				else if(temp[11]==count[11][2])
					thecounts[3][12]=i;
			
				else if(temp[11]==count[11][3])
					thecounts[3][13]=i;
			}	
			
			thecounts[3][4]=index[9];
			thecounts[3][9]=index[10];
			thecounts[3][14]=index[11];	
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<15;j++) {
				++indice;
				array[indice]=thecounts[i][j]; 
				array[indice]/=this.length;
			}
		}
		return array;
	}
	
	//Forming the final feature vector of length 105 from all values obtained
	public float[] VectorFormFunction() {
		
		float [] featureVector = new float[105];
		
		//Keeping track of index in feature vector
		int index=-1;
			float temp1[] = new float[12];
			temp1=this.CompositionFunction();
			for(int i=0;i<12;i++) {
				++index;
				featureVector[index]=temp1[i];
			}
			float temp2[] = new float[12];
			temp2=this.TransitionFunction();
			for(int i=0;i<12;i++) {
				++index;
				featureVector[index]=temp2[i];
			}
			
		//Adding one dimensional array obtained from distribution object to feature vector
		float temp3[] = new float[60];
		temp3=DistributionFunction();
		for(int i=0;i<60;i++) {
			++index;
			featureVector[index]=temp3[i];
		}
		
		//Adding one dimensional array obtained from PercentFrequency object to feature vector
		float temp4[] = new float[20];
		temp4=this.PercentFrequencyFunction();
		for(int i=0;i<20;i++) {
			++index;
			featureVector[index]=temp4[i];
		}
		
		//Adding protein length obtained from Maps object to feature vector
		++index;
		featureVector[index]=this.length;
		
		//Displaying feature vector of length 105
//		for(int i=0;i<105;i++) {
//				System.out.print(featureVector[i]+" ");
//		}
//			
		return featureVector;
	}
}
