export interface Invoice {
  invoice_id: number;
  customer_id: number;
  invoice_date: Date;
  due_date: Date;
  total_btw: number;
  total_amount: number;
  status: string;
}
