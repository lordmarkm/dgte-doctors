export class Page {
  totalPages: number;
  totalElements: number;
  content: Project[];
}

export class Project {
  id: number;
  name: string;
  fundSource: FundSource;
  type: string;
}

export class FundSource {
  id: number;
  name: string;
}