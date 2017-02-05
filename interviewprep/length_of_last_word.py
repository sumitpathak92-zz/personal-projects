A = str(raw_input())
#A = list(string.split(" "))
list_words = list(A.split(" "))
list_words = set(list_words)
if '' in list_words:
    list_words.remove('')
list_words = list(list_words)
if len(list_words)==0: print 0
if len(list_words)==1:
    print 1
print len(list_words[-1])
#print list_words
#print len(list_words[-1])
