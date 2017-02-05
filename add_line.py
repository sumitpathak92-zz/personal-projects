 # -*- coding: utf-8 -*-import glob, os
import fnmatch

#line_to_add = 'hi i am here'
filename = 'login.py'
dirpath = '/home/sumit/personal files/personal projects'
matches = []


def hello_sumit():
    print "hi sumit pathak"




def directory_scanner(dirpath):
    os.chdir(dirpath)
    print "i am inside "
    for root, dirnames, filenames in os.walk(dirpath):
        for filename in fnmatch.filter(filenames, '*.py'):
            matches.append(os.path.join(root, filename))
    print "match count is",len(matches)
    print "matches are", matches
    for match in matches:
        appendatstart(match)
    #os.chdir("/alps")

#for file in glob.glob("*.txt"):
#    print(file)

def appendatstart(filename):
    with open(filename, 'r+') as f:
        first_line = f.readline()
        if first_line != " # -*- coding: utf-8 -*-":
            lines=f.readlines()
            f.seek(0)
            f.write(" # -*- coding: utf-8 -*-")
            f.write(first_line)
            f.writelines(lines) 

if __name__=='__main__':
    directory_scanner(dirpath)
