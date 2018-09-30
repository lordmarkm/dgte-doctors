import { Component, ViewEncapsulation, ChangeDetectorRef, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { Modal } from 'ngx-modialog/plugins/bootstrap';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { forkJoin } from 'rxjs/observable/forkJoin';

const HttpUploadOptions = {
      headers: new HttpHeaders({ 
        'Authorization' : 'Client-ID ' + environment.imgur.clientId
      })
    };

@Component({
  selector: 'app-multiple-image-uploader',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './multiple-image-uploader.component.html',
  styleUrls: ['./multiple-image-uploader.component.scss']
})
export class MultipleImageUploaderComponent implements OnInit {
    public images: string[] = [];
    private imgurUrls: string[] = [];
    dirty: boolean = false;
    loading: boolean = false;
    imgurUrl: string = 'https://api.imgur.com/3/image';

    @Input('existingUrls')
    existingUrls: string[];
    @Output('save')
    savedImages = new EventEmitter<string[]>();
    @Output('cancel')
    cancelEventEmitter = new EventEmitter<boolean>();

    constructor( private changeDetectorRef: ChangeDetectorRef, private modal: Modal, private http: HttpClient ) { }
    
    ngOnInit () {
      //load existing images, if any
      //TODO is there any way to get away with no reupload???
      console.log('existing urls: ' + this.existingUrls);
      if (this.existingUrls.length) {
        this.loading = true;
        let promises: Observable<any>[] = this.existingUrls.map(url => {
          return this.http.get(url, { responseType: 'blob' });
        });
        console.log('promises: ' + promises);
        forkJoin(promises).subscribe(resps => {
          resps.map(resp => { 
            console.info('got resp data: ' + resp);
            let reader = new FileReader();
            this.readFile(resp, reader, (result) => {
              this.images.push(result);
            });
          });
        },
        (err) => {
          console.info(err);
          this.loading = false;
        },
        () => {
          this.loading = false;
        });
      }
    }

    fileChange(input){
        this.readFiles(input.files);
    }
    
    readFile(file, reader, callback){
        reader.onload = () => {
            callback(reader.result);
        }
        reader.readAsDataURL(file);
    }
    
    readFiles(files, index=0){
        let reader = new FileReader();
        
        if (index in files){
            this.readFile(files[index], reader, (result) =>{
                this.images.push(result);
                this.readFiles(files, index+1);
            });
        }else{
            this.changeDetectorRef.detectChanges();
            this.dirty = true;
        }
    }

    removeImage(index):void{
        if (this.loading) {
          return;
        }
        this.images.splice(index, 1);
        this.dirty = true;
    }

    save() {
      if (!this.dirty) {
        //if saved w/o doing anything, might as well be a cancel
        this.cancelEventEmitter.emit(true);
      }

      let pending = this.images.length;
      if (this.images.length) {
        let promises: Observable<any>[] = this.images.map(img => {
          let formData = new FormData();
          let imageStr = img.split(',')[1];
          formData.append('image', imageStr);
          formData.append('type', 'base64');
          return this.http.post(this.imgurUrl, formData, HttpUploadOptions);
        });

        //https://coryrylan.com/blog/angular-multiple-http-requests-with-rxjs
        this.loading = true;
        forkJoin(promises).subscribe(resps => {
          let urls = resps.map(resp => resp.data.link);
          console.info('imgur urls: ' + JSON.stringify(urls));
          this.savedImages.emit(urls);
        },
        err => {
          alert('There was an error! Please refresh the page and try again');
          this.loading = false;
        },
        () => {
          this.loading = false;
        });
      } else {
        this.savedImages.emit([]);
      }
    }

    cancel() {
      let confirmClose = Promise.resolve(true);
    
      if (this.dirty) {
        confirmClose = this.modal.confirm()
          .title('Cancel')
          .body('Are you sure? You will lose all unsaved data')
          .okBtn('Yes, discard data')
          .cancelBtn('No')
          .open().result;
      }

      confirmClose.then(r => {
        console.log('Closing!');
        this.cancelEventEmitter.emit(true);
      }, () => { /*do nothing on negative*/ });
    }

}
