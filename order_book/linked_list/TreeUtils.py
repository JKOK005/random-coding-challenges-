import graphviz
from OrderBook import *

class TreeUtils(object):
	@classmethod
	def count_nodes(cls, root: BookNode):
		if root is None:
			return 0 
		return 1 + cls.count_nodes(root.get_next_node())

	@classmethod
	def count_super_nodes(cls, root: BookSuperNode):
		if root is None:
			return 0
		return cls.count_nodes(root = root.get_first_child()) + cls.count_super_nodes(root = root.get_next_node()) 

	@classmethod
	def visualize(cls, root: BookSuperNode, file_name: str):
		dot = graphviz.Digraph()
		dot.node(f"{root.get_price()} - {root.get_volume()}", style = 'filled', fillcolor = '#D0321D')

		def add_nodes_edges(node):
			node_repr = f"{node.get_price()} - {node.get_volume()}"

			if node.get_next_node():
				left_repr = f"{node.get_next_node().get_price()} - {node.get_next_node().get_volume()}"
				dot.node(left_repr)
				dot.edge(node_repr, left_repr)
				add_nodes_edges(node.get_next_node())
		
		add_nodes_edges(root)
		dot.render(directory = 'runs', view = True)
		# dot.render(filename = file_name, directory = 'runs', view = False, format='jpg')
