#import sys
import re
def main():
    tc=int(raw_input())
    st = list()
    while tc:
        tc-=1
        if int(raw_input().split()[0])!=1:
            a, b=raw_input().split()
            st.append(int(b))
        elif int(raw_input().split()[-1])==1:
            if len(st)!=0:
                print st.pop()
            else:
                print "No Food"
        #a, b = raw_input().split()
        #st.append(int(b))

if __name__=='__main__':
    main()
