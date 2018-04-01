import java.util.*;
import java.io.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;

public class SeleniumChrome extends Exception {
	
	protected static String link			= "https://npsa-prabi.ibcp.fr/cgi-bin/npsa_automat.pl?page=/NPSA/npsa_gor4.html";
	protected static String link2			= "http://www.biogem.org/tool/chou-fasman/index.php";
	protected static String chromeDriver	= "webdriver.chrome.driver";
	protected static String cDriverPath		= "C:\\Users\\Crytek\\eclipse-workspace\\shq\\chromedriver\\chromedriver.exe";
	protected static String notice			= "notice";
	protected static String ipFile1			= "pdb_seq_S.txt";
	protected static String ipFile2			= "pdb_seq_NS.txt";
	protected static String opFile			= "ContentTest.txt";
	protected static String opFile2			= "ContentTest_New2.txt";
	protected static String seq1			= "MVLSEGEWQLVLHVWAKVEADVAGHGQDILIRLFKSHPETLEKFDRVKHLKTEAEMKASEDLKKHGVTVLTALGAILKKKGHHEAELKPLAQSHATKHKIPIKYLEFISEAIIHVLHSRHPGNFGADAQGAMNKALELFRKDIAAKYKELGYQG";
	protected static String seq2			= "SVLLQMTQRLALSDAHFRRICQLIYQRAGIVLADHKRDMVYNRLVRRLRALGLDDFGRYLSMLEANQNSAEWQAFINALTTNLTAFFREAHHFPILAEHARRRHGEYRVWSAAASTGEEPYSIAITLADALGMAPGRWKVFASDIDTEVLEKARSGIYRLSELKTLSPQQLQRYFMRGTGPHEGLVRVRQELANYVEFSSVNLLEKQYNVPGPFDAIFCRNVMIYFDKTTQEDILRRFVPLLKPDGLLFAGHSENFSNLVREFSLRGQTVYALS";
	protected static String textElement;
	protected static BufferedReader br1;
	protected static BufferedReader br2;
	protected static BufferedWriter bw;
	protected static float max;
	
	
	public static void runScript(int lineNum, int endNum) throws Exception {
		boolean flag = true;
		int count = 0;
		br1 = new BufferedReader(new FileReader(ipFile1));
		br2 = new BufferedReader(new FileReader(ipFile2));
		bw = new BufferedWriter(new FileWriter(opFile, true));
		System.setProperty(chromeDriver, cDriverPath);
		WebDriver driver = new ChromeDriver();
		driver.get(link);
		long timeStart, timeEnd, timeTaken;
		timeStart = System.currentTimeMillis();
		for(int i = 1; i < lineNum; i++) {
			br1.readLine();
			br2.readLine();
			br2.readLine();
		}
		while(flag) {
			try {
				WebElement textBoxArea = driver.findElement(By.name(notice));
				textBoxArea.clear();
				textBoxArea.sendKeys(br1.readLine());
				WebElement submitBtn = driver.findElement(By.cssSelector("input[type=submit]"));
				submitBtn.click();
				WebElement content = driver.findElement(By.xpath("/html/body/pre[3]"));
				textElement = content.getText();
				String[] newTextLine = textElement.split("\\r?\\n");
				String[] curTextLine;
				String name = null, perc = null;
				float temp;
				max = -1;
				count++;
				
				if(count > endNum)
					throw new Exception("COUNT REACHED " + (endNum));
				for(int i = 1; i < newTextLine.length; i++) {
					curTextLine = newTextLine[i].split("\\s+"); 
					if(i != 10)
						perc = curTextLine[7];
					else
						perc = curTextLine[6];
					temp = Float.parseFloat(perc.substring(0, perc.indexOf("%")));
					if(temp > max) {
						name = curTextLine[1] + " " + curTextLine[2];
						max = temp;
					}
				}
				bw.write(br2.readLine() + "\n" + br2.readLine() + "\n" + name + ": " + max + "\n");
				System.out.println(count + " : " + name + " : " + max);
				if(count % 100 == 0)
					System.out.println("Count: " + count);
				driver.navigate().back();
			} catch(Exception e) {
				flag = false;
				System.out.println(e);
				bw.close();
				br1.close();
				br2.close();
			}
		}
		timeEnd = System.currentTimeMillis();
		timeTaken = (timeEnd - timeStart) / 1000;
		System.out.println("Time elapsed: " + timeTaken + "s");
	}

