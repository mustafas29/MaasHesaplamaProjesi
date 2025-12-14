package com.mustafasengul;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * Bu sınıf, MaasHesaplama sınıfındaki metotları test etmek için yazılmıştır.
 * Her test metodu farklı bir senaryoyu test eder.
 */
public class MaasHesaplamaTest {

    // Testlerde kullanılacak Personel nesnesi
    private Personel personel;

    // Her test öncesi çalışacak kurulum metodu
    @BeforeMethod
    public void testtenOnceHazirlikYap() {
        // Her test için yeni bir Personel nesnesi oluşturuyoruz
        // Başlangıç değeri olarak 20 gün çalışmış bir personel oluşturuyoruz
        personel = new Personel("Ahmet Yılmaz", 20);
    }

    // 1. Test: Normal çalışma günleri için maaş hesaplama testi
    @Test
    public void normalCalismaGunleriIcinMaasHesapla() {
        // Ön Hazırlık (Given)
        personel.setCalismaGunu(20); // 20 gün çalışmış
        int beklenenMaas = 20 * 2000; // 20 gün * 2000 TL = 40,000 TL

        // İşlem (When)
        int hesaplananMaas = MaasHesaplama.hesaplaMaas(personel);

        // Doğrulama (Then)
        // Hesaplanan maaşın beklenen değere eşit olup olmadığını kontrol ediyoruz
        assertEquals(hesaplananMaas, beklenenMaas, "20 gün çalışan personel için maaş yanlış hesaplandı");
    }

    // 2. Test: 21-25 gün arası çalışma için primli maaş hesaplama testi
    @Test
    public void primliCalismaGunleriIcinMaasHesapla() {
        // Ön Hazırlık (Given)
        personel.setCalismaGunu(22); // 22 gün çalışmış
        // 22 gün * 2000 TL + 2 gün * 1000 TL prim
        int beklenenMaas = (22 * 2000) + (2 * 1000);

        // İşlem (When)
        int hesaplananMaas = MaasHesaplama.hesaplaMaas(personel);

        // Doğrulama (Then)
        assertEquals(hesaplananMaas, beklenenMaas, "22 gün çalışan personel için primli maaş yanlış hesaplandı");
    }

    // 3. Test: 26+ gün çalışma için yüksek primli maaş hesaplama testi
    @Test
    public void yuksekPrimliCalismaGunleriIcinMaasHesapla() {
        // Ön Hazırlık (Given)
        personel.setCalismaGunu(26); // 26 gün çalışmış
        // 26 gün * 2000 TL + 1 gün * 3000 TL (26-25=1 gün yüksek prim) + 5 gün * 1000 TL (25-20=5 gün normal prim)
        int beklenenMaas = (26 * 2000) + (1 * 3000) + (5 * 1000);

        // İşlem (When)
        int hesaplananMaas = MaasHesaplama.hesaplaMaas(personel);

        // Doğrulama (Then)
        assertEquals(hesaplananMaas, beklenenMaas, "26 gün çalışan personel için yüksek primli maaş yanlış hesaplandı");
    }

    // 4. Test: Maaş kategorisi belirleme testi
    @Test
    public void maasKategorisiBelirle() {
        // Düşük maaş kategorisi testi (30,000 ve altı)
        String kategori1 = MaasHesaplama.maasKategorisi(25000);
        assertEquals(kategori1, "Düşük Maaş", "25,000 TL için yanlış maaş kategorisi belirlendi");

        // Orta maaş kategorisi testi (30,001 - 50,000)
        String kategori2 = MaasHesaplama.maasKategorisi(40000);
        assertEquals(kategori2, "Orta Maaş", "40,000 TL için yanlış maaş kategorisi belirlendi");

        // Yüksek maaş kategorisi testi (50,001 ve üstü)
        String kategori3 = MaasHesaplama.maasKategorisi(60000);
        assertEquals(kategori3, "Yüksek Maaş", "60,000 TL için yanlış maaş kategorisi belirlendi");
    }
}
