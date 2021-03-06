#!/usr/bin/python
# -*- coding: utf-8 -*-

###
# Grzegorz Wieczorek 07.09.2009 00:43
# gg: 5905482
# jabb: grzew@jabster.pl
# email: grzewster@gmail.com
# 
###

import urllib2
import BeautifulSoup
import re
import sys
from sqlalchemy import *
from cfg import dane
import datetime

zmienne=[]

for arg in sys.argv:
    zmienne.append(arg)


class wiki_parser:

    ##### pobieranie danych #####

    ### Inicjowanie przegladarki - oszukiwanie wikipedii przed 403
 
    def przegladarka(self,url):
        request = urllib2.Request(url)
        user_agent = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.1.14) Gecko/20080418 Ubuntu/7.10 (gutsy) Firefox/2.0.0.14"
        request.add_header("User-Agent", user_agent)
        pagefile = urllib2.urlopen(request)
        soup = BeautifulSoup.BeautifulSoup(pagefile)
        realurl = pagefile.geturl()
        pagefile.close()
        return (soup, realurl)

    ### Usuwanie zbednych tagow html

    def tagi_html(self,value):
        return re.sub(r'<[^>]*?>', '', value)

    ### Bezposrednie pobieranie danych ze strony wikipedii

    def pobierz_dane(self,rok, miesiac,dzien):
        global wyniki
        if dzien[:-1] == "0":
            dzien = dzien[-1]
        strona = "http://en.wikipedia.org/wiki/Portal:Current_events/"+rok+"_"+miesiac+"_"+dzien
        try:
            (soup, url) = self.przegladarka(strona)
            for ul in soup.findAll("td", { "class" : "description" } ):
                wyniki = self.tagi_html(str(ul)).split("\n")
        except:
            wyniki = ["Brak Danych"]
        return wyniki

    ##### koniec pobierania danych #####

    ### Nawiazywanie polaczenia z baza danych

    def inicjacja_bazy(self):
        global db, metadata
        db = create_engine("mysql://"+dane()[0]+":"+dane()[1]+"@"+dane()[2]+"/"+dane()[3]+"")
        # przypisanie tabel do MetaData
        metadata = MetaData(db)

    ### Jesli w bazie nie ma naszej tabeli, to ta funkcja ja tworzy

    def nowa_baza(self):
        # definicja tabeli
        event = Table("event", metadata,
            Column("id", Integer, primary_key=True),
            Column("date", Date),
            Column("description", String(600))
        )
        # utworzenie tabeli jesli nie istnieje
        event.create(checkfirst=True)

    ### Funkcja dodawania danych do bazy danych

    def dodaj(self, idata, idescription):
        parser = Table("event", metadata, autoload=True)
        i = parser.insert()
        i.execute(date = idata, description = idescription)

    ##### koniec bazy danych #####

    ##### funkcje daty #####

    #### Zmiana miesiacy na potrzebe wikipedii

    def miesiace(self,m):
        if m == "01":
            miesiac = "January"
        elif m == "02":
            miesiac = "February"
        elif m == "03":
            miesiac = "March"
        elif m == "04":
            miesiac = "April"
        elif m == "05":
            miesiac = "May"
        elif m == "06":
            miesiac = "June"
        elif m == "07":
            miesiac = "July"
        elif m == "08":
            miesiac = "August"
        elif m == "09":
            miesiac = "September"
        elif m == "10":
            miesiac = "October"
        elif m == "11":
            miesiac = "November"
        elif m == "12":
            miesiac = "December"
        return miesiac

    ### Funkcja przeliczajaca odcinki czasu, wykonuje funkcje dodawania do bazy

    def czas(self,rok_start,miesiac_start,dzien_start,rok_end,miesiac_end,dzien_end):
        delta = datetime.timedelta(1)
        start = datetime.datetime(rok_start,miesiac_start,dzien_start)
        end = datetime.datetime(rok_end,miesiac_end,dzien_end)
        czas = []
        while start <= end:
            tmp = str(start)[:10].split("-")
            start+=delta
            miesiac = tmp[1]
            t= self.miesiace(tmp[1])
            tmp[1]=t
            if tmp[2][:-1] == "0":
                tmp[2] = tmp[2][-1]
            lista = self.pobierz_dane(tmp[0],tmp[1],tmp[2])
            for i in range(len(lista)):
                if lista[i][-1:] != ":":    # pozbywanie sie zbednych i pustych informacji z wikipedii
                    if lista[i] != "":
                        self.dodaj(datetime.date(int(tmp[0]),int(miesiac),int(tmp[2])),lista[i])

    ##### koniec funkcji daty #####
        

w = wiki_parser()

zmienne_start = zmienne[1].split("-")
zmienne_end = zmienne[2].split("-")

if zmienne_start[2][:-1] == "0":
    zmienne_start[2] = zmienne_start[2][-1]

if zmienne_end[2][:-1] == "0":
    zmienne_end[2] = zmienne_end[2][-1]

rok_start = int(zmienne_start[0])
miesiac_start = int(zmienne_start[1])
dzien_start = int(zmienne_start[2])
rok_end = int(zmienne_end[0])
miesiac_end = int(zmienne_end[1])
dzien_end = int(zmienne_end[2])


w.inicjacja_bazy()
w.nowa_baza()
w.czas(rok_start,miesiac_start,dzien_start,rok_end,miesiac_end,dzien_end)
