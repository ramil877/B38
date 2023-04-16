package ru.stqa.b38.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {

    initContactCreation();
    fillContactForm(new ContactData("test123", "test456", "test street", "123456789", "test123@mail.ru"));
    submitContactCreation();
    returnToHomePage();
    getLogout();
  }


}
