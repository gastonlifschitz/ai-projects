from mpl_toolkits import mplot3d

import numpy as np
import matplotlib.pyplot as plt

fig = plt.figure()
ax = plt.axes(projection="3d")

x_points = [1.2,1.2,1.2,0,7.9,0.4,0,-1.3,0.4,0.4,-1.3,0,7.9,1.8,0,-0.5,0,-2,-0.5,7.9,-1.3,0,-2,-2,0,1.8,-0.5,1.8]

y_points = [-0.8,0,-0.8,1.2,1,0,0.4,3.23,2.7,2.7,0,-1.3,1,0,-2,0.6,1.8,2,0.6,0,3.23,7.9,0,2,-0.5,1.6,0,1.6]

z_points = [0,-0.8,1,-0.8,0,2.7,2.7,3,0,2,3.23,3.23,-2,1.6,2,0,1.6,0,2.5,1,0,1,2,-1,0.6,1.3,0.6,0]

ax.scatter3D(x_points, y_points, z_points, c=z_points, cmap='hsv');

#x = np.outer(np.linspace(-2, 2, 30), np.ones(30))
#y = x.copy().T # transpose
#z = np.cos(x ** 2 + y ** 2)

#ax.plot_surface(x, y, z,cmap='viridis', edgecolor='none')

plt.show()

point  = np.array([1, 2, 3])
normal = np.array([1, 1, 2])

# a plane is a*x+b*y+c*z+d=0
# [a,b,c] is the normal. Thus, we have to calculate
# d and we're set
d = -point.dot(normal)

# create x,y
xx, yy = np.meshgrid(range(10), range(10))

# calculate corresponding z
z = (-normal[0] * xx - normal[1] * yy - d) * 1. /normal[2]

# plot the surface
plt3d = plt.figure().gca(projection='3d')
plt3d.plot_surface(xx, yy, z)
plt.show()