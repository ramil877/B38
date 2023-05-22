package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    private void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("test123").withLastname("test456").withAddress("test street").withMobile("123456789").withEmail("test123@mail.ru"));
        }
    }

    @Test//(enabled = false)
    public void testContactPhones(){
       app.contact().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }

}
