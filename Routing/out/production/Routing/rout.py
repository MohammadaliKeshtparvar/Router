from matplotlib import style
import matplotlib.pyplot as plot

file = open("D:\\Term5\\Data Structure and Algorithm\\Homework answeres\\Routing\\rout.txt", "r")
first_line = file.readline()
edge_number = int(first_line)
longitude = []
latitude = []
for i in range(0, edge_number):
    next_line = file.readline()
    temp = next_line.split(" ")
    longitude.append(float(temp[0]))
    latitude.append((float(temp[1])))

style.use('dark_background')
plot.plot(longitude, latitude, linewidth=0.8)
plot.show()