import { Component, OnInit } from '@angular/core';
import { ApiService } from "../api.service";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Revenue } from "../model/Revenue.model";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})

export class DashboardComponent implements OnInit {
  selectedYear: number = new Date().getFullYear(); // Standaard huidige jaar
  years: number[] = []; // Beschikbare jaren
  quarterlyRevenue: Revenue[] = [];
  hasData = true;

  constructor(private apiService: ApiService ) {}

  ngOnInit(): void {
    this.initializeYears(); // Vul de jarenlijst
    this.loadData(); // Haal data op voor het huidige jaar
  }

  // Maak een lijst van beschikbare jaren (bijvoorbeeld de laatste 10 jaar)
  initializeYears(): void {
    const currentYear = new Date().getFullYear();
    this.years = Array.from({ length: 10 }, (_, i) => currentYear - i);
  }

  // Haal de kwartaalcijfers op voor het geselecteerde jaar
  loadData(): void {

    this.apiService.getRevenue(this.selectedYear).subscribe(
      (data) => {
        // Normalizeer en vul ontbrekende velden aan
        const normalizedData: Revenue[] = [1, 2, 3, 4].map((quarter) => {
          const found = data.find((item) => item.quarter === quarter);
          return {
            year: this.selectedYear,                  // Voeg jaar toe
            quarter: quarter,                         // Huidige kwartaal
            total_amount: found?.total_amount || 0,   // Omzet, of 0 als niet gevonden
            invoice_count: found?.invoice_count || 0  // Factuuraantal, of 0 als niet gevonden
          };
        });

        this.hasData = normalizedData.some((item) => item.invoice_count > 0);

        this.quarterlyRevenue = normalizedData; // Gegevens toewijzen
      },
      (error) => {
        console.error('Fout bij het ophalen van de kwartaalcijfers:', error);
        this.quarterlyRevenue = []; // Leegmaken bij fout
        this.hasData = false; // Leegmaken bij fout
      }
    );
  }
}
