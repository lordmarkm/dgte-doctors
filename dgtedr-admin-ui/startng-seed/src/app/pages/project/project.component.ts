import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ProjectService } from './project.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss'],
  encapsulation: ViewEncapsulation.None,
  providers:[ ProjectService ]
})
export class ProjectComponent implements OnInit {

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.projectService.getProjects('deleted==false').subscribe(page => console.debug(page.content));
  }

}
