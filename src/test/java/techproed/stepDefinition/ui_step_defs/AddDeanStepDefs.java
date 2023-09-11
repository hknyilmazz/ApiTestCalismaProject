package techproed.stepDefinition.ui_step_defs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import techproed.pages.AddDeanPage;
import techproed.utilities.ReusableMethods;

import java.util.List;

import static io.restassured.RestAssured.given;
import static techproed.base_urls.BaseUrl.spec;

public class AddDeanStepDefs {
    AddDeanPage addDean = new AddDeanPage();
    Faker faker = new Faker();
    String name;
    int userId;

    @Given("Kullanici login e tiklar")
    public void kullanici_login_e_tiklar() {
        addDean = new AddDeanPage();
        if (addDean.loginLink.isDisplayed()) { // Ramazan değiştirdi: Features seviyesinde çalıştırmak için
            addDean.loginLink.click();
        } else {
            addDean.menuButton.click();
        }
    }

    @When("Username kutusuna {string} girer")
    public void username_i_girer(String string) {
        addDean.usernameBox.sendKeys(string);
    }

    @When("Password kutusuna {string} girer")
    public void password_i_girer(String string) {
        addDean.passwordBox.sendKeys(string);
    }

    @When("Login butonuna tiklar")
    public void login_butonuna_tiklar() {
        ReusableMethods.clickElementByJS(addDean.loginButton);
    }

    @When("Menu butonuna tiklar")
    public void menu_butonuna_tiklar() {
        addDean.menuButton.click();
    }

    @And("Teacher Management butonuna tiklar")
    public void teacherManagementButonunaTiklar() {
        addDean.teacherManagementButton.click();
        ReusableMethods.waitFor(1);
    }

    @And("Guest User linkine tiklanir")
    public void guestUserLinkineTiklanir() {
        addDean.guestUserButton.click();
    }

    @When("Meet Management butonuna tiklar")
    public void meet_management_butonuna_tiklar() {
        addDean.meetManagementButton.click();

    }
    @And("Dean Management butonuna tiklar")
    public void deanManagementButonunaTiklar() {
        addDean.deanManagementButton.click();
    }


    @But("Menu Buttonu altinda Student Management Bolumunu Goremez")
    public void menuButtonuAltindaStudentManagementBolumunuGoremez() {
        Assert.assertFalse(addDean.mainMenueBodyForStudent.getText().contains("Student Management"));
    }

    @When("DeanName kutusuna {string} girer")
    public void dean_name_kutusuna_girer(String str) {
        name = faker.name().firstName();
        if (str.equals("bir isim")) {
            addDean.name.sendKeys(name);
        } else {
            addDean.name.sendKeys(str);
        }
    }
    @When("DeanSurName kutusuna {string} girer")
    public void dean_sur_name_kutusuna_girer(String str) {
        String surname = faker.name().lastName();
        if (str.equals("bir soyisim")) {
            addDean.surname.sendKeys(surname);
        } else {
            addDean.surname.sendKeys(str);
        }
    }
    @When("DeanBirthPlace kutusuna {string} girer")
    public void dean_birth_place_kutusuna_girer(String str) {
        String birthPlace = faker.lorem().word();
        if (str.equals("bir yer ismi")) {
            addDean.birthPlace.sendKeys(birthPlace);
        } else  {
            addDean.birthPlace.sendKeys(str);
            ReusableMethods.waitFor(2);
        }

    }
    @When("DeanGender {string} secenegini girer")
    public void dean_gender_secenegini_girer(String str) {
        String gender = "";
        if (str.equalsIgnoreCase("FEMALE")) {
            addDean.genderFemale.click();
            gender = "FEMALE";
        } else if (str.equalsIgnoreCase("MALE")) {
            addDean.genderMale.click();
            gender = "MALE";
        } else {
            System.out.println("Hatalı veri girdiniz.");
        }
        ReusableMethods.waitFor(2);
    }
    @When("DeanDateOfBirth kutusuna {string} girer")
    public void dean_date_of_birth_kutusuna_girer(String tarih) {
        addDean.birthDay.sendKeys(tarih);
        ReusableMethods.waitFor(2);
    }
    @When("DeanPhone kutusuna {string} girer")
    public void dean_phone_kutusuna_girer(String str) {
        if (str.equals("bir telefon numarasi")) {
            String ListPhoneNo = faker.regexify("[0-9]{3}-[0-9]{3}-[0-9]{4}");
            addDean.phoneNumber.sendKeys(ListPhoneNo);
        } else {
            addDean.phoneNumber.sendKeys(str);
        }
    }
    public static String ListSSNNo;
    @When("DeanSSN kutusuna SSNno girer")
    public void dean_ssn_kutusuna_ss_nno_girer() {
        ListSSNNo = "523-65-6878";
        addDean.ssn.sendKeys(ListSSNNo);
        ReusableMethods.waitFor(2);
    }

