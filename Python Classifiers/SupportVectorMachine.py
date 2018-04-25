import pickle
import sys

arr1 = sys.argv[1].split('[')
arr2 = arr1[1].split(']')
arr = arr2[0].split(',')
arr_num = [float(num) for num in arr]
filename = 'SVM_model.sav'
test = []
test.append(arr_num)

# load the Pre-loaded model from disk
loaded_model = pickle.load(open(filename, 'rb'))
preds3 = loaded_model.predict(test)
print("Support Vector Machine: ")
print(preds3)