#!/usr/bin/python
# -*- coding: utf-8 -*-
import sys
import sqlalchemy
import urllib
import time

### data wejsciowa ###
date = "20090115"
#date = str(sys.argv[1])

class gielda:

    def getdata(self, date):
        if int(date[0:6]) == int((time.strftime('%Y%m'))):
            notowanie = 'http://bossa.pl/pub/ciagle/omega/cgl/'+date+'.txt'
            data = str(urllib.urlopen(notowanie).read())
        elif (int(date[0:4])==int(time.strftime('%Y')) and (int(time.strftime("%m"))-int(date[4:6]))<3):
            import zipfile
            import os
            if not os.path.exists(str("dane/"+date+".txt")):
                import zipfile              
                if not os.path.exists(str("dane/zip/"+date[4:6]+"-"+date[0:4]+".zip")):
                    url=str("http://bossa.pl/pub/ciagle/omega/cgl/"+date[4:6]+"-"+date[0:4]+".zip")
                    download_web= urllib.urlopen(url)
                    download_local= open("./dane/zip/"+url.split('/')[-1], 'w')
                    download_local.write(download_web.read())
                    download_web.close()
                    download_local.close()
                #zip pobrany, a przynajmniej mam taka nadzieje... mozna go sprobowac rozpakowywac
                pathtozip= str("dane/zip/"+date[4:6]+"-"+date[0:4]+".zip")
                pathtotxt= "dane/"
                sourcezip=zipfile.ZipFile(pathtozip, 'r')
                for name in sourcezip.namelist():
                    if name.find('.txt')!= -1:
                        sourcezip.extract(name, pathtotxt)
                sourcezip.close()
            data=open(str("dane/"+date+".txt")).read()
            
        elif (int(date[0:4])==int(time.strftime('%Y'))):
            import zipfile
            import os
            if not os.path.exists(str("dane/"+date+".txt")):
                import zipfile              
                if not os.path.exists(str("dane/zip/"+date[0:4]+"a.zip")):
                    url=str("http://bossa.pl/pub/ciagle/omega/cgl/"+date[0:4]+"a.zip")
                    download_web= urllib.urlopen(url)
                    download_local= open("./dane/zip/"+url.split('/')[-1], 'w')
                    download_local.write(download_web.read())
                    download_web.close()
                    download_local.close()
                pathtozip= str("dane/zip/"+date[0:4]+"a.zip")
                pathtotxt= "dane/zip/"
                sourcezip=zipfile.ZipFile(pathtozip, 'r')
                for name in sourcezip.namelist():
                    if name.find('.zip')!= -1:
                        sourcezip.extract(name, pathtotxt)
                sourcezip.close()
            #zipy rozpakowane, dalej jedziemy jak wczesniej... wiem, ze to nie jest ladne, ale dziala, sorry :P
                if not os.path.exists(str("dane/zip/"+date[4:6]+"-"+date[0:4]+".zip")):
                    url=str("http://bossa.pl/pub/ciagle/omega/cgl/"+date[4:6]+"-"+date[0:4]+".zip")
                    download_web= urllib.urlopen(url)
                    download_local= open("./dane/zip/"+url.split('/')[-1], 'w')
                    download_local.write(download_web.read())
                    download_web.close()
                    download_local.close()
                pathtozip= str("dane/zip/"+date[4:6]+"-"+date[0:4]+".zip")
                pathtotxt= "dane/"
                sourcezip=zipfile.ZipFile(pathtozip, 'r')
                for name in sourcezip.namelist():
                    if name.find('.txt')!= -1:
                        sourcezip.extract(name, pathtotxt)
                sourcezip.close()          

            data=open(str("dane/"+date+".txt")).read()

        else:
            import zipfile
            import os
            if not os.path.exists(str("dane/"+date+".txt")):
                import zipfile              
                if not os.path.exists(str("dane/zip/"+date[0:4]+".zip")):
                    url=str("http://bossa.pl/pub/ciagle/omega/cgl/"+date[0:4]+".zip")
                    download_web= urllib.urlopen(url)
                    download_local= open("./dane/zip/"+url.split('/')[-1], 'w')
                    download_local.write(download_web.read())
                    download_web.close()
                    download_local.close()
                pathtozip= str("dane/zip/"+date[0:4]+".zip")
                pathtotxt= "dane/zip/"
                sourcezip=zipfile.ZipFile(pathtozip, 'r')
                for name in sourcezip.namelist():
                    if name.find('.zip')!= -1:
                        sourcezip.extract(name, pathtotxt)
                sourcezip.close()
            #zipy rozpakowane, dalej jedziemy jak wczesniej... wiem, ze to nie jest ladne, ale dziala, sorry :P
                if not os.path.exists(str("dane/zip/"+date[4:6]+"-"+date[0:4]+".zip")):
                    url=str("http://bossa.pl/pub/ciagle/omega/cgl/"+date[4:6]+"-"+date[0:4]+".zip")
                    download_web= urllib.urlopen(url)
                    download_local= open("./dane/zip/"+url.split('/')[-1], 'w')
                    download_local.write(download_web.read())
                    download_web.close()
                    download_local.close()
                pathtozip= str("dane/zip/"+date[4:6]+"-"+date[0:4]+".zip")
                pathtotxt= "dane/"
                sourcezip=zipfile.ZipFile(pathtozip, 'r')
                for name in sourcezip.namelist():
                    if name.find('.txt')!= -1:
                        sourcezip.extract(name, pathtotxt)
                sourcezip.close()          

            data=open(str("dane/"+date+".txt")).read()
            
        return(data)

    def spolka (self, data):
        firma=[]
        for i in data.splitlines():
            line=i.split(',')
            element = line[0],line[4]
            firma.append(element)
        return(firma)

    def preview (self, spolka):
        for i in spolka:
            print (i)
            

notowanie=gielda()
data = notowanie.getdata(date)
spolka = notowanie.spolka(data)
notowanie.preview(spolka)