    @When("DeanUserName kutusuna {string} girer")
    public void dean_user_name_kutusuna_girer(String str) {
        String username = faker.random().nextInt(1000,9999)+ "ali";
        if (str.equals("bir kullanici adi")) {
            addDean.username.sendKeys(username);
        } else {
            addDean.username.sendKeys(str);
        }
    }

    @When("DeanPassword kutusuna sifre girer")
    public void dean_password_kutusuna_sifre_girer() {
        addDean.deanPassword.sendKeys("12345678Aa");
    }

    @When("DeanSubmit butonuna tiklar")
    public void dean_submit_butonuna_tiklar() {
        addDean.deanSubmit.click();
        ReusableMethods.waitFor(2);
    }

    @Then("Dean kaydi yapildigi dogrulanir")
    public void dean_kaydi_yapildigi_dogrulanir() {
        Assert.assertTrue(addDean.deanSavedPopUp.isDisplayed());
    }

    @When("Dean Name gorundugunu dogrula")
    public void dean_name_gorundugunu_dogrula() {
        Assert.assertTrue(addDean.deanListName.isDisplayed());
    }

    @When("Dean Gender gorundugunu dogrula")
    public void dean_gender_gorundugunu_dogrula() {
        Assert.assertTrue(addDean.deanListGender.isDisplayed());
    }

    @When("Dean Phone gorundugunu dogrula")
    public void dean_phone_gorundugunu_dogrula() {
        Assert.assertTrue(addDean.deanListPhone.isDisplayed());
    }

    @When("Dean SSN gorundugunu dogrula")
    public void dean_ssn_gorundugunu_dogrula() {
        Assert.assertTrue(addDean.deanListSSN.isDisplayed());
    }

    @Then("Dean UserName gorundugunu dogrula")
    public void dean_user_name_gorundugunu_dogrula() {
        Assert.assertTrue(addDean.deanListUserName.isDisplayed());
    }

    @Then("DeanName kutusu required uyarisi gorundugu dogrulanir")
    public void dean_name_kutusu_required_uyarisi_gorundugu_dogrulanir() {
        Assert.assertFalse(addDean.deanSavedPopUp.isDisplayed());
    }

    @Then("Dean saved uyarisi gorunmedigi dogrulanir")
    public void dean_name_kutusu_required_uyarisi_gorundugu_dogrulanir2() {
        Assert.assertFalse(addDean.deanSavedPopUp.isDisplayed());
    }

    @Then("Dean kaydi yapilmadigi dogrulanir")
    public void dean_kaydi_yapilmadigi_dogrulanir() {
        Assert.assertFalse(addDean.deanSavedPopUp.isDisplayed());
    }

    @Then("DeanName kutusunun altinda Required yazisinin gorunur oldugu dogrulanmalidir")
    public void dean_name_kutusunun_altinda_required_yazisinin_gorunur_oldugu_dogrulanmalidir() {
        Assert.assertTrue(addDean.deanNameRequired.isDisplayed());
    }

    @Then("DeanName kutusu enaziki karakter uyarisi gorunmeli ve dean kaydi yapilmadigi gorunmeli")
    public void dean_name_kutusu_enaziki_karakter_uyarisi_gorunmeli_ve_dean_kaydi_yapilmadigi_gorunmeli() {
        Assert.assertTrue(addDean.deanNameEnAzIkiKarakterIcermeliPopUp.isDisplayed());
    }

    @Then("DeanName alani karakter icermeli uyarisi gorunmeli")
    public void dean_name_alani_karakter_icermeli_uyarisi_gorunmeli() {
        Assert.assertTrue(addDean.nameKarakterIcermeliPopUp.isDisplayed());
    }

    @Then("DeanSurName kutusu required uyarisi gorundugu dogrulanir")
    public void dean_surname_kutusu_required_uyarisi_gorundugu_dogrulanir() {
        Assert.assertTrue(addDean.deanSurNameRequired.isDisplayed());
    }

