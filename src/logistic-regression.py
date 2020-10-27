import matplotlib.pyplot as plt
import numpy as np
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report, confusion_matrix

# Really good article https://realpython.com/logistic-regression-python/

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

x = np.array(x_data)
y = np.array(y_data)


x_test_data = []
y_test_data = []

#test_passwords = open("test-results.txt", "r").readlines()
test_passwords = open("/Users/dwebster/Desktop/test-results.txt", "r").readlines()
for result in test_passwords:
    result_fields = result.split(" ")
    classification = int(result_fields[0])
    metrics = []

    for i in range(1, len(result_fields)):
        score = result_fields[i]
        metrics.append(float(score))

    x_test_data.append(metrics)
    y_test_data.append(classification)

test_x = np.array(x_test_data)
test_y = np.array(y_test_data)

model = LogisticRegression(solver='liblinear', random_state=0).fit(x, y)

#print(model.predict(x))
#print(model.score(x, y))

print(model.predict(test_x))
print(model.score(test_x, test_y))

#print(model.coef_[0])
#print(model.predict_proba(x_test_data))


"""percentages = []
for data in x_data:
    percentages.append(data[5])


plt.scatter(y_data, percentages)
plt.show()"""


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

# print(classification_report(test_y, model.predict(test_x)))