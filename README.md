# ProteinStructurePrediction-FYP

## SHQ and PDBParallel

Save both the folders into your eclipse workspace directory. 

Also copy **_"pdb_cleansed.csv"_** from the Drive folder if you want to run any cleaning operations.

Remember to have Chrome installed.

Only works on Windows atm.

Will have to change the chromedriver file if used on linux.

Driver found here:
> https://chromedriver.storage.googleapis.com/index.html?path=2.37/

### SeleniumChrome.java alterations:

Change the location of the chrome driver appropriately.
```javascript
protected static String cDriverPath	= "C:\\Users\\Crytek\\eclipse-workspace\\shq\\chromedriver\\chromedriver.exe";
```
Change the runScript parameters in the main function for other sequences.

> *_runScript(401, 400);_*

Initial parameter for the Sequence Number, the second parameter for the number of sequences to be processed.

## GUI and PYTHON Classifiers

### GUI

Have WindowBuilder installed from the Eclipse MarketPlace.

Import FYPGUI onto **Eclipse**.

Remember to change the **_theDirectory_** variable to the corresponding directory to where you've placed the **_"Python Classifiers"_** folder.

Run **_"ControlPane.java"_** for the GUI Window.

Paste a protein sequence _(Length: 20 to 1231)_ and click either __Predict__ or __Predict All__ to get results.

#### Python Classifiers

Install your preferred version of Python __(Tested on Python 2.7)__

Run these commands from the __command prompt__
> pip install gensim

> pip install scipy

> pip install sklearn

Run these commands from the __command prompt__ for the classification information such as **Accuracy**, **Precision** and **Confusion Matrix** and also **Graphs**
> pip install pandas

> pip install matplotlib

> pip install scikit-plot