    @Then("DeanSurName alani karakter icermeli uyarisi gorunmeli")
    public void dean_sur_name_alani_karakter_icermeli_uyarisi_gorunmeli() {
        Assert.assertTrue(addDean.surNameKarakterIcermeliPopUp.isDisplayed());
    }

    @Then("DeanSurname kutusu enaziki karakter uyarisi gorunmeli ve dean kaydi yapilmadigi gorunmeli")
    public void dean_surname_kutusu_enaziki_karakter_uyarisi_gorunmeli_ve_dean_kaydi_yapilmadigi_gorunmeli() {
        Assert.assertTrue(addDean.surNameEnAzIkiKarakterIcermeliPopUp.isDisplayed());
    }

    @Then("DeanBirthPlace kutusu required uyarisi gorundugu dogrulanir")
    public void dean_birth_place_kutusu_required_uyarisi_gorundugu_dogrulanir() {
        Assert.assertTrue(addDean.deanBirthPlaceBoxRequired.isDisplayed());
    }

    @Then("DeanBirthPlace karakter icermeli uyarisi gorunmeli ve dean kaydi yapilmamalidir")
    public void dean_birth_place_karakter_icermeli_uyarisi_gorunmeli_ve_dean_kaydi_yapilmamalidir() {
        Assert.assertTrue(addDean.birthPlaceKarakterIcermeliPopUp.isDisplayed());
    }

    @Then("DeanBirthPlace enaz iki karakter icermeli uyarisi gorunmeli ve dean kaydi yapilmamalidir")
    public void dean_birth_place_enaz_iki_karakter_icermeli_uyarisi_gorunmeli_ve_dean_kaydi_yapilmamalidir() {
        Assert.assertTrue(addDean.birthPlaceEnAzIkiKarakterIcermeliPopUp.isDisplayed());
    }

    @Then("GenderOptions required uyarisi gorundugu dogrulanir")
    public void gender_options_required_uyarisi_gorundugu_dogrulanir() {
        Assert.assertTrue(addDean.deanGenderBoxRequired.isDisplayed());
    }

    @Then("DeanDateOfBirth kutusu required uyarisi gorundugu dogrulanir")
    public void dean_date_of_birth_kutusu_required_uyarisi_gorundugu_dogrulanir() {
        Assert.assertTrue(addDean.deanDateOfBirthBoxRequired.isDisplayed());
    }

    @Then("DeanPhone kutusu required uyarisi gorundugu dogrulanir")
    public void dean_phone_kutusu_required_uyarisi_gorundugu_dogrulanir() {
        ReusableMethods.waitForVisibility(addDean.deanPhoneNumberBoxRequired, 3);
        Assert.assertTrue(addDean.deanPhoneNumberBoxRequired.isDisplayed());
    }

    @When("DeanPhone kutusuna invalid {string} girer")
    public void dean_phone_kutusuna_invalid_girer(String str) {
        if (str.equals("bir telefon numarasi")) {
            addDean.phoneNumber.sendKeys(faker.regexify("[0-9]{4}-[0-9]{4}-[0-9]{2}"));
        } else {
            addDean.phoneNumber.sendKeys(str);
        }
    }

    @When("DeanSSN kutusuna invalid SSNno girer")
    public void dean_ssn_kutusuna_invalid_ss_nno_girer() {
        addDean.ssn.sendKeys(faker.regexify("[0-9]{15}"));
        ReusableMethods.waitFor(2);
        faker.internet().password();
    }

    @Then("DeanSSN kutusu required uyarisi gorundugu dogrulanir")
    public void dean_ssn_kutusu_required_uyarisi_gorundugu_dogrulanir() {
        ReusableMethods.waitForVisibility(addDean.deanSSNBoxRequired, 3);
        Assert.assertTrue(addDean.deanSSNBoxRequired.isDisplayed());
    }

    @Then("DeanUserName kutusu required uyarisi gorundugu dogrulanir")
    public void dean_user_name_kutusu_required_uyarisi_gorundugu_dogrulanir() {
        Assert.assertTrue(addDean.deanUserNameBoxRequired.isDisplayed());
    }

    @When("DeanPassword kutusuna invalid sifre girer")
    public void dean_password_kutusuna_invalid_sifre_girer() {
        addDean.deanPassword.sendKeys(faker.internet().password(5, 7));
    }

    @Then("DeanPassword kutusu required uyarisi gorundugu dogrulanir")
    public void dean_password_kutusu_required_uyarisi_gorundugu_dogrulanir() {
        Assert.assertTrue(addDean.deanPasswordBoxRequired.isDisplayed());
    }

