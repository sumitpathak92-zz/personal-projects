for _ in xrange(int(raw_input())):
	inputs=list(map(int, raw_input().split()))
	x=inputs[1] 
	y=inputs[2] 
	z=inputs[3]
	array = map(int, raw_input().split())
	print array
	max_elem = max(array)
	print "here"
	all_fact=list()
	all_fact = all_factors(max_elem)
	print all_fact
	if set(array).issubset(all_fact): print "She can"
	else: print "She can't"

def all_factors(x):
	l = []
	for i in range(1, x+1):
		if x%i==0: l.append(i)
	return l
