def unique_in_order(iterable):
    li = []
    if isinstance(iterable, list):
        if len(iterable)==0:
            return li
        string = ''.join(str(x) for x in iterable)
        li.append(string[0])
        if len(string)==1:
            return string[0]
        for i in range(1, len(string)):
            if string[i] == li[len(li)-1]:
                pass
            else:
                li.append(string[i])
        return map(int, li)
    else:
        if len(iterable)==0:
            return li
        if len(iterable)==1:
            li.append(iterable[0])
            return li
        li.append(iterable[0])
        for i in range(1, len(iterable)):
            if iterable[i] == li[len(li)-1]:
                pass
            else:
                li.append(iterable[i])
        return li


if __name__=='__main__':
    print unique_in_order([1,2,2,3,3])
    print unique_in_order('12233')

