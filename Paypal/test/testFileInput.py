class Node(object):
    val     = None
    left    = None
    right   = None
    
    def __init__(self, X):
        self.val    = X
    
    def getVal(self): 
        return self.val
    
    def getLeft(self):
        return self.left
    
    def getRight(self):
        return self.right
    
    def setLeft(self, node):
        self.left = node
    
    def setRight(self, node):
        self.right = node
    

def insert(root, key, counter):    
    counter += 1
    if(key > root.getVal):
        if(root.getRight() == None):
            node    = None(key)
            root.setRight(node)
		else:
            insert(root.getRight(), key, counter)
            
    else:
         if(root.getLeft() == None):
            node    = None(key)
            root.setLeft(node)
         else:
            insert(root.getLeft(), key, counter)
    return
    
def createBST(keys):
    counter = 0
    root    = None
    for each_key in keys:
        if(root == None):
            root    = Node(each_key)
        else:
            insert(root, each_key, counter)
        print(counter)
    return
