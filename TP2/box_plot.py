import numpy as np
import matplotlib.pyplot as plt
import sys 

import scanFile as scanner


identificador , repeticiones = scanner.scanxy(sys.argv[1])

# Fake dataset
height = repeticiones
bars = identificador
y_pos = np.arange(len(bars))
 
# Create bars and choose color
plt.bar(y_pos, height, color = (0.5,0.1,0.5,0.6))
 
name = str(sys.argv[1]).replace('_frecuencia.txt','')

# Add title and axis names
plt.title('Frecuencia de ' + name)
plt.xlabel('Id de' + name)
plt.ylabel('Frecuencia')
 
# Limits for the Y axis
plt.ylim(0,max(repeticiones)+10)
 
# Create names
plt.xticks(y_pos, bars)
 
# Show graphic
plt.show()