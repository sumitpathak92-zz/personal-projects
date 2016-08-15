 # -*- coding: utf-8 -*-

from neo4j.v1 import GraphDatabase, basic_auth

driver = GraphDatabase.driver("bolt://localhost", auth=basic_auth("neo4j", "--23october--"))
session = driver.session()

session.run("CREATE (a:Person {name: 'Sumit', title: 'coder'})")

result = session.run("MATCH (a:Person) WHERE a.name='Sumit' RETURN a.name AS name, a.title AS title")
print "result is", type(result)

for record in result:
    print("%s %s" % (record["title"], record["name"]))


session.close()
