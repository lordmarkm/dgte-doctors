import { ErrorHandler, Injectable} from '@angular/core';

//This does not handle HTTP erorrs like 401, 403!!! See global.http.error.handler for that
@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor() { }
  handleError(error) {
     console.log('Error!')
     // IMPORTANT: Rethrow the error otherwise it gets swallowed
     throw error;
  }
  
}
