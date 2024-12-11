package org.vti.vtibackend.BLL;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.vti.vtibackend.DAL.Mapper.InvoiceMapper;
import org.vti.vtibackend.BLL.Service.InvoiceService;
import org.vti.vtibackend.BLL.Interface.IInvoiceDAL;

import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceServiceTest {
    @Mock
    private IInvoiceDAL invoiceDAL;

    @Mock
    private InvoiceMapper invoiceMapper;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


}
