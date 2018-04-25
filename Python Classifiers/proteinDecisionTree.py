import pandas
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score
from sklearn import metrics
from sklearn import svm
from sklearn.metrics import precision_recall_curve
import matplotlib.pyplot as plt
import scikitplot as skplt
import csv

results = []
with open("FeatureVectors.csv") as csvfile:
    reader = csv.reader(csvfile, quoting=csv.QUOTE_NONNUMERIC)  # change contents to floats
    for row in reader:  # each row is a list
        results.append(row)

colnames = ['Intent']
data = pandas.read_csv('ClassLabels.csv', names=colnames)

intent = data.Intent.tolist()


train, test, train_labels, test_labels = train_test_split(results,
                                                          intent,
                                                          test_size=0.40,
                                                          random_state=42)

count1 = 0
count2 = 0
count3 = 0

for i in range(len(test_labels)):

    if(test_labels[i] == "Alpha helix"):
        count1 = count1 + 1
    elif(test_labels[i] == "Random coil"):
        count2 = count2 + 1
    else:
        count3 = count3 + 1


print("Number of Alpha Helix used for Testing: " + str(count1))
print("Number of Random Coil used for Testing: " + str(count2))
print("Number of Extended Strand used for Testing: " + str(count3))

#Random Forest Classifier

clf = RandomForestClassifier()
model1 = clf.fit(train, train_labels)
preds1 = clf.predict(test)

#Gaussian Naive Bayers Classifier

gnb = GaussianNB()
model2 = gnb.fit(train, train_labels)
preds2 = gnb.predict(test)

#SVM Classifier

clff = svm.SVC(gamma=0.001, C=100)
model3 = clff.fit(train, train_labels)
preds3 = clff.predict(test)


print("\n")
print("Confusion Matrix for Random Forest:")
print("\n")
print("Alpha  Extended Random")
confusion = metrics.confusion_matrix(test_labels, preds1)
print(confusion)
skplt.metrics.plot_confusion_matrix(test_labels, preds1, normalize=True)
plt.show()
print("\n")

print("Confusion Matrix for Gaussian:")
print("\n")
print("Alpha  Extended Random")
confusion = metrics.confusion_matrix(test_labels, preds2)
print(confusion)
skplt.metrics.plot_confusion_matrix(test_labels, preds2, normalize=True)
plt.show()
print("\n")


print("Confusion Matrix for SVM:")
print("\n")
print("Alpha  Extended Random")
confusion = metrics.confusion_matrix(test_labels, preds3)
print(confusion)
skplt.metrics.plot_confusion_matrix(test_labels, preds3, normalize=True)
plt.show()
print("\n")



print("SVM Classifier: ")
print("Accuracy of Prediction: " + str(accuracy_score(test_labels, preds3)))
print("Precision: " + str(metrics.precision_score(test_labels, preds3, average='weighted')))
print("\n")

print("Random Forest Classifier: ")
print("Accuracy of Prediction: " + str(accuracy_score(test_labels, preds1)))
print("Precision: " + str(metrics.precision_score(test_labels, preds1, average='weighted')))
print("\n")

print("Gaussian Naive Bayers Classifier: ")
print("Accuracy of Prediction: " + str(accuracy_score(test_labels, preds2)))
print("Precision: " + str(metrics.precision_score(test_labels, preds2, average='weighted')))

# print('True:', test_labels[0:25])
# print('False:', preds1[0:25])
# print('False:', preds2[0:25])


