# Käyttöohje

Lataa tiedosto [Address_book-1.0-SNAPSHOT.jar](https://github.com/MiraVorne77/ot-harjoitustyo/releases/)

## Konfigurointi

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar Address_book-1.0-SNAPSHOT.jar
```

## Kirjautuminen

Sovellus käynnistyy aloitusnäkymään:

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/startView.jpg" width="400">

Kirjautuminen onnistuu valitsemalla _File - Login_, kirjoittamalla olemassaoleva käyttäjätunnus syötekenttään ja painamalla _login_:

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/loginView.jpg" width="400">

Taulukkoon tulostuu yhteystietoja käyttöoikeuksien mukaisesti. 
* Parent -oikeuksilla aukeaa kaikki omat ja lasten yhteystiedot. Yhteystietoja voi lajitella taulukon kenttien mukaan.   

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/parentView.jpg" width="400">

* Child -oikeuksilla aukeaa kyseisen lapsen omat yhteystiedot.

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/childView.jpg" width="400">

## Uuden käyttäjän luominen

Uuden käyttäjän luomisnäkymään voi siirtyä valitsemalla _File - Add New User_, kirjoittamalla uusi käyttäjätunnus, valitsemalla _Parent_, jos käyttäjä on aikuinen ja painamalla _Add User_ -nappia.

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/newUserView.jpg" width="400">

Käyttäjän luomisen jälkeen, käyttäjä voi kirjautua järjestelmään uudella käyttäjätunnuksella.

## Todojen luominen ja tehdyksi merkkaaminen

Onnistuneen kirjautumisen myötä siirrytään käyttäjien tekemättömät työt listaavaan näkymään

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/k-3.png" width="400">

Näkymä mahdollistaa olemassaolevien todojen merkkaamisen tehdyksi painikkeella _done_ sekä uusien todojen luomisen kirjoittamalla syötekenttään tehtävän kuvauksen ja painamalla _create_. 

Klikkaamalla näkymän oikean ylänurkan painiketta _logout_ käyttäjä kirjautuu ulos sovelluksesta ja sovellus palaa takaisin kirjaantumisnäkymään.