	public static void csvMaker (String fileName, int start, int num) throws Exception {
		br1 = new BufferedReader(new FileReader(fileName));
		bw = new BufferedWriter(new FileWriter("ContentTest.csv", true));
		boolean flag = true;
		int count = 0;
		String line;
		for(int i = 0; i < start * 3; i++)
			br1.readLine();
		while(flag) {
			try {
				if(count > num) {
					flag = false;
					break;
				}
				line = br1.readLine();
				line = line.substring(0, line.length()-1);
				bw.write(line + "," + br1.readLine());
				line = br1.readLine();
				line = line.substring(0, line.indexOf(":"));
				bw.write("," + line + "\n");
				count++;
			} catch(Exception e) {
				flag = false;
				bw.close();
				br1.close();
			}
		}
	}
	
	public static void csvMakerNoLim (String fileName) throws Exception {
		br1 = new BufferedReader(new FileReader(fileName));
		bw = new BufferedWriter(new FileWriter("ContentTest.csv"));
		boolean flag = true;
		String line;
		while(flag) {
			try {
				line = br1.readLine();
				line = line.substring(0, line.length()-1);
				bw.write(line + "," + br1.readLine());
				line = br1.readLine();
				line = line.substring(0, line.indexOf(":"));
				bw.write("," + line + "\n");
			} catch(Exception e) {
				flag = false;
				bw.close();
				br1.close();
			}
		}
	}
	
	public static void runScriptV2(int lineNum, int endNum) throws Exception {
		boolean flag = true;
		int count = 0;
		br1 = new BufferedReader(new FileReader(ipFile1));
		br2 = new BufferedReader(new FileReader(ipFile2));
		bw = new BufferedWriter(new FileWriter(opFile2, true));
		System.setProperty(chromeDriver, cDriverPath);
		WebDriver driver = new ChromeDriver();
		driver.get(link2);
		long timeStart, timeEnd, timeTaken;
		timeStart = System.currentTimeMillis();
		for(int i = 1; i < lineNum; i++) {
			br1.readLine();
			br2.readLine();
			br2.readLine();
		}
		while(flag) {
			try {
				WebElement textBoxArea = driver.findElement(By.id("sequence"));
				textBoxArea.clear();
				textBoxArea.sendKeys(">1\n" + br1.readLine());
				WebElement submitBtn = driver.findElement(By.cssSelector("input[type=submit]"));
				submitBtn.click();
				WebElement content = driver.findElement(By.xpath("/html/body/pre[3]"));
				textElement = content.getText();
				String[] newTextLine = textElement.split("\\r?\\n");
				String[] curTextLine;
				String name = null, perc = null;
				float temp;
				max = -1;
				count++;
				
				if(count > endNum)
					throw new Exception("COUNT REACHED " + (endNum));
				for(int i = 1; i < newTextLine.length; i++) {
					curTextLine = newTextLine[i].split("\\s+"); 
					if(i != 10)
						perc = curTextLine[7];
					else
						perc = curTextLine[6];
					temp = Float.parseFloat(perc.substring(0, perc.indexOf("%")));
					if(temp > max) {
						name = curTextLine[1] + " " + curTextLine[2];
						max = temp;
					}
				}
				bw.write(br2.readLine() + "\n" + br2.readLine() + "\n" + name + ": " + max + "\n");
				System.out.println(count + " : " + name + " : " + max);
				if(count % 100 == 0)
					System.out.println("Count: " + count);
				driver.navigate().back();
			} catch(Exception e) {
				flag = false;
				System.out.println(e);
				bw.close();
				br1.close();
				br2.close();
			}
		}
		timeEnd = System.currentTimeMillis();
		timeTaken = (timeEnd - timeStart) / 1000;
		System.out.println("Time elapsed: " + timeTaken + "s");
	}
	
	public static void main(String[] args) throws Exception {
		//runScript(3001, 8160-3001);
		//csvMaker("ContentTest.txt", 0, 1000);
		csvMakerNoLim("ContentTest.txt");
	}	
}
