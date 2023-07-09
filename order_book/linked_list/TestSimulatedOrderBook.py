import itertools
from Orders import *
from SimulatedOrderBook import *
from TreeUtils import TreeUtils

if __name__ == "__main__":
	counter = itertools.count()
	simulated_order_book = SimulatedOrderBook()

	# Test insert buy limit orders
	for i in range(10):
		price 	= 10 + i
		qty 	= 20
		cur_counter = next(counter)

		buy_order = LimitBuyOrders(order_id = f"{i}", price = price, qty = qty)
		simulated_order_book.handle_orders(order = buy_order)

		TreeUtils.visualize(
			root = simulated_order_book.buy_book.get_book_head(),
			file_name = f"{cur_counter}-ADD-LIMIT-{price}-{qty}"
		)

	for i in range(10):
		price 	= i
		qty 	= 10
		cur_counter = next(counter)

		buy_order = LimitBuyOrders(order_id = f"{i}", price = price, qty = qty)
		simulated_order_book.handle_orders(order = buy_order)

		TreeUtils.visualize(
			root = simulated_order_book.buy_book.get_book_head(),
			file_name = f"{cur_counter}-ADD-LIMIT-{price}-{qty}"
		)