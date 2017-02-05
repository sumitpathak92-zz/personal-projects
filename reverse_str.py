input_str="this is great"
t=list()
def reverse(string):
    p=list()
    s=string.split(" ")
    t.append(s[i] for i in xrange(len(s)))
    for _ in range(len(t)):
        p.append(t.pop())
    print p
    output=" ".join(p[i] for i in range(len(p)))
    return output
reverse(input_str)