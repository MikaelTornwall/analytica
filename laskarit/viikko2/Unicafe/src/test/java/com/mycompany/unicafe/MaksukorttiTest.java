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
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        assertEquals(110, kortti.saldo());
    }

    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }

    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(20);
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void palautetaanTrueJosRahatRiittavat() {
        assertTrue(kortti.otaRahaa(5));
    }

    @Test
    public void palautetaanFalseJosRahatEivatRiita() {
        assertFalse(kortti.otaRahaa(20));
    }
}
