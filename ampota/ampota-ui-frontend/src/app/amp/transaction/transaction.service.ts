import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Page } from './transaction.model';
import { Transaction } from '@app/amp/shopping-cart/shopping-cart.model';
import { environment } from '../../../environments/environment'

@Injectable()
export class TransactionService {
    public url = environment.ampUrl + "/api/transaction";

    constructor(public http:HttpClient) { }

    public get(term: string, size: string): Observable<Page> {
        console.debug('Getting transactions providers!');
        let params = new HttpParams()
          .set('term', term)
          .set('size', size);
        return this.http.get<Page>(this.url, { params: params });
    }

    public findOne(id: number): Observable<Transaction> {
      return this.http.get<Transaction>(this.url + '/' + id);
    }

    public save(txn: Transaction) {
        console.debug('Save request!');
        return this.http.post(this.url, txn);
    }

} 