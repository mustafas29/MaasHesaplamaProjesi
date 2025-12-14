package com.mustafasengul;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * Bu sınıf, DataProvider kullanarak farklı senaryoları test etmek için yazılmıştır.
 * Aynı test metodunu farklı verilerle tekrar tekrar çalıştırmamızı sağlar.
 */
public class MaasHesaplamaDataProviderTest {

    // Testlerde kullanılacak MaasHesaplama nesnesi
    private MaasHesaplama maasHesaplama;

    // Her test öncesi çalışacak kurulum metodu
    @BeforeMethod
    public void testtenOnceHazirlikYap() {
        maasHesaplama = new MaasHesaplama();
    }

    // 1. DataProvider: Farklı çalışma günleri için beklenen maaş değerlerini içerir
    @DataProvider(name = "maasHesaplamaVerileri")
    public Object[][] maasHesaplamaVerileri() {
        return new Object[][] {
                // {çalışılan gün sayısı, beklenen maaş}
                { 15, 15 * 2000 },           // 15 gün çalışma - normal maaş
                { 20, 20 * 2000 },           // 20 gün çalışma - normal maaş (üst sınır)
                { 21, (21 * 2000) + 1000 },  // 21 gün çalışma - 1 gün primli
                { 22, (22 * 2000) + 2000 },  // 22 gün çalışma - 2 gün primli
                { 25, (25 * 2000) + 5000 },  // 25 gün çalışma - 5 gün primli
                { 26, (26 * 2000) + 8000 },  // 26 gün çalışma - 1 gün yüksek prim + 5 gün normal prim
                { 30, (30 * 2000) + 20000 }  // 30 gün çalışma - 5 gün yüksek prim + 5 gün normal prim
        };
    }

    // 1. Test: DataProvider ile farklı çalışma günleri için maaş hesaplama testi
    @Test(dataProvider = "maasHesaplamaVerileri")
    public void farkliCalismaGunleriIcinMaasHesapla(int calismaGunu, int beklenenMaas) {
        // Ön Hazırlık (Given)
        Personel personel = new Personel("Test Personeli", calismaGunu);

        // İşlem (When)
        int hesaplananMaas = MaasHesaplama.hesaplaMaas(personel);

        // Doğrulama (Then)
        String hataMesaji = calismaGunu + " gün çalışan personel için maaş yanlış hesaplandı";
        assertEquals(hesaplananMaas, beklenenMaas, hataMesaji);
    }

    // 2. DataProvider: Farklı maaş değerleri için beklenen kategorileri içerir
    @DataProvider(name = "maasKategorisiVerileri")
    public Object[][] maasKategorisiVerileri() {
        return new Object[][] {
                // {maaş miktarı, beklenen kategori}
                { 15000, "Düşük Maaş" },    // 30,000 ve altı
                { 25000, "Düşük Maaş" },    // 30,000 ve altı
                { 30000, "Düşük Maaş" },    // Sınır değeri
                { 30001, "Orta Maaş" },     // Sınır değerinin bir üstü
                { 40000, "Orta Maaş" },     // 30,001 - 50,000 arası
                { 50000, "Orta Maaş" },     // Sınır değeri
                { 50001, "Yüksek Maaş" },   // Sınır değerinin bir üstü
                { 60000, "Yüksek Maaş" }    // 50,001 ve üstü
        };
    }

    // 2. Test: DataProvider ile farklı maaş değerleri için kategori belirleme testi
    @Test(dataProvider = "maasKategorisiVerileri")
    public void farkliMaaslarIcinKategoriBelirle(int maas, String beklenenKategori) {
        // İşlem (When)
        String kategori = MaasHesaplama.maasKategorisi(maas);

        // Doğrulama (Then)
        String hataMesaji = maas + " TL için yanlış maaş kategorisi belirlendi";
        assertEquals(kategori, beklenenKategori, hataMesaji);
    }
}
