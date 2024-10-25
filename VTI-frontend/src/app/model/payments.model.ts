export interface Payment {
  payment_id: number;
  invoice_id: number;
  payment_date: Date;
  amount: number;
  payment_method: string;
}
