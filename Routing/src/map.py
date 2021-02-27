from matplotlib import style
import matplotlib.pyplot as plot
import sys

class Node():
    def __int__(self, id, longitude, latitude):
        self.id = id
        self.longitude = longitude
        self.latitude = latitude


class HashTable:
    def __init__(self, size):
        self.size = size
        self.hash_table = self.create_buckets()

    def create_buckets(self):
        return [[] for _ in range(self.size)]

    def set_val(self, key, val):
        hashed_key = hash(key) % self.size
        bucket = self.hash_table[hashed_key]
        found_key = False
        for index, record in enumerate(bucket):
            record_key, record_val = record
            if record_key == key:
                found_key = True
                break
        if found_key:
            bucket[index] = (key, val)
        else:
            bucket.append((key, val))

    def get_val(self, key):
        hashed_key = hash(key) % self.size
        bucket = self.hash_table[hashed_key]
        found_key = False
        for index, record in enumerate(bucket):
            record_key, record_val = record
            if record_key == key:
                found_key = True
                break
        if found_key:
            return record_val
        else:
            return "No record found"

file = open("D:\\Term5\\Data Structure and Algorithm\\Homework answeres\\Routing\\src\\4\\m4.txt", "r")
first_line = file.readline()
result = first_line.split(" ")
node_number = int(result[0])
edge_number = int(result[1])
longitude = []
latitude = []
nodes = []
hash_table = HashTable(node_number)
for i in range(0, node_number):
    next_line = file.readline()
    temp = next_line.split(" ")
    n = Node()
    n.__int__(temp[0], temp[1], temp[2])
    nodes.append(n)
    longitude.append(float(temp[1]))
    latitude.append((float(temp[2])))
    hash_table.set_val(temp[0], n)

edgesX = []
edgesY = []
k = 0
flag = True
prev_node = ""
style.use('dark_background')
for i in range(0, edge_number):
    next_line = file.readline()
    temp = next_line.split(" ")
    key1 = hash_table.get_val(temp[0])
    edgesX.append(float(key1.latitude))
    edgesY.append(float(key1.longitude))
    a = int(temp[1])
    key2 = hash_table.get_val(str(a))
    edgesX.append(float(key2.latitude))
    edgesY.append(float(key2.longitude))
    plot.plot(edgesX, edgesY, linewidth=0.8)
    edgesX.clear()
    edgesY.clear()
    prev_node = temp[1]

plot.scatter(latitude, longitude, 2)
plot.title('x[n]')
plot.xlabel('n-axis')
plot.ylabel('x[n]-axis')
#plot.savefig('map.png')
plot.show()
