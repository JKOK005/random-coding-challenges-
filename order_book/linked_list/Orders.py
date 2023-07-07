import math

class Orders(object):
	"""
	orders class contains the orders placed by the user

	Params:
	- order_id 		- Order ID submitted by the user
	- price 		- Price of the order
	- qty 			- Quantity of the order
	"""
	def __init__(self,
				 order_id: str, 
				 price: int, 
				 qty: int
				):
		self.order_id 	= order_id
		self.price 		= price
		self.qty 		= qty
		return

	def get_order_id(self):
		return self.order_id

	def get_price(self):
		return self.price 

	def set_qty(self, new_qty: int):
		self.qty = new_qty
		return

	def get_qty(self):
		return self.qty

class SellOrders(Orders):
	def __init__(self,
				 order_id: str,
				 price: int, 
				 qty: int
				):

		super(SellOrders, self).__init__(order_id = order_id,
										 price = price,
										 qty = qty
										)
		return

class LimitSellOrders(SellOrders):
	def __init__(self,
				 order_id: str,
				 price: int, 
				 qty: int
				):

		super(LimitSellOrders, self).__init__(order_id = order_id,
											  price = price,
											  qty = qty
											)
		return

class MarketSellOrders(SellOrders):
	def __init__(self,
				 order_id: str,
				 qty: int
				):

		super(MarketSellOrders, self).__init__(order_id = order_id,
											   price = -1,
											   qty = qty
											)
		return

class BuyOrders(Orders):
	def __init__(self,
				 order_id: str,
				 price: int, 
				 qty: int
				):

		super(BuyOrders, self).__init__(order_id = order_id,
										price = price,
										qty = qty
										)
		return

class LimitBuyOrders(BuyOrders):
	def __init__(self,
				 order_id: str,
				 price: int, 
				 qty: int
				):

		super(LimitBuyOrders, self).__init__(order_id = order_id,
											 price = price,
											 qty = qty
											)
		return

class MarketBuyOrders(BuyOrders):
	def __init__(self,
				 order_id: str,
				 qty: int
				):

		super(MarketBuyOrders, self).__init__(order_id = order_id,
											  price = math.inf,
											  qty = qty
											)
		return