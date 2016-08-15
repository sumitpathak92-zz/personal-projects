 # -*- coding: utf-8 -*-import re
list_of_formats = ['.pdf', '.ppt', '.xls', '.pptx', '.doc', '.docx']

def refine(url):
	for i in range(len(list_of_formats)):
		if url.endswith(list_of_formats[i]) or url.endswith(list_of_formats[i] + '/'):
			result = True
			break
		else:
			result = False
			continue
	return result		

print refine("http://example.com/one.xxx/")