import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SolicitudService {

constructor(private httpClient: HttpClient) { }


    public añadirSolicitud(id:any): Observable<JsonResponse>{
      return this.httpClient.post<JsonResponse>(`${baseUrl}/usuarios/pazysalvo/`,id);
    }

    public getSolicitudbyID(id:any) {
      return this.httpClient.get<any>(`${baseUrl}/usuarios/solicitudes/${id}`);
    }

}
export interface JsonResponse{message: string}
