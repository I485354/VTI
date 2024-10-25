export interface Invoice {
  id: number;
  customer_id: number;
  invoice_date: Date;
  due_date: Date;
  total_amount: number;
  status: string;
}
