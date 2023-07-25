import itertools
import time
from Orders import *
from SimulatedOrderBook import *
from TreeUtils import TreeUtils

if __name__ == "__main__":
	counter = itertools.count()
	simulated_order_book = SimulatedOrderBook()

	# Test insert buy limit orders
	for i in range(10, 1, -1):
		price 	= i
		qty 	= 20
		cur_counter = next(counter)

		buy_order = LimitBuyOrders(order_id = f"limit-buy-{price}", price = price, qty = qty)
		simulated_order_book.handle_orders(order = buy_order)

		TreeUtils.visualize(
			root = simulated_order_book.buy_book.get_book_head(),
			file_name = f"{cur_counter}-ADD-LIMIT-{price}-{qty}"
		)
		time.sleep(1)

	for i in range(9, 1, -1):
		price = i
		simulated_order_book.cancel(order_id = f"limit-buy-{price}")
		cur_counter = next(counter)

		TreeUtils.visualize(
			root = simulated_order_book.buy_book.get_book_head(),
			file_name = f"{cur_counter}-CANCEL-LIMIT-{price}-{qty}"
		)
		time.sleep(1)

	