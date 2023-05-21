package ru.stqa.b38.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;

import java.time.Duration;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData("test123", "test456", "test street", "123456789", "test123@mail.ru"));
        }
    }

    @Test(enabled = false)
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().selectContact(index);
        app.contact().deleteContact();
        app.contact().waitAlertAccept(Duration.ofSeconds(2));
        app.alertAccept();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
