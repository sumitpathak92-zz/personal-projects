 # -*- coding: utf-8 -*-import time
def cow(array,dp,days):
    c=0
    try:
        for i in xrange(days-2,-1,-1):
            c+=1
            for j in xrange(0,i+1):
                dp[i].append(max((i+1)*array[j]+dp[i+1][j+1],(i+1)*array[j+c]+dp[i+1][j]))
    except Exception as e:
        pass
    return dp[0][0]

start = time.time()    
days=input()
dp=[]
dp_array=[]
array=[]
for i in xrange(0,days):
	item=input()
	array.append(item)
	dp.append([])
	dp_array.append(days*item)
dp.pop()
dp.append(dp_array)
end = time.time()
print end-start
print cow(array,dp,days)