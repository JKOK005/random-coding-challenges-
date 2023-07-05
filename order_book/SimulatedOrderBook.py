from Orders import *
from OrderBookNode import *
from OrderBookTree import *	

class SimulatedOrdrBook(object):
	buy_book_tree: 	BuyOrderBookTree
	sell_book_tree: SellOrderBookTree

	def __init__(self):
		self.buy_book_tree 	= BuyOrderBookTree()
		self.sell_book_tree = SellOrderBookTree()
		return 

	def handle_orders(self, order: Orders):
		if type(order) == MarketSellOrders:
			filfilled_orders = buy_book_tree.consume_order(new_order = order)
		
		elif type(order) == LimitSellOrders:
			filfilled_orders = buy_book_tree.consume_order(new_order = order)

			if order.get_qty() > 0:
				sell_book_tree.add(new_order = order)

		elif type(order) == MarketBuyOrders:
			filfilled_orders = sell_book_tree.consume_order(new_order = order)

		else:
			filfilled_orders = sell_book_tree.consume_order(new_order = order)

			if order.get_qty() > 0:
				buy_book_tree.add(new_order = order)

		return filfilled_orders