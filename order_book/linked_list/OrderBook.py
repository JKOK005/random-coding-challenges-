import copy
from Orders import *
from typing import Union
from typing_extensions import Self

class BookNode(object):
	order: Orders
	prev_node: Self
	next_node: Self

	def __init__(self, order: Orders):
		self.order 		= order
		self.prev_node 	= None 
		self.next_node 	= None
		return

	def get_order(self):
		return self.order

	def get_prev_node(self):
		return self.prev_node

	def set_prev_node(self, node: Self):
		self.prev_node = node 
		return 

	def get_next_node(self):
		return self.next_node

	def set_next_node(self, node: Self):
		self.next_node = node 
		return

class BookSuperNode(object):
	price: int 
	volume: int
	prev_node: Self
	next_node: Self
	first_child: BookNode
	last_child: BookNode

	def __init__(self, price: int):
		self.price 			= price
		self.volume 		= 0
		self.prev_node 		= None
		self.next_node 		= None
		self.first_child 	= None
		self.last_child 	= None
		return 

	def get_price(self):
		return self.price

	def get_volume(self):
		return self.volume

	def set_volume(self, volume: int):
		self.volume = volume
		return

	def get_prev_node(self):
		return self.prev_node

	def set_prev_node(self, node: Self):
		self.prev_node = node
		return

	def get_next_node(self):
		return self.next_node

	def set_next_node(self, node: Self):
		self.next_node = node
		return

	def get_first_child(self):
		return self.first_child

	def set_first_child(self, child: BookNode):
		self.first_child = child
		return

	def get_last_child(self):
		return self.last_child

	def set_last_child(self, child: BookNode):
		self.last_child = child
		return

	def is_empty(self):
		return self.first_child is None

	def add(self, order: Orders):
		new_order_book_node = BookNode(order = order)

		if self.get_first_child() is None:
			self.set_first_child(child = new_order_book_node)
			new_order_book_node.set_prev_node(node = self)

		if self.get_last_child() is None:
			self.set_last_child(child = new_order_book_node)

		else:
			last_child = self.get_last_child()
			last_child.set_next_node(node = new_order_book_node)
			new_order_book_node.set_parent(node = last_child)

		self.volume += order.get_qty()
		return new_order_book_node

	def consume_first(self, order: Orders):
		"""
		Only consumes the first available BookNode. 
		Any quantity consumed is directly debited from both the first order and the new order

		Returns original order_id class with the amount consumed 
		"""
		first_child 	= self.get_first_child()
		available_qty 	= first_child.get_order().get_qty()
		order_qty  		= order.get_qty()
		consumed_qty 	= min(available_qty, order_qty)

		consumed_order 	= copy.deepcopy(first_child.get_order())
		consumed_order.set_qty(new_qty = consumed_qty)

		first_child.get_order().set_qty(new_qty = available_qty - consumed_qty)
		order.set_qty(new_qty = order_qty - consumed_qty)
		self.volume 	-= consumed_qty

		if first_child.get_order().get_qty() == 0:
			self.set_first_child(child = first_child.get_next_node())

		return consumed_order

from OrderCache import OrderCache

class OrderBook(object):
	book_head: BookSuperNode
	cache: OrderCache

	def __init__(self):
		self.book_head 	= None 
		self.cache  	= OrderCache()
		return

	def _insert_rule(self, cur_head_price: int, order_price: int):
		pass

	def _liquidate_rule(self, cur_head_price: int, order_price: int):
		pass

	def get_book_head(self):
		return self.book_head

	def set_book_head(self, node: BookSuperNode):
		self.book_head = node
		return

	def add(self, order: Orders):
		order_price 		= order.get_price()
		cur_head  			= self.get_book_head()
		new_book_super_node = BookSuperNode(price = order_price)
		node_ref 			= new_book_super_node.add(order = order)

		if cur_head is None:
			# No limit orders have been placed
			self.set_book_head(node = new_book_super_node)

		else:
			while 	cur_head is not None and \
					self._insert_rule(cur_head_price = cur_head.get_price(), order_price = order_price):
				prev_head 	= cur_head
				cur_head 	= cur_head.get_next_node()

			if cur_head is None:
				prev_head.set_next_node(node = new_book_super_node)
				new_book_super_node.set_prev_node(node = prev_head)

			else:
				if cur_head.get_price() == order_price:
					node_ref = cur_head.add(order = order)

				else:
					if cur_head == self.get_book_head():
						self.set_book_head(node = new_book_super_node)
						new_book_super_node.set_next_node(node = cur_head)
						cur_head.set_prev_node(node = new_book_super_node)

					else:
						new_book_super_node.set_next_node(node = cur_head)
						new_book_super_node.set_prev_node(node = prev_head)
						prev_head.set_next_node(node = new_book_super_node)

		self.cache.save(order_id = order.get_order_id(), node_ref = node_ref)
		return

	def consume(self, order: Orders):
		all_consumed_orders = []

		while 	order.get_qty() > 0 and \
				self.get_book_head() is not None and \
				self._liquidate_rule(cur_head_price = self.get_book_head().get_price(), order_price = order.get_price()):
			book_head 		= self.get_book_head()
			consumed_order 	= book_head.consume_first(order = order)
			all_consumed_orders.append(consumed_order)

			if book_head.is_empty():
				self.set_book_head(node = book_head.get_next_node())
				book_head.set_prev_node(node = None)
		return all_consumed_orders

	def cancel(self, order_id: str):
		node_ref 	= self.cache.get(order_id = order_id)
		parent 		= node_ref.get_prev_node()

		if type(parent) == BookSuperNode:
			parent.set_first_child(child = node_ref.get_next_node())

			if parent.is_empty():
				if parent.get_prev_node() is None and parent.get_next_node() is None:
					self.book_head = None 

				elif parent.get_prev_node() is None and parent.get_next_node() is not None:
					self.book_head = parent.get_next_node()
					parent.get_next_node().set_prev_node(node = None)

				elif parent.get_prev_node() is not None and parent.get_next_node() is None:
					parent.get_prev_node().set_next_node(node = parent.get_next_node())

				else:
					parent.get_prev_node().set_next_node(node = parent.get_next_node())
					parent.get_next_node().set_prev_node(node = parent.get_prev_node())

		else:
			parent.set_next_node(node = node_ref.get_next_node()) 
			
		if node_ref.get_next_node() is not None:
			node_ref.get_next_node().set_prev_node(node = parent)
		return

class BuyOrderBook(OrderBook):
	def _insert_rule(self, cur_head_price: int, order_price: int):
		return cur_head_price > order_price

	def _liquidate_rule(self, cur_head_price: int, order_price: int):
		return cur_head_price >= order_price

class SellOrderBook(OrderBook):
	def _insert_rule(self, cur_head_price: int, order_price: int):
		return cur_head_price < order_price

	def _liquidate_rule(self, cur_head_price: int, order_price: int):
		return cur_head_price <= order_price
