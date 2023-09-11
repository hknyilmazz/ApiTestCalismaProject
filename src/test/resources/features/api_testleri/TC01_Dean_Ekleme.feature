@apitesti
  Feature: Dean Ekleme Testi
    Scenario: Dean basarili bir sekilde eklenebilmeli
      Given Dean eklemek icin Post request hazirligi yapilir
      And Gonderilecek dean bilgileri hazirlanir
      When Dean eklemek icin Post request gonderilir
      Then Dean bilgileri dogrulanir