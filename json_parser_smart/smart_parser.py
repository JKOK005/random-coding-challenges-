import json
from Queue import Queue

def formatKeyString(key_string):
	return key_string

def formatValString(val_string):
	return val_string

def praseJson(json_string):
	# Split on key - value via ':' delimiter
	content = json_string[1:-1]  	# Remove the {} enclosing brackets
	split_list = content.split(":")
	json_dict = {}
	key_queue = Queue()
	val_queue = Queue()

	key_queue.put(split_list[0])
	for indx in range(1, len(split_list) -1):
		element = split_list[indx]
		key_val_mix = element.split(",")
		key = key_val_mix[-1]
		val = "".join(key_val_mix[:-1])
		key_queue.put(formatKeyString(key))
		val_queue.put(formatValString(val))

	val_queue.put(split_list[-1])

	while not key_queue.empty():
		json_dict[key_queue.get()] = val_queue.get()

	return json_dict

if __name__ == "__main__":
	"""
	Assumptions
	1) Values do not contain ":" delimiter
	2) Keys do not contain any "," or ":" delimiter
	"""
	content = '{"a":"value","nextkey":"value_no_2","thisWorked??":"HopeSo"}'
	print(praseJson(content))