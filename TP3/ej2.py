import scanFile as scanner
import matplotlib.pyplot as plt
import numpy as np 

x , y = scanner.scanxy('ej2Plot.txt')

#print(x)
#print(y)

plt.title('Ejercicio 2')
plt.xlabel('Paso')
plt.ylabel('Error')
plt.plot(x,y)
plt.xticks(np.arange(0, len(x) + 1 , len(x)/10)) 
plt.show()

x = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27]
y = [26.36, 13.53, 13.26, 3.49, 12.01, 9.36, 9.13, 14.84, 0.59, 5.98, 1.29, 4.00, 3.39, 10.39, 6.43, 13.19, 0.22, 2.70, 1.32, 10.18, 0.35, 0.02, 17.58, 1.78, 25.73, 3.60, 19.84, 1.70 ]
z = [0.30, 0.15 ,0.15, 0.04, 0.14, 0.11, 0.10, 0.17, 0.01, 0.07, 0.01, 0.05, 0.04, 0.12, 0.07, 0.15, 0.00, 0.03, 0.02, 0.12, 0.00, 0.00, 0.20, 0.02, 0.29, 0.04, 0.23, 0.02 ]
plt.title('Ejercicio 2 Error por indice')
plt.xlabel('Indice')
plt.ylabel('Error')
plt.scatter(x,y)
plt.show()

plt.title('Ejercicio 2 Error por indice (Normalizado)')
plt.xlabel('Indice')
plt.ylabel('Error')
plt.scatter(x,z)
plt.show()