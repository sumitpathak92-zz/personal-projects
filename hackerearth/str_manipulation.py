tc=int(raw_input())
while tc:
    tc-=1
    S, N, M=raw_input().split(" ")
    N, M=int(N), int(M)
    print S
    print N
    print M
    s=S[N:M+1]
    t=str("".join(sorted(s)))
    #print s, t
    S.replace(s, t)
    print S
