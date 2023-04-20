package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("test124", "test457", "test street1", "123456790", "test124@mail.ru"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }
}
