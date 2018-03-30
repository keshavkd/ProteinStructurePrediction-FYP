import java.util.*;
import java.io.*;

public class PDBCleanse {
	
	static String ipFile			= "pdb_cleansed.csv";
	static String opFile1			= "pdb_seq_NLS.txt";
	static String opFile2			= "pdb_seq_NS.txt";
	static String opFile3			= "pdb_seq_S.txt";
	protected static boolean flag			= true;
	protected static String line, size, name, sequence;
	protected static String[] content; 
	
	public static void allTypes() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(ipFile));
		BufferedWriter bx = new BufferedWriter(new FileWriter(opFile1));
		BufferedWriter by = new BufferedWriter(new FileWriter(opFile2));
		BufferedWriter bz = new BufferedWriter(new FileWriter(opFile3));
		line = br.readLine();
		int count = 0;
		while(flag) {
			try {
				line = br.readLine();
				content = line.split(",");
				size = content[2];
				sequence = content[3];
				name = content[4];
				bx.write(name.substring(0, name.length()-1) + "," + size + "\n");
				bx.write(sequence + "\n");
				by.write(name + "\n");
				by.write(sequence + "\n");
				bz.write(sequence + "\n");
				count++;
			} catch(Exception e) {
				flag = false;
				br.close();
				bx.close();
				by.close();
				bz.close();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		allTypes();
	}
	
}
