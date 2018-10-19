import { Component, ChangeDetectionStrategy, OnInit } from '@angular/core';

import { DialogRef, ModalComponent, CloseGuard } from 'ngx-modialog';
import { BSModalContext } from 'ngx-modialog/plugins/bootstrap';

import { Bundle } from '@app/amp/bundle/bundle.model';
import { BundleService } from '@app/amp/bundle/bundle.service';
import { Modal, bootstrap4Mode } from 'ngx-modialog/plugins/bootstrap';

import { UserProfileService } from '@app/amp/user-profile/user-profile.service';
import { Order} from '@app/amp/shopping-cart/shopping-cart.model';
import { ShoppingCartService } from '@app/amp/shopping-cart/shopping-cart.service';

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
  providers: [ BundleService, UserProfileService ]
})
export class AddToCartModalComponent implements CloseGuard, ModalComponent<CustomModalContext>, OnInit {
  context: CustomModalContext;
  bundle: Bundle;
  cardImg: string;

  qty: number = 1;
  price: number;

  constructor(public  dialog: DialogRef<CustomModalContext>, private bundleService: BundleService, private modal: Modal, private cart: ShoppingCartService,
    private userProfileService: UserProfileService) {
    this.context = dialog.context;
    if (dialog.context.bundle) {
      this.bundle = dialog.context.bundle;
      this.cardImg = this.bundle.card.imageUris.small;
    }
    bootstrap4Mode();
    dialog.setCloseGuard(this);
  }

  ngOnInit() {
    this.computePrice();
  }

  computePrice() {
    if (this.bundle.sellPrice && this.bundle.sellPriceSet) {
      let setsPrice = Math.floor(this.qty / 4) * this.bundle.sellPriceSet;
      this.price = setsPrice + ((this.qty % 4) * this.bundle.sellPrice);
    } else if (this.bundle.sellPrice) {
      this.price = this.bundle.sellPrice * this.qty;
    }
  }

  beforeClose(): boolean {
    //return true to prevent dialog close
    return false;
  }

  cancel(cartForm) {
    this.modal.confirm()
      .title('Cancel')
      .body('Are you sure? Item has not yet been added to cart')
      .okBtn('Yes, cancel order')
      .okBtnClass('btn btn-danger')
      .cancelBtn('No')
      .open().result.then(r => {
        this.dialog.close();
      }, () => { /*do nothing on negative*/ });
  }

  //Save bundle
  public saveOrder() {
    let order: Order = new Order();
    order.bundle = this.bundle;
    order.qty = this.qty;
    order.price = this.price;
    this.cart.addToCart(order, null, null);
    this.dialog.close();
  }

}