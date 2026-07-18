import { Routes } from '@angular/router';
import { RegisterComponent } from './register/register';
import { LoginComponent } from './login/login';
import { TasksComponent } from './tasks/tasks';
import { TaskformComponent } from './taskform/taskform';


export const routes: Routes = [
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: 'tasks', component: TasksComponent},
    {path: 'tasks/add', component: TaskformComponent},
    {path: 'tasks/edit/:id', component: TaskformComponent},

    // DEFAULT FALLBACK URL
    {path: '', redirectTo: '/tasks', pathMatch: 'full'},
    {path: '**', redirectTo: '/tasks', pathMatch: 'full'}
];
