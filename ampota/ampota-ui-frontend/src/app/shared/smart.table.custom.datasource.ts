import { HttpParams } from '@angular/common/http';
import { ServerDataSource } from 'ng2-smart-table';

//overriding functions from here https://github.com/akveo/ng2-smart-table/blob/master/src/ng2-smart-table/lib/data-source/server/server.data-source.ts
//TODO put in own file for reusability
export class CustomDataSource extends ServerDataSource {

  forSale: boolean;
  uniqueCardnameFilter: string;
  legality: string;
  colors: string[];

  public refreshFilters(filters: any) {
    this.forSale = filters.forSale;
    this.uniqueCardnameFilter = filters.cardName;
    this.legality = filters.legality;
    this.colors = filters.colors;
    this.refresh();
  }
  public setForSale(forSale: boolean) {
    this.forSale = forSale;
  }

  public setUniqueCardnameFilter(name: string) {
    this.uniqueCardnameFilter = name;
  }

  protected addSortRequestParams(httpParams: HttpParams): HttpParams {
    if (this.sortConf) {
      //default sort by created date desc
      if (!this.sortConf.length) {
        httpParams = httpParams.set('sort', 'createdDate,DESC');
      }
      this.sortConf.forEach((fieldConf) => {
        let sortKey = fieldConf.field + ',' + fieldConf.direction.toUpperCase();
        httpParams = httpParams.set('sort', sortKey);
      });
    }
    return httpParams;
  }

  protected addFilterRequestParams(httpParams: HttpParams): HttpParams {
    let term = 'deleted==false';
    if (this.forSale) {
      term += ';forSale==true';    
    }
    if (this.uniqueCardnameFilter) {
      term += ';cardName=="' + this.uniqueCardnameFilter + '"';
    }
    if (this.colors && this.colors.length) {
      term += ';colors=any=(' + this.colors + ')';
    }
    if (this.legality) {
      term += ';' + this.legality + '=in=(legal,restricted)';
    }
    if (this.filterConf.filters) {
      this.filterConf.filters.forEach((fieldConf: any) => {
        if (fieldConf['search']) {
          if (term.length) {
            term += ';';
          }
          term += fieldConf['field'] + '=like=' + fieldConf['search'];
        }
      });
    }
    httpParams = httpParams.set('term', term);
    return httpParams;
  }

  protected extractTotalFromResponse(res: any): number {
    return res.body.totalElements;
  }

	public update(element: any, values: any): Promise<any> {
	    return new Promise((resolve, reject) => {
	        this.find(element).then(found => {
	            //Copy the new values into element so we use the same instance
	            //in the update call.
	            element.name = values.name;
	            element.enabled = values.enabled;
	            element.condition = values.condition;
	
	            //Don't call super because that will cause problems - instead copy what DataSource.ts does.
	            ///super.update(found, values).then(resolve).catch(reject);
	            this.emitOnUpdated(element);
	            this.emitOnChanged('update');
	            resolve();
	        }).catch(reject);
	    });
	}
	public find(element: any): Promise<any> {
	    //Match by the trigger id
	    const found: any = this.data.find(el => el.id === element.id);
	     if (found) {
	        return Promise.resolve(found);
	    }
	    return Promise.reject(new Error('Element was not found in the dataset'));
	}

}