def toString(a):
    return ''.join(a)

final_res = list()
def recursive_function(string, first, last):
    if first == last:
        print toString(string)
    for i in xrange(first, last+1):
        string[first], string[i] = string[i], string[first]
        recursive_function(string, first+1, last)
        string[first], string[i] = string[i], string[first]

input_str = 'ABC'
n = len(input_str)
a = list(input_str)
recursive_function(a, 0, n-1)
