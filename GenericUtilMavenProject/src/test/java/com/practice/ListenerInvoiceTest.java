package com.practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(com.practice.ListenerImpClass.class)
public class ListenerInvoiceTest extends ListenerBaseClass {
    
    @Test
    public void createInvoiceTest() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Login");
        System.out.println("step-1");
        System.out.println("step-2");
        System.out.println("step-3");
        System.out.println("step-4");
    }
    
    @Test
    public void createInvoiceWithContactTest() {
        System.out.println("step-1");
        System.out.println("step-2");
        System.out.println("step-3");
        System.out.println("step-4");
        Assert.fail("Intentional failure to check screenshot functionality"); // Temporary for testing
    }
}

