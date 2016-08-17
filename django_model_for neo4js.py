 # -*- coding: utf-8 -*-
from neo4jdjango.db import models

class Person(models.NodeModel):
	name = models.StringProperty
	age = models.IntegerProperty

	friends = models.Relationship('self', rel_type = 'friends_with')


# Properties example

class OnlinePerson(Person):
	email = models.EmailProperty()
	homepage = models.URLProperty()


class EmployedPerson(Person):
	job_title = models.StringProperty(indexed=True)


# Relationship example

class Pet(models.NodeModel):
	owner = models.Relationship(Person,
								rel_type = 'owner',
								single = True,
								related_name = 'pets'
								)


pete = Person.objects.create(name='Sumit', age=24)
garfield = Pet.objects.create()
pete.pets.add(garfield)
print list(pete.pets.all())