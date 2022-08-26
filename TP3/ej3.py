import scanFile as scanner
import matplotlib.pyplot as plt
import numpy as np 

x , y = scanner.scanxy('ex3a.txt')

#print(x)
#print(y)

plt.title('Ejercicio 3a')
plt.xlabel('Paso')
plt.ylabel('Error')
plt.plot(x,y)
plt.xticks(np.arange(0, len(x) + 1 , len(x)/10)) 
plt.show()

x , y = scanner.scanxy('ex3b.txt')

#print(x)
#print(y)

plt.title('Ejercicio 3b')
plt.xlabel('Paso')
plt.ylabel('Error')
plt.plot(x,y)
plt.xticks(np.arange(0, len(x) + 1 , len(x)/10)) 
plt.show()