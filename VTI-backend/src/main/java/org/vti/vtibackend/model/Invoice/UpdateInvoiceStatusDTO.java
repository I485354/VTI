package org.vti.vtibackend.model.Invoice;


public class UpdateInvoiceStatusDTO {

    private String status;

    public UpdateInvoiceStatusDTO() {
    }

    public UpdateInvoiceStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

