import { ActivatedRoute, Router } from '@angular/router';

import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { ClassScheduling } from '../../../Models/class-scheduling.model';
import { ClassSchedulingService } from '../../../Services/ClassSchedulingService.service';
import { Subscription } from 'rxjs';





@Component({
  selector: 'app-classscheduling',
  templateUrl: './classscheduling.component.html',
  styleUrl: './classscheduling.component.css'
})
export class ClassschedulingComponent implements OnInit,OnDestroy {

  private subscription!:Subscription
  classes: ClassScheduling[] = [];
  newClass: ClassScheduling = { classId: 0, className: '', name: '', date: new Date(), enrolled: 0, duration: '' };

  isEditing: boolean = false;
  editingClass: ClassScheduling | null = null;

  constructor(private router: Router, private route: ActivatedRoute, private classService: ClassSchedulingService,private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params['name']) {
        this.newClass.name = params['name'];
        this.loadClasses();
      }
    });
  }

  loadClasses() {
    this.classService.getOneWeekClassScheduling().subscribe(classes => {
      this.classes = classes;
    });
  }

  saveClass() {
    this.classService.saveClassScheduling(this.newClass).subscribe((savedClass: ClassScheduling) => {
      ('Class saved successfully');
      this.showSnackBar('Class saved successfully!');
     
      this.classes.push(savedClass);
      
    });
  }
  
  
  showSnackBar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000, // Duration in milliseconds
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }

  editClass(classToEdit: ClassScheduling) {
    this.isEditing = true;
    this.editingClass = { ...classToEdit }; 
     this.newClass.name = classToEdit.name; 
  }
  
  
 
  updateClass() {
    if (this.editingClass) {
      this.classService.saveClassScheduling(this.editingClass).subscribe(() => {
        
        this.showSnackBar('Class updated successfully!');
        this.isEditing = false;
        this.editingClass = null;
        this.loadClasses();
      });
    } else {
      console.error('No class to update.');
    }
  }

  deleteClass(deleteClass: ClassScheduling) {
    this.classes = this.classes.filter(c => c.classId !== deleteClass.classId); 
    this.showSnackBar('Class deleted successfully!');
    this.classService.deleteClassScheduling(deleteClass.classId).subscribe(() => {
     
    });
  }
  
  goToUserClass() {
    this.router.navigate(['/user-class']);
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
    
}