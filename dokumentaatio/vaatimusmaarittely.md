# Vaatimusmäärittely

## Sovelluksen tarkoitus

Ajatus sovellukseen syntyi opettajana työskentelevän äidin ongelmasta hallita yhteyksiä kolmen pienen lapsensa vanhempien kanssa. Yhteystietoja tarvitaan mm. leikkitreffien, yökyläilyjen, synttärijuhlien ym. sopimiseen. Tai kun lapsi ei olekaan illalla kotona ja kännykästä on akku loppu, ryhdytään käymään läpi ystävien ja heidän vanhempiensa yhteystietolistaa. 

Sovellukseen tallennetaan kaikki perheelle tärkeät yhteystiedot, kuten lasten ystävien ja heidän vanhempiensa puhelinnumerot ja osoitteet. Jokainen perheenjäsen voi selata omien ystäviensä yhteystietoja, vanhemmat voivat selata myös lastensa ystävien sekä tarvittaessa myös heidän vanhempiensa yhteystietoja. 

## Käyttäjät

Sovelluksella on kaksi käyttäjäroolia, perheen vanhempien käyttäjärooli, eli _pääkäyttäjä_ sekä lasten käyttäjärooli, eli _normaali käyttäjä_.

## Käyttöliittymäluonnos

Sovellus koostuu viidestä eri näkymästä

* Aloitusnäkymä. (TEHTY)
* Kirjautumisnäkymä. (TEHTY)
* Lisää uusi käyttäjä -näkymä. (TEHTY)
* Lisää uusi yhteystieto -näkymä (TEHTY)
* Taulukkonäkymä yhteystiedoista. (TEHTY)

Sovellus aukeaa aloitusnäkymään, josta on mahdollista siirtyä uuden käyttäjän luomisnäkymään tai kirjautumisnäkymään. Onnistunut kirjautuminen ohjaa käyttäjän vanhemman tai lapsen yhteystietojen taulukkonäkymään.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda järjestelmään käyttäjätunnuksen.
  - Käyttäjälle voi antaa vanhempi -oikeudet.

- käyttäjä voi kirjautua järjestelmään.
  - kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle.
  
### Kirjautumisen jälkeen

- Lapsi-käyttäjä näkee omien ystäviensä yhteystiedot.

- Lapsi-käyttäjä voi luoda uuden tai poistaa vanhan yhteystiedon.
  - luotu yhteystieto näkyy ainoastaan sen luoneelle lapsi-käyttäjälle tai vanhempi-käyttäjälle.

- Lapsi-käyttäjä voi kirjautua ulos järjestelmästä.

- Vanhempi-käyttäjä näkee lastensa ystävien ja heidän vanhempiensa yhteystiedot.

- Vanhempi-käyttäjä voi hakea yhteystietoja tietyn lapsensa mukaan.

- Käyttäjä voi luoda uuden tai poistaa vanhan yhteystiedon.
  - luotu yhteystieto näkyy ainoastaan sille liittyvälle lapsi-käyttäjälle tai vanhemmat-käyttäjälle.

- Vanhempi-käyttäjä voi kirjautua ulos järjestelmästä.

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään ajan salliessa esim. seuraavilla toiminnallisuuksilla:

- Yhteystietojen muokkaus.
- Käyttäjien yhteyteen salasana, joka vaaditaan kirjautuessa.
- Tulevat tapahtumat kyseisen ystävän kanssa, esim. yökyläily 10.12.2019 klo 15.
- Sukulaisten ja perhetuttujen yhteystietojen lisääminen järjestelmään.
- Sukulaisten ja perhetuttujen yhteystietojen näkyminen kaikille käyttäjille. 
- Sukulaisten ja lapsen ystävien yhteystietojen erittely käyttöliittymässä, esim. alasvetovalikolla.
