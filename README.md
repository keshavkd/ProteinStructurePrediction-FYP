# ProteinStructurePrediction-FYP

Save both the folders into your eclipse workspace directory. 
Also copy "pdb_cleansed.csv" from the Drive folder if you want to run any cleaning operations.

Remember to have Chrome installed.

Only works on Windows atm.
Will have to change the chromedriver file if used on linux.
Driver found here:
https://chromedriver.storage.googleapis.com/index.html?path=2.37/

SeleniumChrome.java alterations:
Change the location of the chrome driver appropriately
protected static String cDriverPath		= "C:\\Users\\Crytek\\eclipse-workspace\\shq\\chromedriver\\chromedriver.exe";

Change the runScript parameters in the main function for other sequences
runScript(401, 400);
Initial parameter for the Sequence Number, the second parameter for the number of sequences to be processed.