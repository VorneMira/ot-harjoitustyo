# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

User, Contact ja Dao testataan.

### DAO-luokat

Molempien DAO-luokkien toiminnallisuus on testattu luomalla testeissä testitietokanta. Testitietokannan sisältö tyhjennetään ja luodaan aina ennen testien ajamista. 

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 89% ja haarautumakattavuus 87%

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/test.jpg">

Testaamatta jäävät lähinnä dao exceptionit.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu käyttöohjeen kuvaamalla tavalla.

### Toiminnallisuudet

Kaikki määrittelydokumentin ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi.