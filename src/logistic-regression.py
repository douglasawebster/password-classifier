import matplotlib.pyplot as plt
import numpy as np
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report, confusion_matrix

# Really good article https://realpython.com/logistic-regression-python/

def trainModel():
    x_data = []
    y_data = []

    training_passwords = open("training-results.txt", "r").readlines()
    for result in training_passwords:
        result_fields = result.split(" ")
        classification = int(result_fields[0])
        metrics = []

        for i in range(1, len(result_fields)):
            score = result_fields[i]
            metrics.append(float(score))

        x_data.append(metrics)
        y_data.append(classification)

    return {
        "X" : x_data,
        "Y" : y_data
    }

def test_model():
    x_test_data = []
    y_test_data = []

    test_passwords = open("test-results.txt", "r").readlines()
    for result in test_passwords:
        result_fields = result.split(" ")
        classification = int(result_fields[0])
        metrics = []

        for i in range(1, len(result_fields)):
            score = result_fields[i]
            metrics.append(float(score))

        x_test_data.append(metrics)
        y_test_data.append(classification)

    return {
        "X" : x_test_data,
        "Y" : y_test_data,
    }

def test_input():
    password = open("./temp.txt").readline()
    result_fields = password.split(" ")
    return [result_fields]


training_results = trainModel()
x = np.array(training_results["X"])
y = np.array(training_results["Y"])

testing_results = test_model()
test_x = np.array(testing_results["X"])
test_y = np.array(testing_results["Y"])

sinlge_test_password = test_input()
print(sinlge_test_password)

model = LogisticRegression(solver='liblinear', random_state=0).fit(x, y)

predictions = model.predict(test_x)
score = model.score(test_x, test_y)

print(predictions)
print(score)

cm = confusion_matrix(test_y, model.predict(test_x))
fig, ax = plt.subplots(figsize=(8, 8))
ax.imshow(cm)
ax.grid(False)
ax.xaxis.set(ticks=(0, 1), ticklabels=('Predicted 0s', 'Predicted 1s'))
ax.yaxis.set(ticks=(0, 1), ticklabels=('Actual 0s', 'Actual 1s'))
ax.set_ylim(1.5, -0.5)
for i in range(2):
    for j in range(2):
        ax.text(j, i, cm[i, j], ha='center', va='center', color='red')
plt.show()
