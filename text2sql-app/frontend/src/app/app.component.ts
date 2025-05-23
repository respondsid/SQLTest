import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms'; // Import FormsModule
import { HttpClient, HttpClientModule } from '@angular/common/http'; // Import HttpClient and HttpClientModule

@Component({
  selector: 'app-root',
  standalone: true, // Mark as standalone
  imports: [CommonModule, RouterOutlet, FormsModule, HttpClientModule], // Add CommonModule, FormsModule, HttpClientModule
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Natural Language to SQL Interface';
  naturalLanguageQuery: string = '';
  generatedSql: string = '';
  queryResults: any[] = [];
  error: string = '';

  constructor(private http: HttpClient) {} // Inject HttpClient

  executeQuery() {
    this.generatedSql = '';
    this.queryResults = [];
    this.error = '';

    if (!this.naturalLanguageQuery.trim()) {
      this.error = 'Please enter a query.';
      return;
    }

    const apiUrl = 'http://localhost:8080/api/query'; // Backend API URL
    const requestBody = { query: this.naturalLanguageQuery };

    this.http.post<any>(apiUrl, requestBody).subscribe({
      next: (response) => {
        this.generatedSql = response.generatedSql;
        this.queryResults = response.queryResults;
        if (response.error) {
          this.error = response.error;
        }
      },
      error: (err) => {
        console.error('Error executing query:', err);
        this.error = err.error?.error || 'An unexpected error occurred while communicating with the backend.';
        this.generatedSql = err.error?.generatedSql || ''; // Display generated SQL even on error if available
      }
    });
  }

  getColumnNames(): string[] {
    if (this.queryResults.length === 0) {
      return [];
    }
    return Object.keys(this.queryResults[0]);
  }
}
