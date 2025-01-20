package org.vti.vtibackend.DAL.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.vti.vtibackend.DAL.Entity.Invoice;

import java.util.List;


public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Query("SELECT MAX(i.invoice_number) FROM Invoice i")
    int findHighestInvoiceNumber();

    @Query("SELECT COUNT(i) FROM Invoice i WHERE i.status = 'Open'")
    int countOpenInvoices();

    @Query("SELECT QUARTER(i.invoice_date) as quarter, SUM(i.total_amount) as total_amount, COUNT(i.invoice_id) as invoice_count FROM Invoice i WHERE YEAR(i.invoice_date) = :year AND i.status = 'Betaald' GROUP BY QUARTER(i.invoice_date)")
    List<Object[]> findInvoicesByYear(@Param("year") int year);


    @Query("SELECT i.customer_id, i.invoice_date, i.due_date, i.total_amount, i.total_btw, i.status, i.invoice_number, i.deleted, c.name, c.company, c.address, c.email,c.phone,c.customer_number FROM Invoice i inner join Customer c on i.customer_id = c.customer_id")
    List<Object[]> findInvoices();

    @Query("SELECT i.invoice_id, i.customer_id, i.car_id, i.invoice_date, i.due_date, i.total_amount, i.total_btw, i.status, i.invoice_number, i.deleted FROM Invoice i WHERE i.customer_id = :customerId")
    List<Object[]> findInvoicesByCustomerId(@Param("customerId") int customerId);

}
