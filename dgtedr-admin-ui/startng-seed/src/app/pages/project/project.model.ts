export class Page {
  totalPages: number;
  totalElements: number;
  content: Project[];
}

export class Project {
  id: number;
  name: string;
  fundSource: FundSource;
  static readonly type = 'project';
}

export class FundSource {
  id: number;
  name: string;
}