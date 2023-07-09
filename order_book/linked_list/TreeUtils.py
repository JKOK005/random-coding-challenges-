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
		dot.node(f"S - {root.get_price()} - {root.get_volume()}", style = 'filled', fillcolor = '#D0321D')

		def add_nodes(node: BookNode):
			node_repr = f"N - {node.get_order().get_price()} - {node.get_order().get_qty()}"

			if node.get_next_node():
				left_repr = f"N - {node.get_next_node().get_order().get_price()} - {node.get_next_node().get_order().get_qty()}"
				dot.node(left_repr)
				dot.edge(node_repr, left_repr)
				add_nodes(node = node.get_first_child())

		def add_super_nodes(node: BookSuperNode):
			node_repr = f"S - {node.get_price()} - {node.get_volume()}"
			child_repr = f"N - {node.get_first_child().get_order().get_price()} - {node.get_first_child().get_order().get_qty()}"
			dot.node(child_repr)
			dot.edge(node_repr, child_repr)
			add_nodes(node = node.get_first_child())

			if node.get_next_node():
				left_repr = f"S - {node.get_next_node().get_price()} - {node.get_next_node().get_volume()}"
				dot.node(left_repr)
				dot.edge(node_repr, left_repr)
				add_super_nodes(node.get_next_node())
		
		add_super_nodes(root)
		dot.render(directory = 'runs', view = True)
		# dot.render(filename = file_name, directory = 'runs', view = False, format='jpg')
