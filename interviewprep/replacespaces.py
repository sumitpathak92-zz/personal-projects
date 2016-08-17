input_str = "Mr John Smith    "
true_len=13
str_as_list = [x for x in input_str]
new_lst=list()
for i in range(true_len):
    if input_str[i]==' ':
        new_lst.append('%20')
    else:
        new_lst.append(input_str[i])
new_str = ''.join(new_lst[i] for i in xrange(len(new_lst)))
print "new string",new_str
