
# Ohjelmistotekniikka syksy 2019   

## Harjoitustyö  
[Address book](https://github.com/MiraVorne77/ot-harjoitustyo/tree/master/Address_book)

## Dokumentaatio   
* [Vaatimusmäärittely](https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)
* [Arkkitehtuuri](https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)
* [Työaikakirjanpito](https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)


## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

Generoi hakemistoon target suoritettavan jar-tiedoston Address_book-1.0-SNAPSHOT.jar

### Checkstyle

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html