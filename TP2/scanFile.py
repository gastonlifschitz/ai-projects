def scanxy(filename):
    file = open(filename, 'r') 
    Lines = file.readlines() 
    
    x = []
    y = []
    
    count = 0
    
    for line in Lines:
        arr = line.rsplit(' ',1)
        x.append(arr[0])
        f = float(arr[1])
        format(f,'.2f')
        y.append(f)
        #print(f)
        count += 1
    
    return x , y

def scanarr(filename):
    file = open(filename, 'r') 
    Lines = file.readlines() 
    count = 0
    main = []
    for line in Lines:
        aux = []
        arr = line.split()
        aux.append(arr[0])
        aux.append(arr[1])
        aux.append(arr[2])
        #aux.append(format(float(arr[3]),'.2f'))
        #aux.append(format(float(arr[4]),'.2f'))
        main.append(aux)
        count += 1
    
    return main