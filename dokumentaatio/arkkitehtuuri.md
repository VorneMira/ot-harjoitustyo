# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuuria:

* Sovelluksen käyttöliittymä
* Sovelluksen toiminta
* Sovelluksen pysyväistallennus

Pakkaus _addressbook.gui_ sisältää JavaFX:llä toteutetun käyttöliittymän _addressbook.logics_ sovelluslogiikan ja _addressbook.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

Käyttöliittymä sisältää viisi erillistä näkymää
- Aloitusnäkymä
- Kirjautuminen
- Uuden käyttäjän luominen
- Uuden yhteystiedon luominen
- Taulukkonäkymä yhteystiedoista

jokainen näistä on toteutettu omana Scene-oliona. Taulukkonäkymä pysyy taustalla, apuikkunat (kirjautuminen, käyttäjän lisääminen, yhteystiedon lisääminen) avautuvat modaalisena. Käyttöliittymä on rakennettu ohjelmallisesti luokassa mira.addressbook.gui.AddressBookGUI.

Kun sovelluksen yhteystietotaulukon tilanne muuttuu, eli uusi käyttäjä kirjautuu tai yhteystietoja lisätään, kutsutaan sovelluksen metodia showContacts joka renderöi yhteystietotaulukon uudelleen sovelluslogiikalta saamansa näytettävien yhteystietolistan perusteella riippuen käyttöoikeustasosta.

## Luokkakaavio

### Käyttäjän kontaktit

Sovelluksen loogisen datamallin muodostavat luokat User ja Contact, jotka kuvaavat käyttäjiä ja käyttäjiin liitettyjä yhteystietoja.

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/luokkakaavio.jpg">

## Tietojen pysyväistallennus

Pakkauksen _mira.addressbook.dao_ luokat _SqlContactDao_ ja _SqlUserDao_ huolehtivat tietojen tallettamisesta tietokantaan.

Luokat noudattavat Data Access Object-suunnittelumallia ja ne on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos sovelluksen datan talletustapaa päätetään vaihtaa. Luokat onkin eristetty rajapintojen _ContactDao_ ja _UserDao_ taakse ja sovelluslogiikka ei käytä luokkia suoraan.

Sovelluslogiikan testauksessa hyödynnetäänkin tätä siten, että testeissä käytetään erillistä testitietokantaa.

## Sekvenssikaavio

Kuvataan seuraavaksi sovelluksen toimintalogiikka päätoiminnallisuuden osalta sekvenssikaaviona.

### Uuden yhteystiedon luominen

Uuden yhteystiedon luovan _button2_-painikkeen klikkaamisen jälkeen sovelluksen kontrolli etenee seuraavasti:

<img src="https://github.com/MiraVorne77/ot-harjoitustyo/blob/master/dokumentaatio/sekvenssi_lisaa_ystava.jpg">

Tapahtumakäsittelijä kutsuu contactDao.addContact antaen parametriksi luotavan yhteystiedon tiedot. 

#### Muut toiminnallisuudet

Sama periaate toistuu käyttäjän lisäämisessä.

## Ohjelman rakenteeseen jääneet heikkoudet

### käyttöliittymä

Graafinen käyttöliittymä on toteutettu määrittelemällä lähes koko käyttöliittymän struktuuri luokan _AddressBookGUI_ metodissa _start_. Ainakin kaikkien sovelluksen päänäkymät rakentava koodi olisi syytä erottaa omiksi metodeikseen tai kenties luokiksi. Muuttujien nimeämistä voisi parantaa. 

Käyttöliittymän rakenteen ohjelmallinen määrittely kannattaisi kenties korvata FXML-määrittelyllä, tällöin sovelluslogiikan ja käyttöliittymän tapahtumankäsittelijöiden välinen kommunikointi ei hukkuisi GUI-elementtejä rakentavan koodin sekaan.

### DAO-luokat

Tietokantayhteys on kovakoodattu. Jatkokehitysideana ne voisi lukea parametreina tiedostosta, jolloin koko ohjelmaa ei tarvitsisi kääntää aina, kun tietokanta vaihtuu.