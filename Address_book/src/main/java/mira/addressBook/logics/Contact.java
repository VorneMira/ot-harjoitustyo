/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mira.addressBook.logics;

/**
 *
 * @author Mira Vorne
 */
public class Contact {

    private String friendName;
    private String friendPhone;
    private String friendAddress;
    private String parentName;
    private String parentPhone;

    public Contact(String friendName) {
        this.friendName = friendName;
    }

    public Contact(String friendName, String friendPhone, String friendAddress, String parentName, String parentPhone) {
        this.friendName = friendName;
        this.friendPhone = friendPhone;
        this.friendAddress = friendAddress;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
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

    public String toString() {
        return this.friendName;
    }

}
