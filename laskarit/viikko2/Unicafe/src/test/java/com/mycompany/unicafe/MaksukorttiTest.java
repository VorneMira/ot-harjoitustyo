package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null); 
    }
    @Test
    public void kortinSaldoAlussaOikein() {
        int saldo = kortti.saldo();
        assertEquals(10, saldo); 
    }
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(10);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void palauttaaTrueJosRahatRiittivät() {
        boolean vastaus = kortti.otaRahaa(9);
        assertEquals(true, vastaus);
    }
    @Test
    public void palauttaaFalseJosRahatEivatRiita() {
        boolean vastaus = kortti.otaRahaa(11);
        assertEquals(false, vastaus);
    }
}
