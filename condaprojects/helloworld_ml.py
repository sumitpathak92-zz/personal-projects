from sklearn import tree
import numpy as np
from sklearn.datasets import load_iris
features = [[140, 1], [130, 1], [150, 0], [170, 0]]
# 0 represents an apple, 1 represents orange
labels = [0, 0, 1, 1]
clf = tree.DecisionTreeClassifier()
clf = clf.fit(features, labels)
#print clf.predict([[150, 0]])

iris = load_iris()
test_idx = [0, 50, 100]

#training data
train_target = np.delete(iris.target, test_idx)
train_data = np.delete(iris.data, test_idx, axis=0)

#testing data
test_target = iris.target[test_idx]
test_data = iris.data[test_idx]

clf = tree.DecisionTreeClassifier()
clf = clf.fit(train_data, train_target)

print test_target
print clf.predict(test_data)



