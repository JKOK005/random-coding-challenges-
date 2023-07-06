from Orders import *
from OrderBookNode import *	

class OrderBookTree(object):
	root_ptr: 	OrderBookNode
	front_ptr:	OrderBookNode

	def __init__(self):
		self.root_ptr 	= None
		self.front_ptr 	= None

	def cancel(self, cur_order: Orders):
		# TODO: Implement cancel
		pass

	def _create_new_node(self, new_order: Orders):
		pass

	def _seek_front_ptr(self, cur_root: OrderBookNode):
		pass

	def _add(self, cur_root: OrderBookNode, new_order: Orders):
		if cur_root.get_price() == new_order.get_price():
			cur_root.add_orders(new_order = new_order)

		elif cur_root.get_price() > new_order.get_price():
			if cur_root.get_left() is not None:
				self._add(cur_root = cur_root.get_left(), new_order = new_order)

			else:
				new_node = self._create_new_node(new_order = new_order)
				cur_root.set_left(left_child = new_node)
				new_node.set_parent(parent = cur_root)

		else:
			if cur_root.get_right() is not None:
				self._add(cur_root = cur_root.get_right(), new_order = new_order)

			else:
				new_node = self._create_new_node(new_order = new_order)
				cur_root.set_right(right_child = new_node)
				new_node.set_parent(parent = cur_root)
		return

	def add(self, new_order: Orders):
		if self.root_ptr is None:
			new_node  		= self._create_new_node(new_order = new_order)
			self.root_ptr 	= new_node

		else:
			self._add(cur_root = self.root_ptr, new_order = new_order)

		self.front_ptr = self._seek_front_ptr(cur_root = self.root_ptr)
		return

class BuyOrderBookTree(OrderBookTree):
	def __init__(self):
		super(BuyOrderBookTree, self).__init__()

	def _seek_front_ptr(self, cur_root: OrderBookNode):
		# Seeks the largest priced node
		if cur_root.get_right() is not None:
			return self._seek_front_ptr(cur_root = cur_root.get_right())
		return cur_root

	def _create_new_node(self, new_order: BuyOrders):
		new_buy_book_node = BuyOrderBookNode(price = new_order.get_price())
		new_buy_book_node.add_orders(new_order = new_order)
		return new_buy_book_node

	def consume_order(self, new_order: SellOrders):
		all_fulfilled_orders = []

		while 	self.root_ptr is not None and \
				new_order.get_qty() > 0 and \
				new_order.get_price() <= self.front_ptr.get_price():
			
			all_fulfilled_orders += self.front_ptr.consume_order(impending_order = new_order)

			if self.front_ptr.is_empty():
				parent 		= self.front_ptr.get_parent()
				left_child 	= self.front_ptr.get_left()
				right_child = self.front_ptr.get_right()

				if parent is None and left_child is None and right_child is None:
					self.root_ptr 	= None 
					self.front_ptr 	= None

				elif parent is None and left_child is not None and right_child is None:
					self.root_ptr 	= left_child
					self.root_ptr.set_parent(parent = parent)
					self.front_ptr 	= self._seek_front_ptr(cur_root = self.root_ptr)

				elif parent is not None and left_child is None and right_child is None:
					parent.set_right(right_child = None)
					self.front_ptr 	= parent

				elif parent is not None and left_child is not None and right_child is None:
					parent.set_right(right_child = left_child)
					self.front_ptr 	= parent

				else:
					raise Exception("Look into this")
		return all_fulfilled_orders

