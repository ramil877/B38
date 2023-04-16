package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("test123", "test456", "test street", "123456789", "test123@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    app.getLogout();
  }


}
