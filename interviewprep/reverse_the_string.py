A = "fwbpudnbrozzifml osdt  ulc jsx kxorifrhubk ouhsuhf  sswz qfho dqmy  sn myq igjgip iwfcqq"
list_A = list(A.split(" "))
list_A = sorted(set(list_A), key=list_A.index)
print "1111", list_A
list_A.reverse()
list_A = [x for x in list_A if x!=""]
print "2222", list_A
print " ".join(w for w in list_A)
