import matplotlib.pyplot as plt

import scanFile as scanner
import numpy as np 

generaciones , fitness = scanner.scanxy('fitness(generaciones).txt')

plt.title('fitness segun generacion')
plt.xlabel('generaciones')
plt.ylabel('fitness')
#plt.scatter(generaciones,fitness)
plt.plot(generaciones , fitness , linestyle='', marker='o', markersize=0.7)
#(0,n,n/10)
plt.xticks(np.arange(0, len(generaciones) + 1 , len(generaciones)/10)) 
#plt.yticks(np.arange(1000, 2101, 100)) 
plt.show()