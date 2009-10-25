#!/usr/bin/python
# -*- coding: utf-8 -*-
#Parser danych gieldowych
# manequin 2009/10/26

import sys
import os
from sqlalchemy import *
import urllib
from shutil import copyfile

copyfile("../parser-wiki/cfg.py", "./cfg.py")
from cfg import dane

date_start = str(sys.argv[1][0:4])+str(sys.argv[1][5:7])+str(sys.argv[1][8:10])
date_end = str(sys.argv[2][0:4])+str(sys.argv[2][5:7])+str(sys.argv[2][8:10])
if len(sys.argv) > 3:
    debug = sys.argv[3]=="-d"
else:
    debug=0

class gielda:
    
    def update_files(self):
        if debug:
            print "pobieranie danych..."
        temp_zip = "./dane/temp_zip.zip"
        download_web=urllib.urlopen("http://bossa.pl/pub/indzagr/mstock/mstzgr.zip")
        download_local=open(temp_zip,'w')
        download_local.write(download_web.read())
        download_web.close()
        download_local.close()
        if debug:
            print "rozpakowywanie archiwum..."
        import zipfile
        pack=zipfile.ZipFile(temp_zip,'r')
        for name in pack.namelist():
            pack.extract(name, "./dane/")
        os.remove(temp_zip)    
    
    def read(self, start, end):
        spolki=[]
        folder="./dane/"
        for filename in os.listdir(folder):
            spolka=[]
            data=open(folder+filename,'r').read()
            if debug:
                sys.stdout.write ("trwa wyodrębnianie danych spółki: "+ str(filename.split('.')[0]))
            for i in data.splitlines()[1:-1]:
                line=i.split(',')
                element=line[0],line[1],line[5]
                if int(start) <= int(element[1]) <= int(end):
                    spolka.append(element)
            spolki.append(spolka)
            if debug:
                print ("        [OK]")
        return (spolki)

    def clear(self):
        for filename in os.listdir("./dane/"):
            os.remove("./dane/"+filename)
        os.remove ("./cfg.py")
        os.remove ("./cfg.pyc")
        if debug:
            print ("pliki tymczasowe usunięte")

    def init_db(self):
        global db, metadata
        db = create_engine("mysql://"+dane()[0]+":"+dane()[1]+"@"+dane()[2]+"/"+dane()[3]+"")
        metadata = MetaData(db)

    def create_db(self):
        for filename in os.listdir("./dane/"):
            event = Table(filename[0:-4].lower(), metadata,
                Column("date", String(8), primary_key=True),
                Column("value", Float))
            event.drop()
            event.create(checkfirst=True)

    def add(self, name, dat, val):
        parser = Table(name, metadata, autoload=True)
        insert=parser.insert()
        insert.execute(date=dat, value=val)
    
notowanie=gielda()
notowanie.update_files()
spolki = notowanie.read(date_start, date_end)
notowanie.init_db()
notowanie.create_db()
for spolka in spolki:
    for i in spolka:
        notowanie.add(i[0].lower(), i[1], float(i[2]))
notowanie.clear()
