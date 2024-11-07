export interface AccountingEntry {
  entry_id: number;
  invoice_id: number;
  entry_date: Date;
  debit_amount: number;
  credit_amount: number;
  descriptions: string;
  
}
 