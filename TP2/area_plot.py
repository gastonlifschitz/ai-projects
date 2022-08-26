# library
import numpy as np
import matplotlib.pyplot as plt
 
import scanFile as scanner

generaciones , fitness = scanner.scanxy('fitness(generaciones).txt')
 
# Change the color and its transparency
plt.title('fitness segun generacion')
plt.xlabel('generaciones')
plt.ylabel('fitness')
plt.fill_between( generaciones,fitness, color="skyblue", alpha=0.4)
plt.xticks(np.arange(0, len(generaciones) + 1 , len(generaciones)/10)) 
plt.show()
 
# Same, but add a stronger line on top (edge)
plt.fill_between( generaciones, fitness, color="skyblue", alpha=0.2)
plt.plot(generaciones,fitness, color="Slateblue", alpha=0.6)
# See the line plot function to learn how to customize the plt.plot function

