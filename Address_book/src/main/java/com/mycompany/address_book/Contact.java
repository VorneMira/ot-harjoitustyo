/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.address_book;

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
   
    public String toString() {
        return this.friendName;
    }
    
}

