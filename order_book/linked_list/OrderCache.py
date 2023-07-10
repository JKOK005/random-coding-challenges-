from OrderBook import BookNode

class OrderCache(object):
	cache = {}

	def save(self, order_id: str, node_ref: BookNode):
		self.cache[order_id] = node_ref

	def get(self, order_id: str):
		return self.cache.get(order_id, None)