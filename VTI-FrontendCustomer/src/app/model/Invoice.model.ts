export interface Invoice {
  invoice_id: number;
  customer_id: number;
  car_id: number | null;
  invoice_date: Date;
  due_date: Date;
  total_btw: number;
  total_amount: number;
  status: string;
  invoice_number: number;
}