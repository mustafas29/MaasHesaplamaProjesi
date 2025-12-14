package com.mustafasengul;


import javax.validation.constraints.NotNull;

public class MaasHesaplama {

    public static String maasKategorisi(int maas) {
        return (maas > 50000) ? "Yüksek Maaş" : (maas > 30000 ? "Orta Maaş" : "Düşük Maaş");
    }

    public static int hesaplaMaas(Personel p) {
        int gunlukUcret = 2000;
        int maas = p.getCalismaGunu() * gunlukUcret;

        // Prim hesaplama
        if (p.getCalismaGunu() > 25) {
            int fazlaGun = p.getCalismaGunu() - 25;
            maas += (5 * 1000) + (fazlaGun * 3000); // İlk 5 gün 1000 TL, sonrası 3000 TL
        } else if (p.getCalismaGunu() > 20) {
            int fazlaGun = p.getCalismaGunu() - 20;
            maas += fazlaGun * 1000; // 20-25 gün arası her gün için 1000 TL
        }

        return maas;
    }

}
