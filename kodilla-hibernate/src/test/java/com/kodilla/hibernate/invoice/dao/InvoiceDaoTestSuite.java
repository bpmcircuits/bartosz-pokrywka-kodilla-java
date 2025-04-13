package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoiceDaoTestSuite {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private InvoiceDao invoiceDao;

    @BeforeEach
    public void setUp() {
        productDao.deleteAll();
        itemDao.deleteAll();
        invoiceDao.deleteAll();
    }

    @Test
    void testInvoiceDaoSave() {
        // given
        Product pen = new Product("Pen");
        Product paper = new Product("Paper");
        Product chair = new Product("Chair");

        productDao.save(pen);
        int penId = pen.getId();
        productDao.save(paper);
        int paperId = paper.getId();
        productDao.save(chair);
        int chairId = chair.getId();

        Item item = new Item(pen, new BigDecimal(5), 100);
        Item item2 = new Item(paper, new BigDecimal(10), 40);
        Item item3 = new Item(paper, new BigDecimal(39), 10);

        Invoice invoice = new Invoice("123");
        invoice.getItems().add(item);
        invoice.getItems().add(item2);
        invoice.getItems().add(item3);

        item.setInvoice(invoice);
        item2.setInvoice(invoice);
        item3.setInvoice(invoice);

        // when
        invoiceDao.save(invoice);
        int invoiceId = invoice.getId();

        // then
        assertNotEquals(0, penId);
        assertNotEquals(0, paperId);
        assertNotEquals(0, chairId);
        assertNotEquals(0, invoiceId);
        //clean up
        try {
            itemDao.deleteById(item.getId());
            itemDao.deleteById(item2.getId());
            itemDao.deleteById(item3.getId());
            productDao.deleteById(penId);
            productDao.deleteById(paperId);
            productDao.deleteById(chairId);
            invoiceDao.deleteById(invoiceId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}