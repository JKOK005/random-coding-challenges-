from Orders import *
from OrderBook import *	
from TreeUtils import *

class SimulatedOrderBook(object):
	buy_book: 	BuyOrderBook
	sell_book: 	SellOrderBook

	def __init__(self):
		self.buy_book 	= BuyOrderBook()
		self.sell_book 	= SellOrderBook()
		return 

	def handle_orders(self, order: Orders):
		if type(order) == MarketSellOrders:
			filfilled_orders = self.buy_book.consume(order = order)
		
		elif type(order) == LimitSellOrders:
			filfilled_orders = self.buy_book.consume(order = order)

			if order.get_qty() > 0:
				self.sell_book.add(order = order)

		elif type(order) == MarketBuyOrders:
			filfilled_orders = self.sell_book.consume(order = order)

		else:
			filfilled_orders = self.sell_book.consume(order = order)

			if order.get_qty() > 0:
				self.buy_book.add(order = order)

		return filfilled_orders

if __name__ == "__main__":
	simulated_order_book = SimulatedOrderBook()

	# Test insert buy limit orders
	for i in range(10):
		buy_order = LimitBuyOrders(order_id = f"{i}", price = i, qty = 10)
		simulated_order_book.handle_orders(order = buy_order)

	assert(TreeUtils.count_super_nodes(root = simulated_order_book.buy_book.get_book_head()) == 10)

	# Test sell limit partially taking out buy orders
	# Buys are from prices 0 - 9 @ 10 qty each
	# After order, we should take out buy limits from 5 - 9 and have 50 units of sell remaining
	sell_order 			= LimitSellOrders(order_id = f"take out 5 - 9", price = 5, qty = 100)
	fulfilled_orders 	= simulated_order_book.handle_orders(order = sell_order)
	print(fulfilled_orders)
	assert(len(fulfilled_orders) == 5)
	assert(TreeUtils.count_nodes(root = simulated_order_book.buy_book.get_book_head()) == 5)
	assert(TreeUtils.count_nodes(root = simulated_order_book.sell_book.get_book_head()) == 1)

	simulated_order_book = SimulatedOrderBook()

	# Test insert sell limit orders
	for i in range(10):
		sell_order = LimitSellOrders(order_id = f"{i}", price = i, qty = 10)
		simulated_order_book.handle_orders(order = sell_order)
	assert(TreeUtils.count_nodes(root = simulated_order_book.sell_book.get_book_head()) == 10)

	# Test buy limit partially taking out sell orders
	# Sells are from prices 0 - 9 @ 10 qty each
	# After order, we should take out sell limits from 0 - 5 and have 40 units of buy remaining
	buy_order 			= LimitBuyOrders(order_id = f"take out 5 - 9", price = 5, qty = 100)
	fulfilled_orders 	= simulated_order_book.handle_orders(order = buy_order)
	assert(len(fulfilled_orders) == 6)
	assert(TreeUtils.count_nodes(root = simulated_order_book.sell_book.get_book_head()) == 4)
	assert(TreeUtils.count_nodes(root = simulated_order_book.buy_book.get_book_head()) == 1)