import copy
from Orders import * 
from typing_extensions import Self

class OrderBookNode(object):
	price: 			int
	orders: 		[Orders] 
	parent: 		Self
	left_child: 	Self
	right_child: 	Self

	def __init__(self, price: int):
		self.price 			= price
		self.orders 		= []
		self.parent 		= None
		self.left_child 	= None
		self.right_child 	= None
		return

	def set_left(self, left_child: Self):
		self.left_child = left_child

	def get_left(self):
		return self.left_child

	def set_right(self, right_child: Self):
		self.right_child = right_child

	def get_right(self):
		return self.right_child

	def set_parent(self, parent: Self):
		self.parent = parent

	def get_parent(self):
		return self.parent

	def add_orders(self, new_order: Orders):
		self.orders.append(new_order)

	def get_price(self):
		return self.price

	def set_price(self, price: int):
		self.price = price
		return

	def is_empty(self):
		return len(self.orders) == 0

	def consume_order(self, impending_order: Orders):
		evicted = []
		evict_indx = 0

		for existing_order in self.orders:
			if impending_order.get_qty() <= 0:
				break

			if existing_order.get_qty() <= impending_order.get_qty():
				consumed_order = copy.deepcopy(existing_order)
				impending_order.set_qty(new_qty = impending_order.get_qty() - existing_order.get_qty())
				existing_order.set_qty(new_qty = 0)
				evict_indx += 1
				
			else:
				consumed_order = copy.deepcopy(existing_order)
				existing_order.set_qty(new_qty = existing_order.get_qty() - impending_order.get_qty())
				impending_order.set_qty(new_qty = 0)

			evicted.append(consumed_order)

		self.orders = self.orders[evict_indx : ]
		return evicted

class SellOrderBookNode(OrderBookNode):
	def __init__(self, price: int):
		super(SellOrderBookNode, self).__init__(price = price)

class BuyOrderBookNode(OrderBookNode):
	def __init__(self, price: int):
		super(BuyOrderBookNode, self).__init__(price = price)

if __name__ == "__main__":
	# Test adding limit sell orders
	price = 10
	order_book_node = OrderBookNode(price = price)

	for qty in range(100):
		sell_order = LimitSellOrders(order_id = f"{qty}", price = price, qty = qty)
		order_book_node.add_orders(new_order = sell_order)

	for qty in range(100):
		assert(order_book_node.orders[qty].get_order_id() == f"{qty}")

	# Test consume limit sell orders
	all_consuming_buy_order = LimitBuyOrders(order_id = "inf", price = 1000, qty = 1000000)
	all_fulfilled_orders = order_book_node.consume_order(impending_order = all_consuming_buy_order)
	assert(len(all_fulfilled_orders) == 100)

	order_book_node = OrderBookNode(price = price)

	for qty in range(100):
		buy_order = LimitBuyOrders(order_id = f"{qty}", price = price, qty = qty)
		order_book_node.add_orders(new_order = buy_order)

	for qty in range(100):
		assert(order_book_node.orders[qty].get_order_id() == f"{qty}")

	# Test consume limit sell orders
	all_consuming_buy_order = LimitSellOrders(order_id = "inf", price = 1000, qty = 1000000)
	all_fulfilled_orders = order_book_node.consume_order(impending_order = all_consuming_buy_order)
	assert(len(all_fulfilled_orders) == 100)

	# Test empty order book node
	assert(order_book_node.is_empty())