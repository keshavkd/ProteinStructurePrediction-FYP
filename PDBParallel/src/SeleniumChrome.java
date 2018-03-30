import java.util.*;
import java.io.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;

public class SeleniumChrome extends Exception {
	
	protected static String link			= "https://npsa-prabi.ibcp.fr/cgi-bin/npsa_automat.pl?page=/NPSA/npsa_gor4.html";
	protected static String chromeDriver	= "webdriver.chrome.driver";
	protected static String cDriverPath		= "C:\\Users\\Crytek\\eclipse-workspace\\shq\\chromedriver\\chromedriver.exe";
	protected static String notice			= "notice";
	protected static String ipFile1			= "pdb_seq_S.txt";
	protected static String ipFile2			= "pdb_seq_NS.txt";
	protected static String opFile			= "ContentTest.txt";
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
					throw new Exception("COUNT REACHED " + (count + endNum));
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
		runScript(401, 400);
	}	
}
