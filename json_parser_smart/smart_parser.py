import json
from Queue import Queue

def formatKeyString(key_string):
	return key_string

def formatValString(val_string):
	content = val_string
	if not content.isdigit() or content in ['true', 'false']:
		content = content[1:-1] 	# Removes " " at start and end of value
		if content[0] == '{' and content[-1] == '}':
			start_brac 	= content[0]
			end_brac 	= content[-1]
			nested_json_string = str(praseJson(content[1:-1]))
			print(praseJson(content))
			content 	= start_brac + nested_json_string[1:-1] + end_brac
		else:
			content 	= '"' + content + '"'
	return content

def praseJson(json_string):
	# Split on key - value via ':' delimiter
	content = json_string[1:-1]  	# Remove the {} enclosing brackets
	split_list = content.split(":")
	json_dict = {}
	key_queue = Queue()
	val_queue = Queue()

	key_queue.put(formatKeyString(split_list[0]))

	val_string = ":".join(split_list[1:])
	val_queue.put(formatValString(val_string))

	# for indx in range(1, len(split_list) -1):
	# 	element = split_list[indx]
	# 	key_val_mix = element.split(",")
	# 	key = key_val_mix[-1]
	# 	val = ",".join(key_val_mix[:-1])
	# 	key_queue.put(formatKeyString(key))
	# 	val_queue.put(formatValString(val))

	# val_queue.put(split_list[-1])

	while not key_queue.empty():
		json_dict[key_queue.get()] = val_queue.get()

	return json_dict

if __name__ == "__main__":
	"""
	Assumptions
	1) Values do not contain ":" delimiter
	2) Keys do not contain any "," or ":" delimiter
	"""
	# content = '{"a":0,"nextkey":"{"value_no_2" : 10}","thisWorked??":true}'
	content = '{nextkey":"{"nested_key_1":10, "nested_key_2":"asd","nested_key_3":true}"}'
	print(praseJson(content))