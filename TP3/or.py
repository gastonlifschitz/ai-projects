import scanFile as scan
import matplotlib.pyplot as plt 

matrix = scan.scanarr('ej1b.txt')

plot = True

def genY(a,b,c):
    ret = [0,0,0,0,0,0,0]
    a = float(a)
    b = float(b)
    c = float(c)
    x = -3
    i = 0
    while(x <= 3):
        ret[i] = -1*(b/c)*x - a/c
        i = i + 1
        x = x + 1
    return ret

for i in range(len(matrix)):
    y = []
    #print(matrix[i])
    y = genY(matrix[i][0],matrix[i][1],matrix[i][2])
    #print(y)
    if(plot):
        x = [-3,-2,-1,0,1,2,3] 
        plt.xlabel('x - axis') 
        plt.ylabel('y - axis')  
        plt.title('OR')
        plt.scatter([1,1,-1,-1],[1,-1,1,-1])
        plt.plot(x,y) 
        plt.show() 
    
    
