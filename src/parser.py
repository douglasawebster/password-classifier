import random

file = open("./../text-files/test-common-passwords.txt", "r")

training_passwords = []
test_passwords = []

for word in file.readlines():
    rand = random.random()

    if rand < 0.2:
        training_passwords.append(word)
        #else:
         #   test_passwords.append(word)

file.close()

training_passwords_file = open("./../text-files/temp.txt", "w")
#test_passwords_file = open("./../text-files/test-common-passwords.txt", "w")

for password in training_passwords:
    training_passwords_file.write(password)

#for password in test_passwords:
 #   test_passwords_file.write("0 " + password)

#file.close()
training_passwords_file.close()
#test_passwords_file.close()

"""file = open("./../text-files/common-passwords.txt", "r")

passwords = []

for word in file.readlines():
    passwords.append(word)

file.close()

strong_passwords_file = open("./../text-files/training-passwords-2.txt", "w")

for password in passwords:
    strong_passwords_file.write("0 " + password)

strong_passwords_file.close()"""
