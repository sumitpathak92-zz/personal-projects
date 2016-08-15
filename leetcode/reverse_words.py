import re

s = " "
if not re.search('[a-zA-Z0-9]+', s):
    print "nada!"
else:
    s_list = list(s.split(" "))
    s_list.reverse()
    # x = " ".join(s_list[i] for i in xrange(len(s_list)))
    print " ".join(s_list[i] for i in xrange(len(s_list)))
