# -*- coding: utf-8 -*- 

import json
import IPython
from Queue import Queue

def formatKeyString(key_string):
	key = key_string.lower()
	return key_string.replace('"','').strip()	

def formatValString(val_string):
	content = val_string.strip()
	try:
		if (not content.isdigit() or content in ['true', 'false']) and (content not in ['""', '"', '', '{}']):
			content = content[1:-1]
			if content[0] == '{' and content[-1] == '}':
				parsed_json = parseJson(content)
				return str(parsed_json)
			elif content[0] == '[' and content[-1] == ']':
				pass
			else:
				content.replace('"', "'")
				content = content

		return content.strip()
	except Exception as ex:
		IPython.embed()

def keyValSplit(content, delimiter):
	split_list = content.split(delimiter)

	curly_brac_count = 0
	sq_brac_count = 0

	merged_list = []
	tmp_string 	= ""
	IPython.embed()

	for each in split_list:
		tmp_string += each
		curly_brac_count += each.count("{") - each.count("}")
		sq_brac_count += each.count("[") - each.count("]")

		if curly_brac_count == 0 and sq_brac_count == 0:
			merged_list.append(tmp_string)	
			tmp_string = ""
		else:
			tmp_string += delimiter
	
	return merged_list

def parseJson(json_string):
	# Split on key - value via ':' delimiter
	content = json_string[1:-1]  	# Remove the {} enclosing brackets
	split_list = keyValSplit(content, ":")
	json_dict = {}
	key_queue = Queue()
	val_queue = Queue()

	print(json_string)
	key_queue.put(formatKeyString(split_list[0]))

	for indx in range(1, len(split_list) -1):
		element = split_list[indx]
		key_val_mix = element.split(",")
		key = key_val_mix[-1]
		val = ",".join(key_val_mix[:-1])
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
	# content = '{"a":0,"nextkey":"{"value_no_2" : 10}","thisWorked??":true}'
	# content = '{"nextkey":"{"nested_key_1":10, "nested_key_2":"as dasd this is the "best" place to eat bruh", "nested_key_3":true}", "brackets":"["http:sas","www:ddd"]", "test":10, "thisFalse":false}'
	content = '{"nested_key_1":10, "nested_key_2":"as dasd", "nested_key_3":true}'
	parsed 	= parseJson(x)
	IPython.embed()
	print(parsed)