import { Component, ChangeDetectionStrategy, OnInit } from '@angular/core';

import { DialogRef, ModalComponent, CloseGuard } from 'ngx-modialog';
import { BSModalContext } from 'ngx-modialog/plugins/bootstrap';

import { Bundle } from '@app/amp/bundle/bundle.model';
import { BundleService } from '@app/amp/bundle/bundle.service';
import { Modal, bootstrap4Mode } from 'ngx-modialog/plugins/bootstrap';

export class CustomModalContext extends BSModalContext {
  public bundle: Bundle;
}

/**
 * A Sample of how simple it is to create a new window, with its own injects.
 */
@Component({
  selector: 'modal-content',
  changeDetection: ChangeDetectionStrategy.Default,
  styleUrls: [ './add-to-cart.modal.scss' ],
  templateUrl: './add-to-cart.modal.html',
  providers: [ BundleService ]
})
export class AddToCartModalComponent implements CloseGuard, ModalComponent<CustomModalContext>, OnInit {
  context: CustomModalContext;
  bundle: Bundle;

  constructor(public dialog: DialogRef<CustomModalContext>, private bundleService: BundleService, private modal: Modal) {
    this.context = dialog.context;
    if (dialog.context.bundle) {
      this.bundle = dialog.context.bundle;
    }
    this.context.dialogClass = 'modal-dialog modal-lg';
    bootstrap4Mode();
    dialog.setCloseGuard(this);
  }

  ngOnInit() {
  }

  beforeClose(): boolean {
    //return true to prevent dialog close
    return false;
  }

  cancel(cartForm) {
    let confirmClose = Promise.resolve(true);
    
    if (cartForm.dirty) {
      confirmClose = this.modal.confirm()
        .title('Cancel')
        .body('Are you sure? Item has not yet been added to cart')
        .okBtn('Yes, cancel order')
        .cancelBtn('No')
        .open().result;
    }

    confirmClose.then(r => {
      this.dialog.close();
    }, () => { /*do nothing on negative*/ });
  }

  //Save bundle
  public saveBundle() {
    this.bundleService.save(this.bundle).subscribe(savedBundle => {
      this.dialog.close(savedBundle);
    });
  }

}