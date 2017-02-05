 # -*- coding: utf-8 -*-# -*- coding: utf-8 -*-
import sys
list = ["汉字", "漢字"]
l1=[]
res = u"汉字".encode("utf-8")
print res
res1 = l1.append(res)

#res = list[0].decode().encode('utf-8')
print l1[0].decode('utf-8')

#print u'[' +  u','.join(w[0] for w in list) + u']'
#print ", ".join(list)
