/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Duuni
 */
public class KassapaateTest {
    
    Kassapaate kassapaate = new Kassapaate();
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }       

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void aluksiKassassaRahaa1000Euroa() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void aluksiEdullisiaLounaitaEiMyyty() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void aluksiMaukkaitaLounaitaEiMyyty() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void riittavallaMaksullaRahamaaraKasvaaEdullisenLounaanVerran() {
        kassapaate.syoEdullisesti(300);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void riittavallaMaksullaRahamaaraKasvaaMaukkaanLounaanVerran() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanVaihtorahanSuuruusOnOikea() {
        int vaihtoraha = kassapaate.syoEdullisesti(300);
        assertEquals(60, vaihtoraha);
    }
    
    @Test
    public void maukkaanLounaanVaihtorahanSuuruusOnOikea() {
        int vaihtoraha = kassapaate.syoMaukkaasti(500);
        assertEquals(100, vaihtoraha);
    }
    
    @Test
    public void riittavallaMaksullaEdullistenMaaraKasvaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void riittavallaMaksullaMaukkaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void riittamattomallaMaksullaEdullisestaKassanRahamaaraEiMuutu() {
        kassapaate.syoEdullisesti(10);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void riittamattomallaMaksullaMaukkaastaKassanRahamaaraEiMuutu() {
        kassapaate.syoMaukkaasti(10);        
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void riittamattomallaMaksullaEdullisestaKaikkiRahatSaadaanTakaisin() {
        int vaihtorahat = kassapaate.syoEdullisesti(230);
        assertEquals(230, vaihtorahat);
    }
    
    @Test
    public void riittamattomallaMaksullaMaukkaastaKaikkiRahatSaadaanTakaisin() {
        int vaihtorahat = kassapaate.syoMaukkaasti(350);
        assertEquals(350, vaihtorahat);
    }
    
    @Test
    public void riittamattomallaMaksullaEdullistenMaaraEiMuutu() {
        kassapaate.syoEdullisesti(230);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void riittamattomallaMaksullaMaukkaidenMaaraEiMuutu() {
        kassapaate.syoMaukkaasti(230);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    // Korttiostot
    
    @Test
    public void josKortillaTarpeeksiRahaaEdullisenSummaVeloitetaanKortilta() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaMaukkaanSummaVeloitetaanKortilta() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaEdulliseenPalautetaanTrue() {
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void josKortillaTarpeeksiRahaaMaukkaaseenPalautetaanTrue() {
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void josKortillaRahaaEdullistenMaaraKasvaa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaRahaaMaukkaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahamaaraEiMuutuEdullisestaOstosta() {
        kortti.otaRahaa(800);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahamaaraEiMuutuMaukkaastaOstosta() {
        kortti.otaRahaa(800);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaEdullistenMaaraEiMuutu() {
        kortti.otaRahaa(800);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaMaukkaidenMaaraEiMuutu() {
        kortti.otaRahaa(800);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaEdulliseenPalautetaanFalse() {
        kortti.otaRahaa(800);
        assertFalse(kassapaate.syoEdullisesti(kortti));        
    }
    
    @Test
    public void josKortillaEiTarpeeksiRahaaMaukkaaseenPalautetaanFalse() {
        kortti.otaRahaa(800);
        assertFalse(kassapaate.syoMaukkaasti(kortti));        
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaMaksettaessaEdullinen() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaMaksettaessaMaukas() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void ladattaessaRahaaKortilleKortinSaldoKasvaaLadatullaSummalla() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
    }
    
    @Test
    public void ladattaessaRahaaKortilleKassanRahamaaraKasvaaLadatullaSummalla() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void ladattaessaNegatiivinenSummaKortilleKortinSaldoEiMuutu() {
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void ladattaessaNegatiivinenSummaKortilleKassanRahamaaraEiMuutu() {
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }    
}
