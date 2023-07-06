import graphviz
from OrderBookNode import *

class TreeUtils(object):
	@classmethod
	def count_nodes(cls, root: OrderBookNode):
		if root is None:
			return 0

		return 1 + cls.count_nodes(root = root.get_left()) + cls.count_nodes(root = root.get_right())

	@classmethod
	def visualize(cls, root: OrderBookNode, front_ptr: OrderBookNode, file_name: str):
		dot = graphviz.Digraph()
		dot.node(f"{root.get_price()} - {root.get_volume()}", style = 'filled', fillcolor = '#D0321D')

		def add_nodes_edges(node):
			node_repr = f"{node.get_price()} - {node.get_volume()}"

			if node.get_left():
				left_repr = f"{node.get_left().get_price()} - {node.get_left().get_volume()}"
				dot.node(left_repr)
				dot.edge(node_repr, left_repr)
				add_nodes_edges(node.get_left())

			if node.get_right():
				right_repr = f"{node.get_right().get_price()} - {node.get_right().get_volume()}"
				dot.node(right_repr)
				dot.edge(node_repr, right_repr)
				add_nodes_edges(node.get_right())

		add_nodes_edges(root)

		front_repr = f"{front_ptr.get_price()} - {front_ptr.get_volume()}"
		
		dot.node(front_repr, style = 'filled', fillcolor = '#63C5DA')
		dot.render(filename = file_name, directory = 'runs', view = False, format='jpg')
		# dot.render(directory = 'binary_tree', view = True)
