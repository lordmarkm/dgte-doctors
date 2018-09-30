export class Page {
  totalPages: number;
  totalElements: number;
  content: Audit[];
}

export class Audit {
  id: number;
  method: string;
  description: string;
  request: string;
  response: string;
  processingTimeMs: number;
  txnReference: string;
  application: string;
}
