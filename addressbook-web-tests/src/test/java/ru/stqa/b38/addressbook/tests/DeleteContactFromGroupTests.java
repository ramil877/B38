package ru.stqa.b38.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.b38.addressbook.model.ContactData;
import ru.stqa.b38.addressbook.model.GroupData;
import ru.stqa.b38.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactFromGroupTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("test123").withLastname("test456").withAddress("test street").withMobile("123456789").withEmail("test123@mail.ru"));
        }
        ContactData contact = app.db().contacts().iterator().next();
        if (contact.getGroups().size() == 0) {
            Groups before = app.db().groups();
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("name1").withHeader("header1").withFooter("footer1"));
            Groups after = app.db().groups();
            List<GroupData> newGroup = new ArrayList<GroupData>(after);
            newGroup.removeAll(before);
            int usedGroup = newGroup.iterator().next().getId();
            app.goTo().homePage();
            app.contact().addingToGroup(contact.getId(), usedGroup);
        }
    }

    @Test//(enabled = false)
    public void testDeleteContactFromGroup() {
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        Groups before = contact.getGroups();
        int usedGroup = contact.getGroups().iterator().next().getId();
        app.contact().deletionFromGroup(usedGroup, contact.getId());
        ContactData contactData = app.db().contacts().iterator().next();
        Groups after = contactData.getGroups();

        assertThat(before.size() - 1, equalTo(after.size()));
    }
}
