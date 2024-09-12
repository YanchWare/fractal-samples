import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Cities, CityInfoResponse, GetCitiesPayload } from '../../core/models/dashboard.model';
import { environment } from '@src/environments/environment.prod';
import { ENDPOINTS } from '@src/app/constants/endpoints';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  constructor(private http: HttpClient) {}

  /**
   * Retrieves a list of cities.
   * @param limit - The maximum number of cities to retrieve. Default is 20.
   * @param offset - The number of cities to skip. Default is 0.
   * @returns An Observable of type Cities containing the list of cities.
   */
  getCities({ limit = 20, offset = 0 }: GetCitiesPayload): Observable<Cities> {
    // FIXME: Remove this hardcoded response and uncomment line 31 to test whit real data
    return of({ cities: [
      { cityId: '1', name: 'New York', country: 'USA' },
      { cityId: '2', name: 'Los Angeles', country: 'USA' },
      { cityId: '3', name: 'San Francisco', country: 'USA' },
      { cityId: '4', name: 'Chicago', country: 'USA' },
      { cityId: '5', name: 'Miami', country: 'USA' },
      { cityId: '6', name: 'Las Vegas', country: 'USA' },
      { cityId: '7', name: 'Orlando', country: 'USA' },
      { cityId: '8', name: 'Seattle', country: 'USA' },
      { cityId: '9', name: 'Boston', country: 'USA' },
    ], count: 0 } as Cities);
    // return this.http.get<Cities>(`${environment.baseUrl}/${ENDPOINTS.CITIES}?limit=${limit}&offset=${offset}`);
  }

  /**
   * Retrieves detailed information about a specific city.
   * @param cityId - The ID of the city to retrieve information for.
   * @returns An Observable of type CityInfoResponse containing the city information.
   */
  getCity(cityId: string): Observable<CityInfoResponse> {
    // FIXME: Remove this hardcoded response and uncomment line 55 to test whit real data
    return of({
      cityId: '1',
      name: 'New York',
      country: 'USA',
      costOfLivingIndex: 70,
      internetSpeed: { download: 73, upload: 53 },
      coworkingSpaces: [
        { name: 'Coworking Space 1', address: 'Address 1', rating: 5 },
        { name: 'Coworking Space 2', address: 'Address 2', rating: 4 },
        { name: 'Coworking Space 3', address: 'Address 3', rating: 3 },
      ],
      safetyIndex: 89,
      climate: { averageTemperature: 24, rainfall: 82 },
    } as CityInfoResponse);
    // return this.http.get<CityInfoResponse>(`${environment.baseUrl}/${ENDPOINTS.CITIES}/${cityId}`);
  }
}
