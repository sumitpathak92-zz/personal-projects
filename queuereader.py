 # -*- coding: utf-8 -*-import boto.sqs

conn=boto.sqs.connect_to_region(region_name='us-east-1',aws_access_key_id='AKIAINMHHN5ZF7QFYFPQ',aws_secret_access_key='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')
q=conn.get_queue('SUMIT_SCOTIA_DC_RANK_PENDING')


count = 0

while 1:

   rs = q.get_messages()
   if len(rs)!=0:
      m = rs[0]
      print(m.get_body())
      count+=1
   else:
      break
print count
