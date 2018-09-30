export class Page {
  totalPages: number;
  totalElements: number;
  content: KnowYourCustomer[];
}

export class KnowYourCustomer {
  registration: Registration;
  id: number;
  createdDate: string;
  updatedDate: string;
  firstName: string;
  lastName: string;
  gender: string;
  birthday: string;
  birthplace: string;
  civilStatus: string;
  nationality: string;
  mothersMaidenName: string;
  street: string;
  barangay: string;
  city: string;
  province: string;
  zip: string;
  sourceOfFunds: string;
  employmentStatus: string;
  companyName: string;
  companyStreet: string;
  companyBarangay: string;
  companyCity: string;
  companyProvince: string;
  companyZip: string;
  idFrontUrl: string;
  idBackUrl: string;
  proofOfAddressUrl: string;
}

export class Registration {
  email: string;
  mobileNo: string;
  password: string;
}
