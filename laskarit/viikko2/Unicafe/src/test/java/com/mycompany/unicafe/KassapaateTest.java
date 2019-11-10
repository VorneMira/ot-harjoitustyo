/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mira Vorne
 */
public class KassapaateTest {
    
    public KassapaateTest() {
    }
    Kassapaate kassa;
    
    @Before
    public void setUpClass() {
        kassa = new Kassapaate();
    }
    
    @After
    public void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void luodunKassapaatteenRahamaaraJaMyytyjenLounaidenMaaraOnOikea() {
        boolean ok = kassa.kassassaRahaa() == 100000 && kassa.maukkaitaLounaitaMyyty() == 0 && kassa.edullisiaLounaitaMyyty() == 0;
        assertEquals(true, ok);
    }
    @Test
    public void maukasJosMaksuRiittavaKassassaOlevaRahamaaraKasvaaLounaanHinnallaJaVaihtorahanSuuruusOnOikea() {
        int vaihtoraha = kassa.syoMaukkaasti(500);
        boolean ok = vaihtoraha == 100 && kassa.kassassaRahaa() == 100400;
        assertEquals(true, ok);
    }
    @Test
    public void maukasJosMaksuOnRiittavaMyytyjenLounaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(500);
        boolean ok = kassa.maukkaitaLounaitaMyyty() == 1 && kassa.edullisiaLounaitaMyyty() == 0;
        assertEquals(true, ok);  
    }
    @Test
    public void maukasJosMaksuEiOleRiittavaKassassaRahamaaraEiMuutuRahatPalautetaanVaihtorahanaMyytyjenLounaidenMaarassaEiMuutosta(){
        int vaihtoraha = kassa.syoMaukkaasti(300);
        boolean ok = vaihtoraha == 300 && kassa.kassassaRahaa() == 100000 && kassa.maukkaitaLounaitaMyyty() == 0 && kassa.edullisiaLounaitaMyyty() == 0;
        assertEquals(true, ok);
    }
    @Test
    public void edullinenJosMaksuRiittavaKassassaOlevaRahamaaraKasvaaLounaanHinnallaJaVaihtorahanSuuruusOnOikea() {
        int vaihtoraha = kassa.syoEdullisesti(500);
        boolean ok = vaihtoraha == 260 && kassa.kassassaRahaa() == 100240;
        assertEquals(true, ok);
    }
    @Test
    public void edullinenJosMaksuOnRiittavaMyytyjenLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(500);
        boolean ok = kassa.maukkaitaLounaitaMyyty() == 0 && kassa.edullisiaLounaitaMyyty() == 1;
        assertEquals(true, ok); 
    }
    @Test
    public void edullinenJosMaksuEiOleRiittavaKassassaRahamaaraEiMuutuRahatPalautetaanVaihtorahanaMyytyjenLounaidenMaarassaEiMuutosta(){
        int vaihtoraha = kassa.syoEdullisesti(200);
        boolean ok = vaihtoraha == 200 && kassa.kassassaRahaa() == 100000 && kassa.maukkaitaLounaitaMyyty() == 0 && kassa.edullisiaLounaitaMyyty() == 0;
        assertEquals(true, ok);
    }
    @Test
    public void maukasJosKortillaOnTarpeeksiRahaaVeloitetaanSummaKortiltaJaPalautetaanTrue(){
        Maksukortti kortti = new Maksukortti(500);
        boolean maksuOk = kassa.syoMaukkaasti(kortti);
        boolean ok = kortti.saldo() == 100 && maksuOk;
        assertEquals(true, ok);
    }
    @Test
    public void maukasJosKortillaOnTarpeeksiRahaaMyytyjenLounaidenMaaraKasvaa(){
        Maksukortti kortti = new Maksukortti(500);
        boolean maksuOk = kassa.syoMaukkaasti(kortti);
        boolean ok = kassa.maukkaitaLounaitaMyyty() == 1 && maksuOk;
        assertEquals(true, ok);
    }
    @Test
    public void maukasJosKortillaEiTarpeeksiRahaaKortinRahamaaraEiMuutuMyytyjenLounaidenMaaraMuuttumatonJaPalautetaanFalse(){
        Maksukortti kortti = new Maksukortti(300);
        boolean maksuOk = kassa.syoMaukkaasti(kortti);
        boolean ok = !maksuOk && kortti.saldo() == 300 && kassa.maukkaitaLounaitaMyyty() == 0 && kassa.edullisiaLounaitaMyyty() == 0;
        assertEquals(true, ok);
    }
    @Test
    public void maukasKassassaOlevaRahamaaraEiMuutuKortillaOstettaessa(){
        Maksukortti kortti = new Maksukortti(500);
        boolean maksuOk = kassa.syoMaukkaasti(kortti);
        boolean ok = kassa.kassassaRahaa() == 100000 && maksuOk;
        assertEquals(true, ok);
    }
    
    @Test
    public void edullinenJosKortillaOnTarpeeksiRahaaVeloitetaanSummaKortiltaJaPalautetaanTrue(){
        Maksukortti kortti = new Maksukortti(500);
        boolean maksuOk = kassa.syoEdullisesti(kortti);
        boolean ok = kortti.saldo() == 260 && maksuOk;
        assertEquals(true, ok);
    }
    @Test
    public void edullinenJosKortillaOnTarpeeksiRahaaMyytyjenLounaidenMaaraKasvaa(){
        Maksukortti kortti = new Maksukortti(500);
        boolean maksuOk = kassa.syoEdullisesti(kortti);
        boolean ok = kassa.edullisiaLounaitaMyyty()== 1 && maksuOk;
        assertEquals(true, ok);
    }
    @Test
    public void edullinenJosKortillaEiTarpeeksiRahaaKortinRahamaaraEiMuutuMyytyjenLounaidenMaaraMuuttumatonJaPalautetaanFalse(){
        Maksukortti kortti = new Maksukortti(200);
        boolean maksuOk = kassa.syoEdullisesti(kortti);
        boolean ok = !maksuOk && kortti.saldo() == 200 && kassa.maukkaitaLounaitaMyyty() == 0 && kassa.edullisiaLounaitaMyyty() == 0;
        assertEquals(true, ok);
    }
    @Test
    public void edullinenKassassaOlevaRahamaaraEiMuutuKortillaOstettaessa(){
        Maksukortti kortti = new Maksukortti(500);
        boolean maksuOk = kassa.syoEdullisesti(kortti);
        boolean ok = kassa.kassassaRahaa() == 100000 && maksuOk;
        assertEquals(true, ok);
    }
    @Test
    public void kortilleRahaaLadattaessaKortinSaldoMuuttuuJaKassassaOlevaRahamaaraKasvaaLadatullaSummalla(){
        Maksukortti kortti = new Maksukortti(500);
        kassa.lataaRahaaKortille(kortti, 500);
        boolean ok = kassa.kassassaRahaa() == 100500 && kortti.saldo() == 1000;
        assertEquals(true, ok);
    }
    
    @Test
    public void kortilleNegatiivinenLadattaessaKortinSaldoEiMuutuJaKassassaOlevaRahamaaraEiMuutu(){
        Maksukortti kortti = new Maksukortti(500);
        kassa.lataaRahaaKortille(kortti, -1);
        boolean ok = kassa.kassassaRahaa() == 100000 && kortti.saldo() == 500;
        assertEquals(true, ok);
    }
    
}
