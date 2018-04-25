import pickle
import sys

arr1 = sys.argv[1].split('[')
arr2 = arr1[1].split(']')
arr = arr2[0].split(',')

arr_num = [float(num) for num in arr]

# Naive Bayesian Classifier
filename = 'NB_model.sav'
test = []
test.append(arr_num)
# load the Pre-loaded model from disk
loaded_model = pickle.load(open(filename, 'rb'))
preds3 = loaded_model.predict(test)
print("Naive Bayesian: ")
print(preds3)
print

#Support Vector Machine Classifier
filename = 'SVM_model.sav'
test = []
test.append(arr_num)
# load the Pre-loaded model from disk
loaded_model = pickle.load(open(filename, 'rb'))
preds3 = loaded_model.predict(test)
print("Support Vector Machine: ")
print(preds3)
print

# Random Forest Classifier
filename = 'RF_model.sav'
test = []
test.append(arr_num)
# load the Pre-loaded model from disk
loaded_model = pickle.load(open(filename, 'rb'))
preds3 = loaded_model.predict(test)
print("Random Forest: ")
print(preds3)