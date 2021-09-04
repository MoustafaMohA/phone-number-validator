import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NzTableQueryParams } from 'ng-zorro-antd/table';
import { CustomerModel } from './customer-model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public customerList : CustomerModel[] = [];
  public customersCount = 0;
  public isDataLoading = true;  
  public countries = [{
    text: 'Cameroon',
    value: '+237'
  }, {
    text: 'Ethiopia',
    value: '+251'
  }, {
    text: 'Morocco',
    value: '+212'
  }, {
    text: 'Mozambique',
    value: '+258'
  }, {
    text: 'Uganda',
    value: '+256'
  }];

  public states = [
    {
      text: 'Valid',
      value: true
    }, {
      text: 'Invalid',
      value: false
    }];

  constructor(private http: HttpClient) {
    this.http.get<number>(`/api/customer/count`).subscribe(data => {
      this.customersCount = data;
    });
  }

  initCustomerList(page: number, size: number): void {
    this.isDataLoading = true;
    this.http.get<CustomerModel[]>(`/api/customer?page=${page}&size=${size}`).subscribe(data => {
      this.customerList = data;
      this.isDataLoading = false;
    });
  }

  searchCutomerList(page: number, size: number, filter: Array<{ key: string; value: any }>): void {
    const customer: CustomerModel = {
      countryCode: filter.find( f => f.key === 'countryCode')?.value,
      isValid: filter.find( f => f.key === 'state')?.value
    }
    this.isDataLoading = true;


    this.http.post<number>(`/api/customer/count-search`, customer).subscribe(data => {
      this.customersCount = data;
    });

    this.http.post<CustomerModel[]>(`/api/customer/search?page=${page}&size=${size}`, customer).subscribe(data => {
      this.customerList = data;
      this.isDataLoading = false;
    });
  }

  onQueryParamsChange(event: NzTableQueryParams): void {
    const {pageIndex, pageSize, filter} = event;
    if (filter.map(f => f.value ).filter(f => f !== null && f !== undefined).length > 0) {
      this.searchCutomerList(pageIndex - 1, pageSize, filter)
    } else {
      this.initCustomerList(pageIndex - 1, pageSize);
    }
  }
}
