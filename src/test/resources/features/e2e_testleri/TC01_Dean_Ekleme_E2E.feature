@e2eTestleri
Feature: Admin Dean Ekleyebilmeli
  Scenario: Admin Web Sitesinde Dean Ekleyebilmeli
    Given Kullanici login e tiklar
    When Username kutusuna "AdminGurkay" girer
    And Password kutusuna "Gurkay123+" girer
    And Login butonuna tiklar
    And Menu butonuna tiklar
    And Dean Management butonuna tiklar
    And DeanName kutusuna "Ayşe" girer
    And DeanSurName kutusuna "Yılmaz" girer
    And DeanBirthPlace kutusuna "İstanbul" girer
    And DeanGender "Female" secenegini girer
    And DeanDateOfBirth kutusuna "05051975" girer
    And DeanPhone kutusuna "524-456-7894" girer
    And DeanSSN kutusuna SSNno girer
    And DeanUserName kutusuna "ayseyilmaz" girer
    And DeanPassword kutusuna sifre girer
    And DeanSubmit butonuna tiklar
    Then Dean kaydi yapildigi dogrulanir

    Scenario: Eklenen Dean API ile test edilebilir
      Given Kayitli dean verisinin ID numarasi alinir
      And Dean verileri hazirlanir
      When Kayitli dean ID ile cagrilir
      Then Dean bilgileri dogrulanir E2E


