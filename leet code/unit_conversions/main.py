class UnitConversion(object):
	known_conversions 	= {}
	conversion_units 	= {}

	def add_conversion(self, metric_a, metric_b, val):
		if metric_a not in self.known_conversions.keys():
			self.known_conversions[metric_a] = []
		self.known_conversions[metric_a].append(metric_b)
		self.conversion_units[(metric_a, metric_b)] = val

		if metric_b not in self.known_conversions.keys():
			self.known_conversions[metric_b] = []
		self.known_conversions[metric_b].append(metric_a)
		self.conversion_units[(metric_b, metric_a)] = 1 / val
		return

	def convert(self, cur_val, cur_metric, dest_metric, explored):
		if (cur_metric, dest_metric) in self.conversion_units.keys():
			return cur_val * self.conversion_units[(cur_metric, dest_metric)]

		else:
			next_units = self.known_conversions[cur_metric]
			unexplored_next_units = set(next_units) - set(explored)

			for each_unit in unexplored_next_units:
				conversion  	= self.conversion_units[(cur_metric, each_unit)]
				converted_val 	= self.convert(cur_val = conversion * cur_val, cur_metric = each_unit, dest_metric = dest_metric, explored = explored + [cur_metric])

				if converted_val is not None:
					return converted_val

			return None

if __name__ == "__main__":
	unit_converter = UnitConversion()
	unit_converter.add_conversion(metric_a = "m", metric_b = "ft", val = 3.28)
	unit_converter.add_conversion(metric_a = "ft", metric_b = "in", val = 12)
	unit_converter.add_conversion(metric_a = "hr", metric_b = "min", val = 60)
	unit_converter.add_conversion(metric_a = "min", metric_b = "sec", val = 60)
	val = unit_converter.convert(cur_val = 1, cur_metric = "sec", dest_metric = "hr", explored = [])
	print(val)
