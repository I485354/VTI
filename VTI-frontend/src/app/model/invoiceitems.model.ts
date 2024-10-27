export interface InvoiceItem {
  invoice_item_id: number;
  invoice_id: number;
  product_id: number;
  quantity: number;
  unit_price: number;
  btw: number;
  total: number;
}
