package com.mustafasengul;
import com.mustafasengul.Personel;
import com.mustafasengul.MaasHesaplama;

public class Main {
    public static void main(String[] args) {
        Personel p1 = new Personel("Ahmet", 22);
        Personel p2 = new Personel("Ayşe", 27);
        Personel p3 = new Personel("Mehmet", 19);

        System.out.println(p1.getIsim() + " maaşı: " + MaasHesaplama.hesaplaMaas(p1));
        System.out.println(p2.getIsim() + " maaşı: " + MaasHesaplama.hesaplaMaas(p2));
        System.out.println(p3.getIsim() + " maaşı: " + MaasHesaplama.hesaplaMaas(p3));

        // Üçlü operatör ile maaş kategorisi gösterimi
        System.out.println(p1.getIsim() + " maaş kategorisi: " +
                MaasHesaplama.maasKategorisi(MaasHesaplama.hesaplaMaas(p1)));


    }
}
