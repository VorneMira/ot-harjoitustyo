# Käyttöohje

Lataa tiedosto [Address_book-1.0-SNAPSHOT.jar](https://github.com/MiraVorne77/ot-harjoitustyo/releases/)

## Konfigurointi

Sovellus käyttää tietokantaa, joka toimii tällä hetkellä Azure-palvelimella. 

* Sovelluksen testaamista varten käytetään testitietokantaa, johon on generoitu testidata. 

* Sovellus käyttää tuotantotietokantaa:

CREATE TABLE `users` (
  `id` varchar(50) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `role` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `contacts` (
  `id` varchar(50) NOT NULL,
  `friendName` varchar(80) DEFAULT NULL,
  `friendPhone` varchar(30) DEFAULT NULL,
  `friendAddress` varchar(100) DEFAULT NULL,
  `parentName` varchar(80) DEFAULT NULL,
  `parentPhone` varchar(30) DEFAULT NULL,
  `id_user` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_User_idx` (`id_user`),
  CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) 

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

Uuden käyttäjän luomisnäkymään voi siirtyä valitsemalla _File - Add New User_. Tunnuksen luomisnäkymässä: 
* Käyttäjä voi kirjoittaa uuden käyttäjätunnuksen. 
* Valita _Parent_, jos käyttäjä on aikuinen.
* Lisätä tunnuksen tietokantaan painamalla _Add User_ -nappia.

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/newUserView.jpg" width="400">

Uuden tunnuksen luomisen jälkeen käyttäjä voi kirjautua järjestelmään uudella käyttäjätunnuksella.

## Uuden yhteystiedon luominen ja poistaminen

Uusia yhteystietoja voi kirjoittaa valitsemalla _File - Add New Contact_

Alasvetovalikosta Parent-käyttäjä voi valita käyttäjän, jonka yhteystieto on kyseessä. Lapsi voi tehdä yhteystietoja vain itselleen.

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/addContactView.jpg" width="400">

Yhteystietoja voi poistaa valitsemalla taulukkonäkymässä yhteystiedon ja painamalla _Delete selected contact_ -nappia. 

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/deleteContactView.jpg" width="400">

Voit poistua järjestelmästä valitsemalla _File - Exit_.