class SellOrderBookTree(OrderBookTree):
	def __init__(self):
		super(SellOrderBookTree, self).__init__()

	def _seek_front_ptr(self, cur_root: OrderBookNode):
		# Seeks the lowest priced node
		if cur_root.get_left() is not None:
			return self._seek_front_ptr(cur_root = cur_root.get_left())
		return cur_root

	def _create_new_node(self, new_order: SellOrders):
		new_sell_book_node = SellOrderBookNode(price = new_order.get_price())
		new_sell_book_node.add_orders(new_order = new_order)
		return new_sell_book_node

	def consume_order(self, new_order: BuyOrders):
		all_fulfilled_orders = []

		while 	self.root_ptr is not None and \
				new_order.get_qty() > 0 and \
				new_order.get_price() >= self.front_ptr.get_price():

			all_fulfilled_orders += self.front_ptr.consume_order(impending_order = new_order)

			if self.front_ptr.is_empty():
				parent 		= self.front_ptr.get_parent()
				left_child 	= self.front_ptr.get_left()
				right_child = self.front_ptr.get_right()

				if parent is None and left_child is None and right_child is None:
					self.root_ptr 	= None 
					self.front_ptr 	= None

				elif parent is None and left_child is None and right_child is not None:
					self.root_ptr 	= right_child
					self.root_ptr.set_parent(parent = parent)
					self.front_ptr 	= self._seek_front_ptr(cur_root = self.root_ptr)

				elif parent is not None and left_child is None and right_child is None:
					parent.set_left(left_child = None)
					self.front_ptr 	= parent

				elif parent is not None and left_child is None and right_child is not None:
					parent.set_left(left_child = right_child)
					self.front_ptr 	= parent

				else:
					raise Exception("Look into this")
		return all_fulfilled_orders

if __name__ == "__main__":
	# Test insertion into buy order book ascending
	buy_order_book_tree = BuyOrderBookTree()

	for i in range(10):
		buy_order = LimitBuyOrders(order_id = f"{i}", price = i, qty = 10)
		buy_order_book_tree.add(new_order = buy_order)

	assert(buy_order_book_tree.root_ptr.get_price() == 0)
	assert(buy_order_book_tree.front_ptr.get_price() == 9)

	# Consume all buy orders with remaining sell orders
	sell_order = MarketSellOrders(order_id = "consume all", qty = 150)
	fulfilled_orders = buy_order_book_tree.consume_order(new_order = sell_order)

	assert(len(fulfilled_orders) == 10)
	assert(sell_order.get_qty() == 50)

	# Test insertion into buy order book descending
	buy_order_book_tree = BuyOrderBookTree()

	for i in range(9, -1, -1):
		buy_order = LimitBuyOrders(order_id = f"{i}", price = i, qty = 10)
		buy_order_book_tree.add(new_order = buy_order)

	assert(buy_order_book_tree.root_ptr.get_price() == 9)
	assert(buy_order_book_tree.front_ptr.get_price() == 9)

	# Consume all buy orders with remaining sell orders
	sell_order = MarketSellOrders(order_id = "consume all", qty = 150)
	fulfilled_orders = buy_order_book_tree.consume_order(new_order = sell_order)

	assert(len(fulfilled_orders) == 10)
	assert(sell_order.get_qty() == 50)

	#########################################################################################################

	# Test insertion into sell order book ascending
	sell_order_book_tree = SellOrderBookTree()

	for i in range(10):
		sell_order = LimitSellOrders(order_id = f"{i}", price = i, qty = 10)
		sell_order_book_tree.add(new_order = sell_order)

	assert(sell_order_book_tree.root_ptr.get_price() == 0)
	assert(sell_order_book_tree.front_ptr.get_price() == 0)

	# Consume all sell orders with remaining sell orders
	buy_order = MarketBuyOrders(order_id = "consume all", qty = 150)
	fulfilled_orders = sell_order_book_tree.consume_order(new_order = buy_order)

	assert(len(fulfilled_orders) == 10)
	assert(buy_order.get_qty() == 50)

	# Test insertion into sell order book descending
	sell_order_book_tree = SellOrderBookTree()

	for i in range(9, -1, -1):
		sell_order = LimitSellOrders(order_id = f"{i}", price = i, qty = 10)
		sell_order_book_tree.add(new_order = sell_order)

	assert(sell_order_book_tree.root_ptr.get_price() == 9)
	assert(sell_order_book_tree.front_ptr.get_price() == 0)

	# Consume all sell orders with remaining sell orders
	buy_order = MarketBuyOrders(order_id = "consume all", qty = 150)
	fulfilled_orders = sell_order_book_tree.consume_order(new_order = buy_order)

	assert(len(fulfilled_orders) == 10)
	assert(buy_order.get_qty() == 50)