    @When("Dean list gorundugu dogrulanir")
    public void dean_list_gorundugu_dogrulanir() {
        Assert.assertTrue(addDean.deanListisDisplayed.isDisplayed());
    }

    @When("Dean delete butonu gorunmedigi dogrulanir")
    public void dean_delete_butonu_gorunmedigi_dogrulanir() {
        ReusableMethods.checkTextContains(addDean.deanListEdit, "delete");
    }

    @When("Dean list son sayfaya gider")
    public void dean_list_son_sayfaya_gider() {
        ReusableMethods.waitFor(1);
        ReusableMethods.clickElementByJS(addDean.deanListSonSayfa);
        ReusableMethods.waitFor(1);
    }

    @When("Dean list son satırda eklenen deani dogrular")
    public void dean_list_son_satirda_eklenen_deani_dogrular() {
        Assert.assertTrue(addDean.tabloNameEnSonHucre.getText().contains(name));
        //Assert.assertTrue(addDean.tabloNameEnSonHucre.getText().contains(name));
    }

    @When("Dean list edit butonuna tıklanir")
    public void dean_list_edit_butonuna_tiklanir() {
        addDean.tabloEnSonHucreedit.click();
    }

    @When("DeanList edit butonu tiklar")
    public void dean_list_edit_butonu_tiklar() {
        ReusableMethods.clickElementByJS(addDean.deanListEdit);
        ReusableMethods.waitFor(2);
    }

    @When("DeanUpdate kutusunu gunceller")
    public void deanupdate_kutusunu_gunceller() {
        ReusableMethods.clickElementByJS(addDean.editDeanName);
        ReusableMethods.cleanByJs(addDean.editDeanName);
        addDean.editDeanName.sendKeys("UpdateName");
        addDean.editDeanSurName.click();
        ReusableMethods.cleanByJs(addDean.editDeanSurName);
        addDean.editDeanSurName.sendKeys("UpdateSurName");
        addDean.editDeanBirthPlace.click();
        ReusableMethods.cleanByJs(addDean.editDeanBirthPlace);
        addDean.editDeanBirthPlace.sendKeys("UpdateBirthPlace");
        addDean.editDeanGender.click();
        addDean.editDeanBirthDay.click();
        ReusableMethods.cleanByJs(addDean.editDeanBirthDay);
        addDean.editDeanBirthDay.sendKeys("01.01.2000");
        addDean.editDeanPhone.click();
        ReusableMethods.cleanByJs(addDean.editDeanPhone);
        addDean.editDeanPhone.sendKeys("905-589-4875");
        addDean.editDeanUserName.click();
        ReusableMethods.cleanByJs(addDean.editDeanUserName);
        addDean.editDeanUserName.sendKeys("paulapaula");
        ReusableMethods.clickElementByJS(addDean.editDeanPassword);
        addDean.editDeanPassword.sendKeys("987456321");

    }

    @Then("EditDean butonuna tiklar")
    public void edit_dean_butonuna_tiklar() {
        ReusableMethods.clickElementByJS(addDean.deanListEditButton);
    }

    @Then("EditDeanSubmit butonuna tiklar")
    public void editdeansubmitButonunaTiklar() {
        ReusableMethods.clickElementByJS(addDean.editDeanSubmit);
    }

    @Then("DeanUpdateSuccessfully uyarisi gorundugu dogrlanir")
    public void deanupdatesuccessfullyUyarisiGorunduguDogrlanir() {
        Assert.assertTrue(addDean.deanUpdatedUyari.isDisplayed());
    }

    @Given("Kayitli dean verisinin ID numarasi alinir")
    public void kayitliDeanVerisininIDNumarasiAlinir() {
        spec.pathParams("first", "dean", "second", "getAll");
        Response response = given(spec).when().get("{first}/{second}");

        JsonPath jsonPath = response.jsonPath();
        List<Integer> userIdList = jsonPath.getList("findAll{it.ssn=='"+ListSSNNo+"'}.userId");
        userId = userIdList.get(0);
    }

    @And("Dean verileri hazirlanir")
    public void deanVerileriHazirlanir() {
        spec.pathParams("first", "dean", "second", "getManagerById", "third", userId);

    }

    @When("Kayitli dean ID ile cagrilir")
    public void kayitliDeanIDIleCagrilir() {
        given(spec).when().get();
    }

    @Then("Dean bilgileri dogrulanir E{int}E")
    public void deanBilgileriDogrulanirEE(int arg0) {
    }
}
