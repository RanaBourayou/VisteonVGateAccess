// visit-request.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { VisitRequest } from '../models/visit-request.model'; 
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VisitRequestService {
  private baseUrl = 'http://localhost:8080/visteonS/api/visit-requests'; 

  constructor(private http: HttpClient) {}

  createVisitRequest(request: VisitRequest): Observable<VisitRequest> {
    return this.http.post<VisitRequest>(this.baseUrl, request);
  }

  getVisitRequestById(id: number): Observable<VisitRequest> {
    return this.http.get<VisitRequest>(`${this.baseUrl}/${id}`);
  }

  getAllVisitRequests(): Observable<VisitRequest[]> {
    return this.http.get<VisitRequest[]>(this.baseUrl);
  }

  updateVisitRequest(id: number, request: VisitRequest): Observable<VisitRequest> {
    return this.http.put<VisitRequest>(`${this.baseUrl}/${id}`, request);
  }

  deleteVisitRequest(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  assignVisitorToRequest(requestId: number, visitorId: number): Observable<VisitRequest> {
    return this.http.post<VisitRequest>(`${this.baseUrl}/${requestId}/assign/${visitorId}`, null);
  }

  getRequestsByRequesterId(userId: number): Observable<VisitRequest[]> {
    return this.http.get<VisitRequest[]>(`${this.baseUrl}/by-requester/${userId}`);
  }
}
