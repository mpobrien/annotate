#!/usr/bin/python

import pymongo
import sys, re
from pymongo import Connection

alltenses = [('present',6),
             ('imperfect',6),
             ('preterite',6),
             ('future',6),
             ('conditional',6),
             ('imperative',5),
             ('present_sub',6),
             ('imperfect_sub',6),
             ('gerund',1),
             ('pastpart',1) ]

def main(args):
    inputfile = file(args[0], 'r')
    lines = inputfile.readlines()
    inputfile.close()
    connection = Connection('localhost', 42126)
    db = connection.mydb
    verbs = db.spanish_verbs
    newverbs = getdocs(lines)
    verbs.insert(newverbs)
    verbs.ensure_index('conj')
    verbs.ensure_index('infinitive', unique=True)

def getdocs(lines):
    #result = []
    for line in lines:
        line = line.strip()
        words = re.split('\s+', line)
        if not words or len(words) != 50: continue
        infinitive = words[0]
        yield {'infinitive': infinitive, 'conj' : words}
        #result.append( {'infinitive': infinitive, 'conj' : words} )
    #return result



if __name__ == '__main__': main(sys.argv[1:])

