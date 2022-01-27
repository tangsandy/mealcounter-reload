import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {firstValueFrom} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  private readonly baseUrl: string;

  constructor(private readonly http: HttpClient) {
    this.baseUrl = environment.baseUrl
  }


  public post(route: string, body: any): Promise<Object> {
    return firstValueFrom(this.http.post(`${this.baseUrl}/${route}`, body));
  }

  public put(route: string, body: any): Promise<Object> {
    return firstValueFrom(this.http.put(`${this.baseUrl}/${route}`, body));
  }

  public get(route: string): Promise<Object> {
    return firstValueFrom(this.http.get(`${this.baseUrl}/${route}`));
  }


}
