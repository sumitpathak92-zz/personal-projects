import Skype4Py
skype = Skype4Py.Skype()

skype.Attach()

print "full name", skype.CurrentUser.FullName
print "all contacts"

for user in skype.Friends:
    print '  ', user.FullName
