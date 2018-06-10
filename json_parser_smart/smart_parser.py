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
	# content = '{"nested_key_1":10, "nested_key_2":"as dasd", "nested_key_3":true}'
	content = """{"ID":"THGFIST000001i7","OwnerID":0,"BusinessUserID":0,"BusinessType":0,"Name":"ข้าวมันไก่ ก๋วยเตี๋ยวไก่มะระ - วชิรธรรมสาธิต 21","Latitude":"13.684632","Longitude":"100.620319","CountryID":3,"CityID":5,"PhoneNumber":"+66800000000","Location":{`country`:`Thailand`,`timezone`:`Asia/Bangkok`,`state`:`Bangkok`,`city`:`Bangkok`,`district`:``,`street`:`ซอยวชิรธรรมสาธิต 21 ถนนสขุมวิท`,`house`:``,`postcode`:``,`address`:`ซอยวชิรธรรมสาธิต 21 ถนนสขุมวิท`},"EstimatedDeliveryTime":45,"Contact":{"name":"","phone":"","email":"","invoice_email":""},"Contract":{"date":"","expireDate":"","commission":"0","charge":"0","npwp":"","taxpayer_name":"","taxpayer_address":"","virtual_account":"","virtual_account_name":"","partner":0},"IconHref":"https@//dtlscuh0h90jk.cloudfront.net/seller/icons/1368_816.jpg","PhotoHref":["https://dtlscuh0h90jk.cloudfront.net/seller/photos/1368_702.jpg"],"SmallPhotoHref":["https://dtlscuh0h90jk.cloudfront.net/seller/pics/THGFIST000001i7_0_624.jpg"],"Tags":"","Tax":"0","ServiceHours":{"sun":"18:00-21:00","mon":"18:00-21:00","tue":"18:00-21:00","wed":"18:00-21:00","thu":"18:00-21:00","fri":"18:00-21:00","sat":"18:00-21:00"},"OpeningHours":{"sun":"18:00-22:00","mon":"18:00-22:00","tue":"18:00-22:00","wed":"18:00-22:00","thu":"18:00-22:00","fri":"18:00-22:00","sat":"18:00-22:00"},"Description":"เบอร์ติดต่อ :","Metadata":{"cuisine":"อาหารไทย","halal":false,"mall":false,"doc_id":"1368","chain_number":"","chain_name":""},"Extra":{},"Status":1,"Created":":0001-01-01 00:00:00 +0000 UTC","Updated":":0001-01-01 00:00:00 +0000 UTC","CloseStatus":0}"""
	parsed 	= parseJson(x)
	IPython.embed()
	print(parsed)