/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mira.addressbook.logics;

/**
 *
 * @author Mira Vorne
 */
public class Contact {

    private String guid = java.util.UUID.randomUUID().toString();
    //private String childName;
    private String friendName;
    private String friendPhone;
    private String friendAddress;
    private String parentName;
    private String parentPhone;
    private User user;

    /*public Contact(String friendName) {
        this.friendName = friendName;
    }*/

    /**
     * Konstruktori yhteystiedolle (Contact)
     * @param friendName ystävän nimi
     * @param friendPhone ystävän puhelinnumero
     * @param friendAddress ystävän osoite
     * @param parentName vanhemman nimi
     * @param parentPhone vanhemman puhelinnumero
     */
    public Contact(String friendName, String friendPhone, String friendAddress, String parentName, String parentPhone) {
        this.friendName = friendName;
        this.friendPhone = friendPhone;
        this.friendAddress = friendAddress;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
    }
    
    /**
     * Konstruktori yhteystiedolle (Contact). Tällä konstruktorilla saa luotua tietokannasta tuodun yhteystiedon,
     * koska guid on annettavissa
     * @param childName lapsen, jonka ystävä tämä yhteystieto on, nimi
     * @param friendName ystävän nimi
     * @param friendPhone ystävän puhelinnumero
     * @param friendAddress ystävän osoite
     * @param parentName vanhemman nimi
     * @param parentPhone vanhemman puhelinnumero
     * @param guid Guid (tietokannan tunniste)
     */
    public Contact(/*String childName, */String friendName, String friendPhone, String friendAddress, String parentName, String parentPhone, String guid) {
        //this.childName = childName;
        this.friendName = friendName;
        this.friendPhone = friendPhone;
        this.friendAddress = friendAddress;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.guid = guid;
    }
    
    public String getGuid() {
        return this.guid;
    }
    
    public String getChildName() {
        if (this.user != null) {
            return this.user.getName();
        } else {
            return "";
        }
    }
    
    public String getFriendName() {
        return this.friendName;
    }

    public String getFriendPhone() {
        return this.friendPhone;
    }

    public String getFriendAddress() {
        return this.friendAddress;
    }

    public String getParentName() {
        return this.parentName;
    }

    public String getParentPhone() {
        return this.parentPhone;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return String esitys yhteystiedosta.
     */
    public String toString() {
        return this.friendName;
    }

}
