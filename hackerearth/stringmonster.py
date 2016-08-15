tc = int(raw_input())
while tc:
    tc-=1
    strtot = int(raw_input())
    input_str=list()
    for _ in xrange(strtot):
        input_str.append(raw_input())
    fin_str = sorted("".join(x for x in input_str))
    print "****",fin_str
    srch_str = raw_input()
    present=[0]*len(srch_str)
    for i in range(len(srch_str)):
        if srch_str[i] in fin_str:
            print srch_str[i]
            present[i]=1
    if 0 in present:
        print "NO"
    else: print "YES"

